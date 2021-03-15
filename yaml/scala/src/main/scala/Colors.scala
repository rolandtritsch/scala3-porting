import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

case class Color(name: String, red: Int, green: Int, blue: Int)
case class Colors(colors: List[Color])

object ColorsYamlProtocol extends DefaultYamlProtocol {
  implicit val colorFormat = yamlFormat4(Color)
  implicit val colorsFormat = yamlFormat1(Colors)
}
