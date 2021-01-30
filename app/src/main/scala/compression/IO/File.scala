package compression.IO

import java.nio.file.{Files, Paths}

object File {
  def getFile(filePath: String): Array[Byte] = Files.readAllBytes(Paths.get(filePath))
  def makeFile(filePath: String, byteArray: Array[Byte]): Unit = Files.write(Paths.get(filePath), byteArray)
}
