import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

case class Color(name: String, red: Int, green: Int, blue: Int)
case class Colors(colors: List[Color])

object ColorYamlProtocol extends DefaultYamlProtocol {
  implicit val colorFormat: YamlFormat[Color] = yamlFormat4(Color.apply)
  implicit val colorsFormat: YamlFormat[Colors] = yamlFormat1(Colors.apply)
}
