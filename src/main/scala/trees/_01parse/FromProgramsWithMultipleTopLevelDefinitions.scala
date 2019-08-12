package trees._01parse

import scala.meta._

object FromProgramsWithMultipleTopLevelDefinitions extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  val buildSbt = """
val core = project
val cli = project.dependsOn(core)
"""

  println(buildSbt.parse[Source])
  // <input>:2: error: expected class or object definition
  // val core = project
  // ^

  println
  println(dialects.Sbt1(buildSbt).parse[Source].get.stats)
  // List(val core = project, val cli = project.dependsOn(core))

  println
  println(
    dialects.Sbt1(Input.VirtualFile("build.sbt", buildSbt)).parse[Source].get.stats
  )

  println("----------------------------------------\n")
}
