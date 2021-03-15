import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

case class Palette(name: Option[String], colors: List[Color])
case class Color(name: String, red: Int, green: Int, blue: Int)

object PaletteYamlProtocol extends DefaultYamlProtocol {
  implicit val colorFormat = yamlFormat4(Color)
  implicit val paletteFormat = yamlFormat2(Palette)
}
