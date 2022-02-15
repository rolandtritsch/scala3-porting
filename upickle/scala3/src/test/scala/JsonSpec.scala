import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

import model._
import OptionPickler._

class JsonSpec extends AnyFlatSpec with should.Matchers {

  "reading resources.json" should "convert to case class" in {
    val json = scala.io.Source.fromResource("resources.json").mkString
    val expected = SingularityResources(0.1, 2.0, 3, 4)
    read[SingularityResources](json) should be (expected)    
  }

  "reading docker-info.json" should "convert to case class" in {
    val json = scala.io.Source.fromResource("docker-info.json").mkString
    val expected = SingularityDockerInfo(
      "network",
      "image",
      Seq(
        SingularityDockerPortMapping(8080, "hostPortType", "containerPortType", 8081, "protocol"),
        SingularityDockerPortMapping(8080, "hostPortType", "containerPortType", 8081, "protocol")
      ),
      true,
      Seq(
        SingularityDockerParameter("key", "value"),
        SingularityDockerParameter("key", "value"),
        SingularityDockerParameter("key", "value")
      )
    )
    read[SingularityDockerInfo](json) should be (expected)    
  }

}
