val scala3Version = "3.5.2"

enablePlugins(JmhPlugin)

lazy val root = project
  .in(file("."))
  .settings(
    name := "adventofcode",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test,
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "org.reflections" % "reflections" % "0.10.2",
    libraryDependencies += "com.chuusai" % "shapeless_2.13" % "2.4.0-M1",
    libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.16"
  )