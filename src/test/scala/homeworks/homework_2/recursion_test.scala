package homeworks.homework_2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class recursion_test extends AnyFlatSpec with Matchers {

  "fibonacci" should "do right calculation" in {
    recursion.fibonacci(50) shouldBe BigInt(12586269025L)

    //check stack usage
    recursion.fibonacci(100000)
  }

}
