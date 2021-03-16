import upickle.default.{ReadWriter => RW, macroRW}

object model {

  case class SingularityResources(
      cpus: Double,
      memoryMb: Double,
      numPorts: Int,
      diskMb: Int
  )

  object SingularityResources {
    implicit val rw: RW[SingularityResources] = macroRW
  }

}
