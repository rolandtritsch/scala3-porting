
object Pickler {
  def main(args: Array[String]): Unit = {
    require(args.size == 1, "Usage: Pickler <fileName>")
    val fileName = args(0)

    val jsonString = scala.io.Source.fromFile(fileName).mkString

  }
}
