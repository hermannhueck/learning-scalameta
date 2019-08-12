package trees._02construct

import scala.meta._

object WithConstructors extends App {
    
    println(s"\n----- ${util.objectName(this)} ---------------")

    println(Term.Apply(Term.Name("function"), List(Term.Name("argument"))))
    // function(argument)

    println
    println("function(argument)".parse[Stat].get.structure)
    // Term.Apply(Term.Name("function"), List(Term.Name("argument")))

    println("----------------------------------------\n")
}