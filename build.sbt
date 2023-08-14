import _root_.sbt.Keys._
import wartremover.Wart
import wartremover.Wart._

name := "made-scala"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions := List(
  "-encoding",
  "utf8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8",
  "-language:_",
  "-Ymacro-annotations"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.4.2"
libraryDependencies += "org.mockito" % "mockito-core" % "3.0.0" % "test"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.3"
libraryDependencies += "com.github.fd4s" %% "fs2-kafka" % "1.8.0"
libraryDependencies += "co.fs2" %% "fs2-core" % "2.3.0"
libraryDependencies += "co.fs2" %% "fs2-io" % "2.3.0"
libraryDependencies += "io.github.embeddedkafka" %% "embedded-kafka" % "2.8.1" exclude("org.scala-lang.modules", "scala-java8-compat_2.13")
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.4.1"
libraryDependencies += "dev.zio" %% "zio" % "1.0.12"
libraryDependencies += "dev.zio" %% "zio-interop-cats" % "2.3.1.0"
// And add any of these as needed
libraryDependencies += "org.tpolecat" %% "doobie-core" % "0.9.0"
libraryDependencies += "org.tpolecat" %% "doobie-h2" % "0.9.0" // H2 driver 1.4.200 + type mappings.
libraryDependencies += "org.tpolecat" %% "doobie-hikari" % "0.9.0" // HikariCP transactor.

val AkkaVersion = "2.6.18"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
)

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.10"

wartremoverErrors ++= Seq[Wart](Any, AsInstanceOf, Null, Return, Throw, While, MutableDataStructures)