package compression

import compression.structures.BinaryTree
import compression.structures.BinaryTree.Branch


object Application extends App {

  val root = Branch(None)
  val tree = BinaryTree(2, root)

  println(tree)
  println(tree.toPrint(""))

  println(tree.zero.get.zero.get)


//  // Files
//  import java.nio.file.{Files, Paths}
//  val byteArray = Files.readAllBytes(Paths.get("src/prova.txt"))
//
//  Files.write(Paths.get("src/out.txt"), byteArray)
//
//  import java.nio.file.{Files, Paths}
//  val byteArray2 = Files.readAllBytes(Paths.get("src/laureacopia.jpeg"))
//
//  Files.write(Paths.get("src/laurea.jpeg"), byteArray2)


}
