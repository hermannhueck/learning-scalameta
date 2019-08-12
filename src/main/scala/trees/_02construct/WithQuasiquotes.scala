package trees._02construct

import scala.meta._

object WithQuasiquotes extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  println
  println(q"function(argument)".structure)

  println
  println(
    q"""
  object Example extends App {
    println(42)
  }
  """.structure)

  // It's important to keep in mind that quasiquotes expand at compile-time into the same program
  // as if you had written normal constructors by hand.
  // This means for example that formatting details or comments are not preserved

  println
  println(q"function  (    argument   ) // comment")
  // function(argument)

  println
  val left = q"Left()"
  // left: Term.Apply = Term.Apply(Term.Name("Left"), List())
  val right = q"Right()"
  // right: Term.Apply = Term.Apply(Term.Name("Right"), List())
  println(q"$left + $right")
  // Left() + Right()

  println
  // A list of trees can be inserted into a quasiquote with double dots ..$
  val arguments = List(q"arg1", q"arg2")
  // arguments: List[Term.Name] = List(Term.Name("arg1"), Term.Name("arg2"))
  println(q"function(..$arguments)")
  // function(arg1, arg2)

  println
  // A curried argument argument lists can be inserted into a quasiquotes with triple dots ...$
  val arguments2 = List(q"arg3", q"arg4")
  // arguments2: List[Term.Name] = List(Term.Name("arg3"), Term.Name("arg4"))
  val allArguments = List(arguments, arguments2)
  // allArguments: List[List[Term.Name]] = List(
  //   List(Term.Name("arg1"), Term.Name("arg2")),
  //   List(Term.Name("arg3"), Term.Name("arg4"))
  // )
  println(q"function(...$allArguments)")
  // function(arg1, arg2)(arg3, arg4)

  println
  // A common mistake is to splice an empty type parameter list into type application nodes . Imagine we have a list of type arguments that happens to be empty
  val typeArguments = List.empty[Type]
  // If we directly splice the lists into a type application we get a cryptic error message "invariant failed"
  // q"function[..$typeArguments]()"
  // org.scalameta.invariants.InvariantFailedException: invariant failed:
  // when verifying targs.!=(null).&&(targs.nonEmpty)
  // found that targs.nonEmpty is false
  // where targs = List()
  //  at org.scalameta.invariants.InvariantFailedException$.raise(Exceptions.scala:15)
  //  at scala.meta.Term$ApplyType$.internal$49(Trees.scala:82)
  //  at scala.meta.Term$ApplyType$.apply(Trees.scala:82)
  //  at repl.Session$App$$anonfun$39.apply(guide.md:220)
  //  at repl.Session$App$$anonfun$39.apply(guide.md:220)

  // The quasiquote above is equivalent to calling the normal constructor Type.ApplyType(.., typeArguments).
  // Scalameta trees perform strict runtime validation for invariants such as "type application arguments must be non-empty".
  // To fix this problem, guard the splice against the length of the list
  println(
    (if (typeArguments.isEmpty)
      q"function()"
    else
      q"function[..$typeArguments]()"
    ).structure
  )
  // Term.Apply(Term.Name("function"), List())

  println("----------------------------------------\n")
}