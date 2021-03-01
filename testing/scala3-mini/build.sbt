val scala3Version = "3.0.0-M3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-testing",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies += "io.monix" %% "minitest" % "2.9.2" % "test",
    testFrameworks += new TestFramework("minitest.runner.Framework")
  )
