package homeworks.homework_3

import homeworks.homework_3.list.getItems
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class list_test extends AnyFlatSpec with Matchers {

  "getItems" should "work" in {
    getItems(List(1, 2, 3), 'A') shouldBe List("Apples")
    getItems(List(1, 2, 3), 'C') shouldBe List("Cherries")
    getItems(List(1, 2, 3), 'B') shouldBe List("Bananas", "Butter")
    getItems(List(1), 'B') shouldBe List("Bananas")
    getItems(List(2), 'B') shouldBe List("Butter")
    getItems(List(3, 4), 'B') shouldBe Nil
    getItems(List(1, 2, 3), 'M') shouldBe List("Milk")
    getItems(List(1, 2, 3), 'Y') shouldBe List("Yogurt")
    getItems(List(1, 2, 3), 'S') shouldBe List("Salt", "Sugar")
    getItems(List(1, 2), 'S') shouldBe List("Salt")
    getItems(List(1, 3), 'S') shouldBe List("Sugar")
    getItems(List(4, 5, 6), 'B') shouldBe Nil
  }

}
