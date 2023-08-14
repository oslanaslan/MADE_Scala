package homeworks.homework_3

import homeworks.homework_3.max.maxima
import org.scalatest.flatspec.AnyFlatSpec

class maxima_test extends AnyFlatSpec {

  "maxima selector" should "operate with empty list" in {
    assert(maxima(Nil) === Nil)
  }

  it should "operate with small lists" in {
    assert(maxima(List(1)) === Nil)
    assert(maxima(List(1, 2)) === Nil)
    assert(maxima(List(2, 2)) === Nil)
  }

  it should "operate with strict maxima" in {
    assert(maxima(List(1, 2, 1)) === List(2))
    assert(maxima(List(1, 2, 1, 10, 8)) === List(2, 10))
    assert(maxima(List(-2, -1, -2, -3)) === List(-1))
  }

  it should "operate with non-strict maxima" in {
    assert(maxima(List(0, 0, 0, 0)) === List(0, 0))
    assert(maxima(List(1, 2, 2, 1, 2, 1)) === List(2, 2, 2))
    assert(maxima(List(-1, -2, -2, -1, -2, -1)) === List(-1))
  }
}
