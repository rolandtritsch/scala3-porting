import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

case class Palette(name: Option[String], colors: List[Color])

object PaletteYamlProtocol extends DefaultYamlProtocol {
  implicit val colorFormat: YamlFormat[Color] = yamlFormat4(Color.apply)
  implicit val paletteFormat: YamlFormat[Palette] = yamlFormat2(Palette.apply)
}
