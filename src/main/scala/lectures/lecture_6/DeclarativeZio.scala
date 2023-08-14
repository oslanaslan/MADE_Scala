package lectures.lecture_6

import lectures.lecture_6.DeclarativeZio.ZIO
import lectures.lecture_6.DeclarativeZio.ZIO.FlatMap

import java.time.ZoneId

object DeclarativeZio {

  // def main: IO
  // IO

  trait ZIO[+A] {
    self =>

    def run(callback: A => Unit): Unit

    def map[B](f: A => B): ZIO[B] = ZIO.Map(self, f)

    def flatMap[B](f: A => ZIO[B]): ZIO[B] = ZIO.FlatMap(self, f)
  }

  object ZIO {

    def succeedNow[A](value: => A): ZIO[A] = ZIO.Succeed(value)

    case class Succeed[A](value: A) extends ZIO[A] {
      override def run(callback: A => Unit): Unit = callback(value)
    }

    case class Map[A, B](zio: ZIO[A], f: A => B) extends ZIO[B] {
      override def run(callback: B => Unit): Unit =
        zio.flatMap[B](a => ZIO.succeedNow(f(a))).run(callback)
    }

    case class FlatMap[A, B](zio: ZIO[A], f: A => ZIO[B]) extends ZIO[B] {
      override def run(callback: B => Unit): Unit =
        zio.run { a =>
          f(a).run(callback)
        }
    }

  }

}

trait ZIOApp {

  def run: ZIO[Any]

  def main(args: Array[String]): Unit =
    println(Map(1 -> 2, 1 -> 4, 1 -> 5)(1))

}

object SucceedNow extends ZIOApp {

  override def run: ZIO[Any] = ZIO.succeedNow(1)

}


object ForComprehension extends ZIOApp {

  val zioApp: ZIO[Int] = for {
    a <- ZIO.succeedNow[Int](1)
    b <- ZIO.succeedNow[Int](2)
  } yield a + b

  override def run: ZIO[Any] = zioApp

}

