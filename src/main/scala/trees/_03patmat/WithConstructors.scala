package trees._03patmat

import scala.meta._

object WithConstructors extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  "function(arg1, arg2)".parse[Term].get match {
    case Term.Apply(function, List(arg1, arg2)) =>
      println("1 " + function)
      println("2 " + arg1)
      println("3 " + arg2)
  }
  // 1 function
  // 2 arg1
  // 3 arg2

  println("----------------------------------------\n")
}