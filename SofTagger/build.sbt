name := "SofTagger"

version := "0.1"

scalaVersion := "2.11.11"

val sparkVersion = "2.2.0"
val sparkGroupId = "org.apache.spark"

val hibernateVersion = "4.3.5.Final"
val hibernateGroupId = "org.hibernate"

val springVersion = "4.0.6.RELEASE"
val springGroupId = "org.springframework"


resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

//libraryDependencies ++= Seq(
//  // "spark-hive"
//  sparkGroupId %% "spark-core" % sparkVersion,
//  sparkGroupId %% "spark-sql" % sparkVersion,
//  sparkGroupId %% "spark-mllib" % sparkVersion,
//  sparkGroupId %% "spark-streaming" % sparkVersion,
//)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"


//libraryDependencies ++= Seq(
//    hibernateGroupId % "hibernate-entitymanager" % hibernateVersion,
//    hibernateGroupId % "hibernate-core" % hibernateVersion,
//)
//
//libraryDependencies ++= Seq(
//    springGroupId % "spring-context" % springVersion,
//    springGroupId % "spring-orm" % springVersion,
//)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"


libraryDependencies += "org.projectlombok" % "lombok" % "1.16.6"
libraryDependencies ++= Seq(
  hibernateGroupId % "hibernate-entitymanager" % hibernateVersion,
  hibernateGroupId % "hibernate-core" % hibernateVersion
)
libraryDependencies += "postgresql" % "postgresql" % "9.1-901.jdbc4"
libraryDependencies += "junit" % "junit" % "4.11"
libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"
libraryDependencies ++= Seq(
  springGroupId % "spring-test" % springVersion,
  springGroupId % "spring-orm" % springVersion,
  springGroupId % "spring-context" % springVersion
)
libraryDependencies += "commons-dbcp" % "commons-dbcp" % "20030825.184428"
libraryDependencies += "commons-pool" % "commons-pool" % "20030825.183949"
libraryDependencies += "commons-collections" % "commons-collections" % "3.2.1"
libraryDependencies += "org.glassfish.extras" % "glassfish-embedded-all" % "3.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.180"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"
