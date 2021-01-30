package compression

object FilePOC extends App {
  val testoIn = "tmp/prova.txt"
  val testoOut = "tmp/out.txt"
  val imgIn = "tmp/laurea.jpeg"
  val imgOut = "tmp/out.jpeg"

  // Files
  import java.nio.file.{Files, Paths}
  import compression.IO.Converter._
  import compression.IO.File._




  lazy val byteArray: Array[Byte] = Files.readAllBytes(Paths.get(imgIn))


  //println(byteArray.mkString("Array(", ", ", ")"))

  import Util._
  lazy val bitList = byteArray.map(_.toInt).map(_+128).map(byte => putZeroUntilLengthIs(byte.toBinaryString, 8))
  //.foldRight("")(_+_)

  println(bitList.mkString("Array(", ", ", ")"))

  lazy val byteArrayAgain = bitList.map(e => Integer.parseInt(e, 2).toByte + 128).map(_.toByte)
  //println(byteArrayAgain.mkString("Array(", ", ", ")"))

  println(byteArray.length, byteArrayAgain.length)

  var eq = true

  byteArray.indices.foreach(i => if (byteArray(i) != byteArrayAgain(i)) {eq = false})

  println(eq)

  Files.write(Paths.get(imgOut), byteArrayAgain)


  //  import java.nio.file.{Files, Paths}
  //  val byteArray2 = Files.readAllBytes(Paths.get("src/laureacopia.jpeg"))
  //
  //  Files.write(Paths.get("src/laurea.jpeg"), byteArray2)
}
