val projectName = "learning-scalameta"

inThisBuild(
  Seq(
    name := projectName,
    scalaVersion := "2.12.8",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalameta" %% "scalameta" % "4.2.0", // for rules 'MissingFinal' and 'Disable'
    ),
    publish / skip := true,
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",     // source files are in UTF-8
      "-deprecation",           // warn about use of deprecated APIs
      "-unchecked",             // warn about unchecked type parameters
      "-feature",               // warn about misused language features
      "-Xlint",                 // enable handy linter warnings
      "-Ypartial-unification",
      "-Ywarn-unused",
      // "-Xfatal-warnings",        // turn compiler warnings into errors, conflicts with scalafixSemanticdb
      ),
  )
)
