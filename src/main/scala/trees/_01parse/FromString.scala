package trees._01parse

import scala.meta._
import util._

object FromString extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  val program = """object Main extends App { print("Hello!") }"""
  val tree = program.parse[Source].get

  println
  println(tree.syntax)
  println
  println(tree.structure)
  println
  println(tree.structureLabeled)

  println
  println
  println("object Main {".parse[Source])

  println("----------------------------------------\n")
}