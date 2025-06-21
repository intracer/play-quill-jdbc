name := "play-quill-jdbc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

val TestcontainersScalaVersion = "0.43.0"

libraryDependencies ++= Seq(
  jdbc,
  "com.h2database" % "h2" % "2.3.232",
  "org.mariadb.jdbc" % "mariadb-java-client" % "3.5.3",
  "io.getquill" %% "quill-jdbc" % "4.8.5",
  "org.playframework" %% "play-jdbc-evolutions" % "3.0.7",
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
  "com.dimafeng" %% "testcontainers-scala-scalatest" % TestcontainersScalaVersion % Test,
  "com.dimafeng" %% "testcontainers-scala-mariadb" % TestcontainersScalaVersion % Test
)

routesGenerator := InjectedRoutesGenerator
