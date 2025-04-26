name := "play-quill-jdbc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
  jdbc,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
  "com.h2database" % "h2" % "1.4.190",
  "io.getquill" %% "quill-jdbc" % "4.8.5",
  "com.typesafe.play" %% "play-jdbc-evolutions" % "2.7.9"
)

routesGenerator := InjectedRoutesGenerator