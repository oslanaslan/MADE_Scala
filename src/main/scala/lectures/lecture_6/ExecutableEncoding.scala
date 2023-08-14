package lectures.lecture_6

import lectures.lecture_6.ExecutableEncoding.Console.succeed

import scala.io.StdIn

object ExecutableEncoding extends App {


  /**
   * 1. Объявить исполняемую модель Console
   */

  case class Console[A](run: () => A)

  /**
   * 2. Объявить конструкторы
   */

  object Console {

    def succeed[A](value: => A): Console[A] = Console(() => value)

    def printLine(string: String): Console[Unit] = Console(() => println(string))

    def readLine(): Console[String] = Console(() => StdIn.readLine())

  }

  /**
   * 3. Описать желаемую программу с помощью нашей модель
   */

  /**
   * 4. Написать операторы
   */

  object ConsoleOps {
    implicit class ops[A](cons: Console[A]) {

      def map[B](f: A => B): Console[B] = flatMap(a => succeed(f(a)))

      def flatMap[B](f: A => Console[B]): Console[B] = f(cons.run())
    }

  }

  import ConsoleOps._

  val askForName: Console[Unit] = {
    for {
      _ <- Console.printLine("Как тебя зовут?")
      name <- Console.readLine()
      _ <- Console.printLine(s"Привет, $name")
    } yield ()
  }

  askForName.run
}
