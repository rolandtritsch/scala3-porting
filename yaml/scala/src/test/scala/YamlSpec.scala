import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

class YamlSpec extends AnyFlatSpec with should.Matchers {

  "reading hello.yml" should "return the right AST" in {
    val yamlString = scala.io.Source.fromResource("hello.yml").mkString
    val yamlAst = yamlString.parseYaml
    val expected = new YamlObject(Map(new YamlString("hello") -> new YamlString("world"), new YamlString("languages") -> new YamlArray(Vector(new YamlString("scala"), new YamlString("scala3"), new YamlString("roland")))))
    yamlAst should be (expected)
  }

}
