name := "play-quill-jdbc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
  jdbc,
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
  "com.h2database" % "h2" % "2.3.232",
  "io.getquill" %% "quill-jdbc" % "4.8.5",
  "org.playframework" %% "play-jdbc-evolutions" % "3.0.7"
)

routesGenerator := InjectedRoutesGenerator