package homeworks.homework_3

import homeworks.homework_3.custom_fold.customFoldRight
import org.scalatest.flatspec.AnyFlatSpec

class custom_fold_test extends AnyFlatSpec {

  "custom fold right" should "operate with empty list" in {
    assert(customFoldRight(List.empty[Int])(100)(_ + _) === 100)
  }

  it should "operate with numeric lists" in {
    assert(customFoldRight(List(1))(0)(_ + _) === 1)
    assert(customFoldRight(List(1, 2))(0)(_ + _) === 3)
    assert(customFoldRight(List(2, 2))(0)(_ + _) === 4)
    assert(customFoldRight(List(1, 2, 3, 4, 5))(0)(_ - _) === 3)
  }

  it should "operate with strings lists" in {
    assert(customFoldRight(List("a"))("")(_ + _) === "a")
    assert(customFoldRight(List("a", "b", "c"))("")(_ + _) === "abc")
  }

}