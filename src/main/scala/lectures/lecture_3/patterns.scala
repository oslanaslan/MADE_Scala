package lectures.lecture_3

object patterns extends App {

  case class Person(name: String, age: Int)

  //val listP: List[Person] = List(1,2, 3,4,5)

  val list: List[Int] = List(1, 2, 3)

  val listS: Seq[Int] = Seq(1, 2, 3)

  val r: List[Int] = 1 :: 2 :: 3 :: Nil

  val ni: List[Int] = Nil

  val ll: List[List[Int]] = List[List[Int]](List(1), List(2), List(1, 2))

  ll match {
    /*    case Nil => println("nil")

        case Seq(first, _*) => println("seq")

        case List(a) => println("list")

        case x :: xs => println("cons " + xs); xs*/

    case x :: y :: List(xs, ys) :: zs => ()
  }

  list match {
    case List(a, b, c) => List(a + 1, b + 1, c + 1)
  }

}
