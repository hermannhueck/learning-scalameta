package trees._06transform

import scala.meta._

object Simple extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  println
  // Use .transform to visit every tree node and transform interesting tree nodes.
  println(
    q"val x = 2".transform { case q"2" => q"42" }
  )
  // val x = 42

  println
  // The contract of .transform is that it will recursively visit all tree nodes, including the transformed trees.
  // Due to this behavior, a common mistake is to introduce infinite recursion in .transform
  q"a + b".transform {
    case name @ Term.Name("b") => q"function($name)"
  }.toString
  // [error] java.lang.StackOverflowError
  // at scala.meta.transversers.Api$XtensionCollectionLikeUI$transformer$2$.apply(Api.scala:10)
  // at scala.meta.transversers.Transformer.apply(Transformer.scala:4)

  // The best solution to fix this problem is to implement a custom transformer to gain fine-grained control over the recursion.

  println

  println("----------------------------------------\n")
}