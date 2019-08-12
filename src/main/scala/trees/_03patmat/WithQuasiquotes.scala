package trees._03patmat

import scala.meta._

object WithQuasiquotes extends App {

  println(s"\n----- ${util.objectName(this)} ---------------")

  println
  // Quasiquotes expand at compile-time and work the same way in pattern position as in term position.
  Term.Apply(
    Term.Name("function"),
    List(Term.Name("arg1"), Term.Name("arg2"))
  ) match {
    case q"$function(..$args)" =>
      println("1 " + function)
      println("2 " + args)
  }
  // 1 function
  // 2 List(arg1, arg2)

  println
  // Use triple dollar splices ...$ to extract curried argument lists
  "function(arg1, arg2)(arg3, arg4)".parse[Term].get match {
    case q"$function(...$args)" =>
      println("1 " + function)
      println("2 " + args)
  }
  // 1 function
  // 2 List(List(arg1, arg2), List(arg3, arg4))

  println
  // Pattern matching with quasiquotes is generally discouraged because it's easy to write patterns that result in unintended match errors.
  // q"final val x = 2" match {
  //   case q"val x = 2" => // boom!
  // }
  // scala.MatchError: final val x = 2 (of class scala.meta.Defn$Val$DefnValImpl)
  //  at repl.Session$App$$anonfun$44.apply(guide.md:271)

  // To fix this pattern, we specify that the final modifier should be ignored using $_
  q"final val x = 2" match {
    case q"$_ val x = 2" => println("OK")
  }
  // OK


  println("----------------------------------------\n")
}