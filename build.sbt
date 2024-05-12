scalaVersion := "2.12.19"
name := "kafka-producer"
version := "0.1"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.2",
  "org.apache.kafka" % "kafka-clients" % "2.4.0",
  "org.apache.kafka" % "connect-json" % "2.4.0",
  "org.apache.kafka" % "connect-runtime" % "2.4.0",
  "org.apache.kafka" % "kafka-streams" % "2.4.0",
  "org.apache.kafka" %% "kafka-streams-scala" % "2.4.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.5",
  "org.apache.kafka" % "connect-runtime" % "2.1.0")