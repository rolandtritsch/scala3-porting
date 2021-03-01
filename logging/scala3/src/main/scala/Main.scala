import de.sciss.log._

object Hello {
  val logger = new Logger("scala3-logging", Level.Info, Console.err)

  def main(_args: Array[String]): Unit = {
    logger.info("Entering main")
    println(message)
  }

  def message: String = {
    logger.info("Entering message")
    "Hello Scala 3!"
  }
}
