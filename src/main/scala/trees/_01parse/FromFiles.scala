package trees._01parse

import scala.meta._
import util._

object FromFiles extends App {

    println(s"\n----- ${util.objectName(this)} ---------------")

    val path = java.nio.file.Paths.get("src/main/scala/trees/_01parse", "FromFiles.scala")
    val bytes = java.nio.file.Files.readAllBytes(path)
    val text = new String(bytes, "UTF-8")
    val input = Input.VirtualFile(path.toString, text)
    val exampleTree = input.parse[Source].get       // comment here is retained while parsing
    
    print(exampleTree.syntax)
    println
    print(exampleTree.structureLabeled)
    println
    println

    println(Input.VirtualFile("example.scala", "object Main {").parse[Source])

    println("----------------------------------------\n")
}
