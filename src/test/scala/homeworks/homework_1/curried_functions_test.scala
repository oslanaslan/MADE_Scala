package homeworks.homework_1

/** *
 * Напишите каррированую функцию calculate вычисления арифмитического выражения
 * x^2 + y^2 + 2*x*y
 * x первый аргумент
 * у второй аргумент
 *
 * Раскоментируйте тест и проверьте результат
 * */

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class curried_functions_test extends AnyFlatSpec with Matchers {

  "calculate" should "do right calculation" in {
    1 shouldBe 1

    /**
     * val partiallyApplied = curried_functions.calculate(1)
     * // 1 + 4 + 4 = 9
     * partiallyApplied(2) shouldBe 9
     * */
  }

}