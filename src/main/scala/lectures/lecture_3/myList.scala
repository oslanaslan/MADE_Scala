package lectures.lecture_3


object myList extends App {

  abstract class MyList[+T] {

    def map[U](f: T => U): MyList[U] = this match {
      case Nil() => Nil()
      case head :: tail => ::(f(head), tail.map(f))
    }

    def ++[T1 >: T](a: MyList[T1]): MyList[T1] = this match {
      case Nil() => Nil()
      case ::(h, t) => ::(h, t ++ a)
    }

    def :+[T1 >: T](a: T1): MyList[T1] = this match {
      case Nil() => ::(a, Nil())
      case _ => ::(a, this)
    }

  }

  case class Nil[T]() extends MyList[T]

  case class ::[T](head: T, tail: MyList[T]) extends MyList[T]

  val myList: MyList[Int] = ::(1, ::(2, Nil[Int]()))

  println(myList.map(_ + 1))

}


/*


abstract class List[T] {
  def headEl: T = {
    this match {
      case Nil() => ???
      case Cons(h, t) => h
    }
  }

  def map[B](f: T => B): List[B] = this match {
    case Nil() => Nil()
    case Cons(h, t) => Cons(f(h), t.map(f))
  }

  def ++[T1 >: T](a: List[T1]): List[T1] = this match {
    case Nil() => Nil()
    case Cons(h, t) => Cons(h, t ++ a)
  }
}


case class Nil[T]() extends List[T]

case class Cons[T](head: T, tail: List[T]) extends List[T]

println(Cons(1, Cons(2, Nil())).map(_ + 1))


trait F

class A extends F*/
