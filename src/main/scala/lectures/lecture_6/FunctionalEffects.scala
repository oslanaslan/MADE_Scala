package lectures.lecture_6

import scala.io.StdIn

object FunctionalEffects extends App {

  val greet = {
    println("Как тебя зовут?")
    val name = StdIn.readLine()
    println(s"Привет, $name")
  }

  val askForAge = {
    println("Сколько тебе лет?")
    val age = StdIn.readInt()
    if (age > 18) println("Можешь проходить")
    else println("Ты еще не можешь пройти")
  }

  //greet : ???

  def greetAndAskForAge(): Unit = {
    greet
    askForAge
  }
}

