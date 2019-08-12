package object util {

  def objectName(obj: java.lang.Object): String = {
    val name = obj.getClass().getName()
    name.take(name.length()-1)
  }
}