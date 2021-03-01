val scala3Version = "3.0.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-logging",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies += "de.sciss" %% "log" % "0.1.1",
  )
