package compression

import compression.IO.Converter._
import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestConverter extends AnyFunSuite {

  test(""){
    val BYTE: Byte = 0.toByte
    val BIT = "10000000"

    assert(byteToBit(BYTE) equals BIT)

  }

}
