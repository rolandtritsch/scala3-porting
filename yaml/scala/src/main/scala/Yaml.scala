import net.jcazevedo.moultingyaml._
//import net.jcazevedo.moultingyaml.DefaultYamlProtocol._
import ColorYamlProtocol._

object Yaml {
  def main(args: Array[String]): Unit = {
    require(args.size == 1, "Usage: Yaml <fileName>")
    val fileName = args(0)

    val yamlString = scala.io.Source.fromFile(fileName).mkString
    val yamlAst = yamlString.parseYaml
    println(yamlAst.prettyPrint)
  }
}
