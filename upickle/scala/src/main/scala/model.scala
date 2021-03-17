import OptionPickler.{ReadWriter => RW, macroRW}

object model {

  case class SingularityResources(
      cpus: Option[Double] = None,
      memoryMb: Double,
      numPorts: Int,
      diskMb: Option[Int] = None
  )

  object SingularityResources {
    implicit val rw: RW[SingularityResources] = macroRW
  }

}
