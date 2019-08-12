package trees._01parse

import scala.meta._

object FromExpressions extends App {

    println(s"\n----- ${util.objectName(this)} ---------------")

    // Stat stand for "statement"
    println("a + b".parse[Stat].get.structure)
    
    // println("a + b".parse[Source].get.structure)
    // <input>:1: error: expected class or object definition
    // a + b
    // ^

    println("A with B".parse[Type].get.structure)

    // println("A with B".parse[Stat].get.structure)
    // <input>:1: error: end of file expected but with found
    // A with B
    //   ^

    println("----------------------------------------\n")
}

