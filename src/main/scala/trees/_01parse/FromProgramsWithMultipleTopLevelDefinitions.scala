package trees._01parse

import scala.meta._
import util._

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
  private val stats1: List[Stat] = dialects.Sbt1(buildSbt).parse[Source].get.stats
  println(stats1)
  // List(val core = project, val cli = project.dependsOn(core))
  println
  println(stats1.structure)
  println
  stats1.map(_.structureLabeled) foreach println

  println
  println(
    dialects.Sbt1(Input.VirtualFile("build.sbt", buildSbt)).parse[Source].get.stats
  )

  println("----------------------------------------\n")
}
