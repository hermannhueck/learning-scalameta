package trees._05traverse

import scala.meta._

object Custom extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  println
  // Extend Traverser if you need to implement a custom tree traversal

  val traverser = new Traverser {
    override def apply(tree: Tree): Unit = tree match {
      case Pat.Var(name) =>
        println(s"stop: $name")
      case node =>
        println(s"${node.productPrefix}: $node")
        super.apply(node)
    }
  }

  // The super.apply(node) call continues the recursion,
  // so in this case we will recursively visit all nodes except children of Pat.Var nodes.

  traverser(q"val x = 2")
  // Defn.Val: val x = 2
  // stop: x
  // Lit.Int: 2

  // There is no .collect equivalent for custom traversals. To collect a value,
  // it's recommended to use List.newBuilder[T] for the type you are interested in and append values inside the apply method.

  println

  println("----------------------------------------\n")
}