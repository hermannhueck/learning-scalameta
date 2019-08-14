package trees._01parse

import scala.meta._
import util._

object FromExpressions extends App {

    println(s"\n----- ${util.objectName(this)} ---------------")

    // Stat stand for "statement"
    println
    private val stat: Stat = "a + b".parse[Stat].get
    println(stat.syntax)
    println(stat.structure)
    println(stat.structureLabeled)

    // println("a + b".parse[Source].get.structure)
    // <input>:1: error: expected class or object definition
    // a + b
    // ^

    println
    private val tpe: Type = "A with B".parse[Type].get
    println(tpe.syntax)
    println(tpe.structure)
    println(tpe.structureLabeled)

    // println("A with B".parse[Stat].get.structure)
    // <input>:1: error: end of file expected but with found
    // A with B
    //   ^

    println("----------------------------------------\n")
}

