name := "Remote Actors"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-kernel" % "2.0.3",
  "com.typesafe.akka" % "akka-actor" % "2.0.3",
  "com.typesafe.akka" % "akka-remote" % "2.0.3"
)