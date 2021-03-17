import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

import model._
import OptionPickler._

class JsonSpec extends AnyFlatSpec with should.Matchers {

  "reading resources.json" should "convert to case class" in {
    val json = scala.io.Source.fromResource("resources.json").mkString
    val expected = SingularityResources(Some(0.1), 2.0, 3, Some(4))
    read[SingularityResources](json) should be (expected)    
  }

  "reading resources.json" should "convert to case class (with option)" in {
    val json = scala.io.Source.fromResource("resources-with-option.json").mkString
    val expected = SingularityResources(None, 2.0, 3, None)
    read[SingularityResources](json) should be (expected)    
  }

}
