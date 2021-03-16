import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

import model._
import upickle.default._

class JsonSpec extends AnyFlatSpec with should.Matchers {

  "reading resources.json" should "convert to case class" in {
    val json = scala.io.Source.fromResource("resources.json").mkString
    val expected = SingularityResources(0.1, 2.0, 3, 4)
    read[SingularityResources](json) should be (expected)    
  }

}
