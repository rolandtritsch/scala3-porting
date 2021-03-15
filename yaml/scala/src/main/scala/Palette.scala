import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

case class Palette(name: Option[String], colors: List[Color])

object PaletteYamlProtocol extends DefaultYamlProtocol {
  implicit val colorFormat = yamlFormat4(Color)
  implicit val paletteFormat = yamlFormat2(Palette)
}
