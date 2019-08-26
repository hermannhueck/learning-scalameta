val projectName = "learning-scalameta"

inThisBuild(Seq(
  name := projectName,
  version := "0.1.0",
  scalaVersion := "2.13.0",
  publish / skip := true,
  libraryDependencies ++= Seq(
    "org.scalameta" %% "scalameta" % "4.2.3" withSources() withJavadoc(),
  ),
  scalacOptions ++= Seq(
    "-encoding", "UTF-8",     // source files are in UTF-8
    "-deprecation",           // warn about use of deprecated APIs
    "-unchecked",             // warn about unchecked type parameters
    "-feature",               // warn about misused language features
    // "-Ypartial-unification", // only 2.12
  ),
  addCompilerPlugin("org.scalameta" % "semanticdb-scalac" % "4.2.3" cross CrossVersion.full),
  compile / scalacOptions ++= Seq(
    "-Yrangepos",             // for semanticdb plugin; see: project/plugins.sbt
    "-Xlint",                 // enable handy linter warnings
    "-Ywarn-unused",          // warn for ununsed symbols and imports
    "-Xfatal-warnings",       // turn compiler warnings into errors, conflicts with scalafixSemanticdb
    ),
  initialCommands := "import scala.meta._",
))
