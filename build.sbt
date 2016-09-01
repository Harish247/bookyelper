name := """bookyelper"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

crossPaths := false

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "bootstrap" % "3.3.1"
)

lazy val bookYelper = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

fork in run := false