package lectures.lecture_6

import lectures.lecture_6.DeclarativeEncoding.Console.succeed

import scala.annotation.tailrec
import scala.io.StdIn

object DeclarativeEncoding extends App {


  /**
   * 1. Объявить декларативную модель Console
   */

  sealed trait Console[A]

  case class Println[A](message: String, rest: Console[A]) extends Console[A]

  case class ReadLine[A](f: String => Console[A]) extends Console[A]

  case class Return[A](value: () => A) extends Console[A]

  case class Draw[A](value: () => A) extends Console[A]


  /**
   * 2. Написать конструкторы
   */

  object Console {

    def succeed[A](value: => A): Console[A] = Return(() => value) //pure

    def printLine(string: String): Console[Unit] = Println(string, succeed())

    def readLine(): Console[String] = ReadLine(string => succeed(string))

  }

  import ConsoleOps._

  /**
   * 3. Описать желаемую программу с помощью нашей модели
   */

  /*
  *
  val greet = {
    println("Как тебя зовут?")
    val name = StdIn.readLine()
    println(s"Привет, $name")
  }
  * */

  val greet: Console[Unit] = Println("Как тебя зовут?",
    ReadLine(name => Println(s"Привет, $name",
      Return(() => ()))
    )
  )

  val askForName: Console[Unit] = {
    for {
      _ <- Console.printLine("Как тебя зовут?")
      name <- Console.readLine()
      _ <- Console.printLine(s"Привет, $name")
    } yield ()
  }

  /*
  *
  val askForAge = {
    println("Сколько тебе лет?")
    val age = StdIn.readInt()
    if (age > 18) println("Можешь проходить")
    else println("Ты еще не можешь пройти")
  }
  *
  * */

  val askForAge: Console[Unit] = {
    for {
      _ <- Console.printLine("Сколько тебе лет?")
      age <- Console.readLine()
      _ <- if (age.toInt > 18) Console.printLine("Можешь проходить") else
        Console.printLine("Ты еще не можешь пройти")
    } yield ()
  }

  val composite = for {
    _ <- askForName
    _ <- askForAge
  } yield ()

  /**
   * 4. Написать операторы
   *
   */

  object ConsoleOps {
    implicit class ops[A](cons: Console[A]) {

      def map[B](f: A => B): Console[B] = flatMap(a => succeed(f(a)))

      def flatMap[B](f: A => Console[B]): Console[B] = cons match {
        case Println(message, rest) => Println(message, rest.flatMap(f))
        case ReadLine(func) => ReadLine(message => func(message).flatMap(f))
        case Return(value) => f(value())
      }
    }

  }

  /**
   * 5. Написать интерпретатор для нашей ф-циональной модели
   */

  @tailrec
  def interpreter[A](console: Console[A]): Unit = console match {
    case Println(message, rest) =>
      println(message)
      interpreter(rest)
    case ReadLine(f) => interpreter(f(StdIn.readLine()))
    case Return(value) => value()
  }

  @tailrec
  def describe[A](console: Console[A]): Unit = console match {
    case Println(message, rest) =>
      println(message)
      describe(rest)
    case ReadLine(f) => describe(f(StdIn.readLine()))
    case Return(value) => value()
  }

  val someComp = succeed("42");

  val c = for {
    age <- Console.readLine()
    _ <- Console.printLine("x = " + age)
  } yield ()

  describe(c)
}
