name := """play-scala-postgres"""
organization := "com.tanesh.postgres"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"
resolvers += "Atlassian 3rd-Party" at "https://maven.atlassian.com/3rdparty/"
libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" %  "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"
libraryDependencies += specs2 % Test


