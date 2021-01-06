package compression

import compression.structures.BinaryTree
import compression.structures.BinaryTree.Branch


object TreePOC extends App {

  val root = Branch(None)
  val tree = BinaryTree(3, root)

  println(tree)
  println(tree.toPrint(""))

  println(tree.zero.get.zero.get)

}


object FilePOC extends App {
  val testoIn = "tmp/prova.txt"
  val testoOut = "tmp/out.txt"
  val imgIn = "tmp/laurea.jpeg"
  val imgOut = "tmp/out.jpeg"

  // Files
  import java.nio.file.{Files, Paths}
  val byteArray = Files.readAllBytes(Paths.get(imgIn))


  //println(byteArray.mkString("Array(", ", ", ")"))

  import Util._
  val bitList = byteArray.map(_.toInt).map(_+128).map(e => {
    putZeroUntilLengthIs(e.toBinaryString, 8) // put zero
  })
  //.foldRight("")(_+_)

  println(bitList.mkString("Array(", ", ", ")"))

  val byteArrayAgain = bitList.map(e => Integer.parseInt(e, 2).toByte + 128).map(_.toByte)
  //println(byteArrayAgain.mkString("Array(", ", ", ")"))

  println(byteArray.size, byteArrayAgain.length)

  var eq = true

  (0 until byteArray.size).foreach(i => if (byteArray(i) != byteArrayAgain(i)) {
    eq = false
  })

  println(eq)

  Files.write(Paths.get(imgOut), byteArrayAgain)


  //  import java.nio.file.{Files, Paths}
  //  val byteArray2 = Files.readAllBytes(Paths.get("src/laureacopia.jpeg"))
  //
  //  Files.write(Paths.get("src/laurea.jpeg"), byteArray2)
}
