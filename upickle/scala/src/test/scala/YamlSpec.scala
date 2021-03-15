import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

import net.jcazevedo.moultingyaml._
import PaletteYamlProtocol._
import ConfigYamlProtocol._

class YamlSpec extends AnyFlatSpec with should.Matchers {

  "reading palette.yml with name" should "convert to Some()" in {
    val yaml = scala.io.Source.fromResource("palette-with-name.yml").mkString
    val yamlAst = yaml.parseYaml
    val palette = yamlAst.convertTo[Palette]
    val expected = Palette(Some("roland"), List(
      Color("red",100,0,0),
      Color("green",0,100,0),
      Color("blue",0,0,100)
    ))
    palette should be (expected)    
  }

  "reading palette.yml without name" should "convert to None" in {
    val yaml = scala.io.Source.fromResource("palette.yml").mkString
    val yamlAst = yaml.parseYaml
    val palette = yamlAst.convertTo[Palette]
    val expected = Palette(None, List(
      Color("red",100,0,0),
      Color("green",0,100,0),
      Color("blue",0,0,100)
    ))
    palette should be (expected)    
  }

  "reading config.yml" should "convert" in {
    val yaml = scala.io.Source.fromResource("config.yml").mkString
    val yamlAst = yaml.parseYaml
    val config = yamlAst.convertTo[Config]
    val expected = Config(
      "0.0.1",
      Environment(
        Some(Resources(0.1, 64, Some(1))),
        Some(Container(
          "hubspot/singularity-test-service",
          None,
          None,
          Some(List(
            PortMap(6060, None, Some("udp,tcp")),
            PortMap(8080, None, None),
            PortMap(9000, Some(12000), None)
          )),
          None,
          None,
          None,
          None,
          None,
          None
        )),
        None,
        Some(SingularityConf(
          "http://localhost:7099/singularity",
          None,
          None,
          Some(1),
          Some(1000),
          None,
          Some(true),
          Some("/hello"),
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None
        )),
        None
      ),
      Map("dev" -> Environment(
        None,
        None,
        None,
        Some(SingularityConf(
          "http://localhost:7099/singularity",
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None
        )),
        None
      ))
    )
    config should be (expected)    
  }

}
