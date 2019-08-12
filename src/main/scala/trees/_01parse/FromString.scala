package trees._01parse

import scala.meta._

object FromString extends App {

    println(s"\n----- ${util.objectName(this)} ---------------")

    val program = """object Main extends App { print("Hello!") }"""
    val tree = program.parse[Source].get

    println
    println(tree.syntax)
    println
    println(tree.structure)
    println

    printPretty(tree)

    println
    println("object Main {".parse[Source])

    println("----------------------------------------\n")

    def printPretty(tree: Tree): Unit = {
        import scala.util.chaining._
        "==============================" tap println
        s"productArity = ${tree.productArity}" tap println
        pairs(tree) foreach { case (name, value) => println(s"$name = $value") }
        tree.productElementNames foreach println
        "==============================" tap println
    }

    def pairs(p: Product): Seq[(String, Any)] = {
        (0 until p.productArity) map { idx =>
            (p.productElementName(idx), p.productElement(idx))
        }
    }
}