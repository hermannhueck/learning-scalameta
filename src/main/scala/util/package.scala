import scala.meta._

package object util {

  def objectName(obj: java.lang.Object): String = {
    val name = obj.getClass.getName
    name.take(name.length()-1)
  }

  implicit class TreeOps(private val tree: Tree) {
    def structureLabeled: String = asString(tree, indent = 0).trim
  }

  def asString(tree: Tree, indent: Int): String = {
    tree match {
      case Lit(value) =>
        s"""\n${blanks(indent)}${tree.productPrefix}("$value")"""
      case Name(value) =>
        s"""\n${blanks(indent)}${tree.productPrefix}("$value")"""
      case Defn.Object(mods, name, templ) =>
        s"""\n${blanks(indent)}${tree.productPrefix}(${asString(mods, indent+1, Some("Mods"))},${asString(name, indent+1)},${asString(templ, indent+1)}\n${blanks(indent)})"""
      case wildcard: Importee.Wildcard =>
        val children = asString(tree.children, indent + 1, None)
        s"""\n${blanks(indent)}${tree.productPrefix}: Tokens(${wildcard.tokens})"""
      case Term.ApplyInfix(lhs, ops, targs, args) =>
        s"""\n${blanks(indent)}${tree.productPrefix}(${asString(lhs, indent+1)},${asString(ops, indent+1)},${asString(targs, indent+1, Some("Targs"))},\n${blanks(indent+1)}Args = List(${asString(args, indent+2, None)}\n${blanks(indent+1)})\n${blanks(indent)})"""
      case _ =>
        val children = asString(tree.children, indent + 1, None)
        s"""\n${blanks(indent)}${tree.productPrefix} = List(${children}\n${blanks(indent)})"""
    }
  }

  def asString(trees: List[Tree], indent: Int, lo: Option[String]): String = {
    if (trees.isEmpty)
      s"\n${blanks(indent)}${label(lo)}Nil"
    else {
      trees map { tree =>
        s"${asString(tree, indent)}"
      }
      }.mkString(",")
  }

  def label(labelOpt: Option[String]): String =
    labelOpt.map(str => s"$str = ").getOrElse("")

  def blanks(indent: Int): String =
    " " * indent * 4
}