import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class HelloSpec extends AnyFlatSpec with should.Matchers {

  "message" should "return the right string" in {
    Hello.message shouldEqual "Hello Scala 3!"
  }

}
