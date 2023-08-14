package homeworks.homework_2

import homeworks.homework_2.pattern_matching._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class pattern_matching_test extends AnyFlatSpec with Matchers {

  "findMyKnife" should "behave correctly" in {
    findMyKnife(StuffBox()) shouldBe false
    findMyKnife(StuffBox(junk = List(Book("Programming in Brainfuck"), Knife))) shouldBe true
    findMyKnife(StuffBox(junk = List(Knife))) shouldBe true
  }

  "sortJunk" should "behave correctly" in {
    val stuff = List(
      Boots("Adidas", 42),
      Book("Game of thrones 1", true),
      Boots("Reebok", 42),
      Watches("Nautica", 10000),
      Boots("Converse", 43),
      Boots("Converse", 43),
      Watches("Breitling", 100000),
      Watches("Electronika", 1000),
      Boots("Skorohod", 50),
      Boots("Noname", 45),
      Watches("Zarya", 15000),
      Book("Game of thrones 3"),
      Book("Game of thrones 4"),
      Watches("Casio", 5000),
      Watches("Citizen", 5000),
      Book("Game of thrones 2", true),
      Book("Idiot", true),
      Book("Lubovnaya lubov"),
      Knife
    )
    val sortedJunk = sortJunk(stuff)
    sortedJunk.books should contain theSameElementsAs Vector(
      Book("Game of thrones 1", true),
      Book("Game of thrones 2", true),
      Book("Idiot", true),
    )
    sortedJunk.boots should contain theSameElementsAs Vector(
      Boots("Adidas", 42),
      Boots("Converse", 43),
      Boots("Converse", 43),
    )
    sortedJunk.watches should contain theSameElementsAs Vector(
      Watches("Nautica", 10000),
      Watches("Breitling", 100000),
      Watches("Zarya", 15000),
      Watches("Casio", 5000),
      Watches("Citizen", 5000),
    )
    sortedJunk.junk should contain theSameElementsAs Vector(
      Boots("Reebok", 42),
      Watches("Electronika", 1000),
      Boots("Skorohod", 50),
      Boots("Noname", 45),
      Knife,
      Book("Game of thrones 3"),
      Book("Game of thrones 4"),
      Book("Lubovnaya lubov")
    )
  }
}