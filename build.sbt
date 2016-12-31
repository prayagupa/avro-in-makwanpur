name := "avro-in-manchester-ww2"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies += "org.apache.avro" % "avro" % "1.8.1"

libraryDependencies += "org.apache.avro" % "avro-ipc" % "1.8.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
