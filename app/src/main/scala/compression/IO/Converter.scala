package compression.IO

import scala.annotation.tailrec

object Converter {

  private val CANONICAL_BYTE_LENGTH = 8
  private val CORRECTION = 128

  def byteToBit(byte: Byte): String = putZeroUntilLengthIs (correct(byte).toBinaryString, CANONICAL_BYTE_LENGTH)
  def bitToByte(bit: String): Byte = {Integer.parseInt(bit, 2).toByte + CORRECTION}.toByte

  private def correct(byte: Byte)(implicit corrector: Byte => Int): Int = corrector(byte)
  implicit val byteToIntCorrector: Byte => Int = _ + CORRECTION

  @tailrec
  private def putZeroUntilLengthIs(code: String, length: Int): String =
    if (code.length < length) putZeroUntilLengthIs("0" + code, length) else code


}
