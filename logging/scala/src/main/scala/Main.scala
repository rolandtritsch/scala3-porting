import com.typesafe.scalalogging.LazyLogging

object Hello extends LazyLogging {
  def main(_args: Array[String]): Unit = {
    logger.info("Entering main")
    println(message)
  }

  def message: String = {
    logger.info("Entering message")
    "Hello Scala 2!"
  }
}
