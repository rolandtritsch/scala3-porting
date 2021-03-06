val scala3Version = "3.0.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-yaml",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.5",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.5" % "test",
    libraryDependencies += "net.jcazevedo" % "moultingyaml_2.13" % "0.4.2"
  )
