package lectures.lecture_3

object aggregations extends App {

  /** fold
   * def foldLeft[B](z: B)(op: (B, A) => B): B
   * */

  val list = List(1, 2, 3)

  val sum = list.foldLeft(0) {
    case (z, elem) => z + elem
  }

  val sum2 = list.sum

  val sl = List(1, 2, 3).foldLeft(0) {
    case (z, elem) => z - elem
  }

  println(sl)

  ((0 - 1) - 2) - 3

  val sr = List(1, 2, 3).foldRight(0) {
    case (z, elem) => z - elem
  }

  (1 - (2 - (3 - 0)))

  println(sr)

  /** reduce */

  List[Int](1).reduce[Int] {
    case (z, elem) => z + elem
  }

}