package compression

import scala.annotation.tailrec

object Util {

  @tailrec
  def putZeroUntilLengthIs(code: String, length: Int): String =
    if (code.length < length) putZeroUntilLengthIs("0" + code, length) else code

}

object Test extends App {
  import Util._
  println(
    putZeroUntilLengthIs("1", 8)
  )

}
