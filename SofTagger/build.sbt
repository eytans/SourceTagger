name := "SofTagger"

version := "0.1"

scalaVersion := "2.11.11"

val sparkVersion = "2.2.0"
val sparkGroupId = "org.apache.spark"

val hibernateVersion = "4.3.5.Final"
val hibernateGroupId = "org.hibernate"

val springVersion = "4.0.6.RELEASE"
val springGroupId = "org.springframework"

val slf4jVersion = "1.7.25"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

libraryDependencies ++= Seq(
  // "spark-hive"
  sparkGroupId %% "spark-core" % sparkVersion,
  sparkGroupId %% "spark-sql" % sparkVersion,
  sparkGroupId %% "spark-mllib" % sparkVersion,
  sparkGroupId %% "spark-streaming" % sparkVersion,
).map(_.exclude("org.slf4j", "*"))

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % slf4jVersion % "provided",
  "org.slf4j" % "slf4j-nop" % slf4jVersion % "test"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "org.projectlombok" % "lombok" % "1.16.6"
libraryDependencies ++= Seq(
  hibernateGroupId % "hibernate-entitymanager" % hibernateVersion,
  hibernateGroupId % "hibernate-core" % hibernateVersion
)
libraryDependencies += "postgresql" % "postgresql" % "9.1-901.jdbc4"
libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"

libraryDependencies ++= Seq(
  springGroupId % "spring-test" % springVersion,
  springGroupId % "spring-orm" % springVersion,
  springGroupId % "spring-context" % springVersion
).map(_.exclude("org.slf4j", "*"))

libraryDependencies += "commons-dbcp" % "commons-dbcp" % "20030825.184428"
libraryDependencies += "commons-pool" % "commons-pool" % "20030825.183949"
libraryDependencies += "commons-collections" % "commons-collections" % "3.2.1"
libraryDependencies += "org.glassfish.extras" % "glassfish-embedded-all" % "3.0" exclude ("org.slf4j", "*")
libraryDependencies += "com.h2database" % "h2" % "1.4.180"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"

// Add the ScalaMock library (versions 4.0.0 onwards)
libraryDependencies += "org.scalamock" %% "scalamock" % "4.0.0" % Test
// also add ScalaTest as a framework to run the tests
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test