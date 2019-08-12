package trees._04compare

import scala.meta._
import scala.util.chaining._

object ForEquality extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  // Scalameta trees use reference equality by default, which may result in surprising behavior.

  println
  // A common mistake is to use == between parsed syntax trees and quasiquotes
  "true".parse[Term].get == q"true" tap println
  // res26: Boolean = false

  // Comparing trees by == is the same as comparing them with eq. Even identical quasiquotes produce different references
  q"true" == q"true" tap println
  // res27: Boolean = false

  // Equality checks with == will only return true when the reference is the same.
  println { val treeReference = q"true"
    treeReference == treeReference
  }
  // res28: Boolean = true

  // The idiomatic way to compare trees for structural equality is to use pattern matching
  q"true" match { case q"true" => println("YAY!") }
  // YAY!

  // If you can't use pattern matching to compare trees by structural equality, you can use .structure
  println( q"true".structure == q"true".structure )
  // res30: Boolean = true

  // The .structure method produces large strings for large programs, which may become prohibitively slow.
  // The Scalameta contrib module contains a more efficient isEqual helper method to compare trees structurally.
  import scala.meta.contrib._
  println( q"true".isEqual(q"true") )
  // res31: Boolean = true

  println

  println("----------------------------------------\n")
}