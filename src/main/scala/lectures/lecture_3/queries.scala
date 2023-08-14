package lectures.lecture_3

import scala.collection.immutable.{HashMap, HashSet}

object queries extends App {

  val v: Seq[Int] = Vector(1, 2, 3, 4).map(_ + 1)

  v.updated(3, 4)

  //l.updated()


  val h: Map[Int, Int] = Map(1 -> 2)

  val hm: Map[Int, Int] = HashMap(1 -> 2, 2 -> 3)

  hm.foreach {
    case (k, v) =>
  }

  //f(1)

  hm(1)

  hm.get(1)

  Option(1)

  /*  sealed trait Option[T]

    case class Some[T](val t: T) extends Option[T]

    case class None[T]() extends Option[T]*/
  /*

    Option(1)
      .map(_ + 1)
      .filter(???)
      .collect(???)
  */

  val opt: Option[Int] = None

  opt.map(_ + 1) // -> None


  val s: Set[Int] = HashSet(1, 2, 3, 3)

  // Map(key -> new Object())

  println(s)

}
