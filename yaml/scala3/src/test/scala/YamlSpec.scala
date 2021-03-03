import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

import net.jcazevedo.moultingyaml._
//import net.jcazevedo.moultingyaml.DefaultYamlProtocol._
import ColorYamlProtocol._

class YamlSpec extends AnyFlatSpec with should.Matchers {

  "reading hello.yml" should "return the right AST" in {
    val yaml = scala.io.Source.fromResource("hello.yml").mkString
    val yamlAst = yaml.parseYaml
    val expected = new YamlObject(Map(new YamlString("hello") -> new YamlString("world"), new YamlString("languages") -> new YamlArray(Vector(new YamlString("scala"), new YamlString("scala3"), new YamlString("roland")))))
    yamlAst should be (expected)
  }

  "reading colors.yml" should "convert to the right case class" in {
    val yaml = scala.io.Source.fromResource("colors.yml").mkString
    val yamlAst = yaml.parseYaml
    val colors = yamlAst.convertTo[Colors]
    val expected = Colors(List(
      Color("red",100,0,0),
      Color("green",0,100,0),
      Color("blue",0,0,100)
    ))
    colors should be (expected)
  }

}
