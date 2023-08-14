package lectures.lecture_3

object combinators extends App {

  /** map */
  /** filter */
  /** flatMap */

  val list: List[Int] = List(1, 2, 3)

  println(list.map(_ + 1))

  println(list.filter(_ % 2 == 0))

  println(list.map(_ + 1).filter(_ % 2 == 0))

  val fl: List[Int] = list.map(List(_)).flatten

  println(fl)

  List(1, 2, 3).map(x => List(x, x, x)).flatten

  List(1, 2, 3).flatMap(x => List(x, x, x))

  //(1,2,3)

  //for comprehension

  val nl = for {
    x <- list if x % 2 == 0
    y <- list
  } yield (x, y)

  println(nl)

  list.foreach(println)

  //custom for comprehension

  class M[T](val t: T) {
    def map[U](f: T => U): M[U] = new M(f(this.t))

    def flatMap[U](f: T => M[U]) = new M(f(this.t))
  }

  val mm = new M(1)

  for {
    m <- mm
    m <- mm
  } yield m

}