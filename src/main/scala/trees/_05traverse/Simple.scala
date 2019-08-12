package trees._05traverse

import scala.meta._
import scala.util.chaining._

object Simple extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  println
  // Use .traverse to visit every tree node and perform a side-effect, similarly to .foreach
  q"val x = 2".traverse {
    case node =>
      println(s"${node.productPrefix}: $node")
  }
  // Defn.Val: val x = 2
  // Pat.Var: x
  // Term.Name: x
  // Lit.Int: 2

  println
  // Use .collect to visit every tree node and collect a value instead of performing a side-effect
  q"val x = 2".collect {
    case node => node.productPrefix -> node.toString
  } tap println
  // res33: List[(String, String)] = List(
  //   ("Defn.Val", "val x = 2"),
  //   ("Pat.Var", "x"),
  //   ("Term.Name", "x"),
  //   ("Lit.Int", "2")
  // )

  // The methods .traverse and .collect don't support customizing the recursion.
  // For more fine-grained control over which tree nodes to visit implement a custom Traverser.

  println

  println("----------------------------------------\n")
}