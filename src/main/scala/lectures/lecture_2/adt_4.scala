package lectures.lecture_2

object adt_4 extends App {


  /**
   * tuples
   * */

  val tup: Tuple3[Int, String, Boolean] = Tuple3(1, "str", true)

  val tup2: (String, Boolean, Int) = ("str", true, 1)

  /**
   * either
   * */

  val left: Either[String, Boolean] = Left("left")

  val right: Either[String, Boolean] = Right(true)

  left match {
    case _: Left[String, Boolean] => println("left")
    case _: Right[String, Boolean] => println("right")
  }

  def doSomething: Either[Throwable, String] = ???


  /**
   * sealed
   * */

  sealed trait Sum
  case object LeftS extends Sum
  case object RightS extends Sum

  val s: Sum = ???

  s match {
    case LeftS => ()
    case RightS => ()
  }

  /**
   * case
   * */

}
