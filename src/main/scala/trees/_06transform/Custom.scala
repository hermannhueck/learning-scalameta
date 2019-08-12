package trees._06transform

import scala.meta._

object Custom extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  println
  // Extend Transformer if you need to implement a custom tree transformation

  val transformer = new Transformer {
    override def apply(tree: Tree): Tree = tree match {
      case name @ Term.Name("b") => q"function($name)"
      case node => super.apply(node)
    }
  }

  // By avoiding the call to super.transform in the first case, we prevent a stack overflow.

  println(
    transformer(q"a + b")
  )
  // a + function(b)

  println

  println("----------------------------------------\n")
}