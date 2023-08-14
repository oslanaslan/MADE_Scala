package lectures.lecture_2

import lectures.lecture_2.classes_1.{Car, MyCar}

object pattern_matching_3 extends App {

  /** *
   * defaults
   */

  val c: Any = Car("blue", 1000, 2021)

  c match {
    case Car("blue", cost, _) => println("Car 1 cost=" + cost)
    case Car(color, cost, 2021) => println("Car 2 cost=" + cost)
    case _ => println("Empty case")
  }

  /** *
   * unapply cc
   */

  val mc: MyCar = MyCar("blue", 1000, 202)

  mc match {
    case mci@MyCar("blue", cost, _) => println("My car id" + mc + " My car id " + mci)
    case _ => println("Empty case")
  }

  /** *
   * literals
   */

  /** *
   * guards
   */

  c match {
    case Car("blue", cost, _) if cost % 2 == 0 && true => println("Car 1 cost=" + cost)
    case Car(color, cost, 2021) => println("Car 2 cost=" + cost)
    case _ => println("Empty case")
  }

  /** *
   * aliases
   */

  c match {
    case c@Car("blue", cc@cost, _) if cost % 2 == 0 => println("Car 1 cost=" + cc)
    case Car(color, cost, 2021) => println("Car 2 cost=" + cost)
    case _ => println("Empty case")
  }


  c match {
    case _: Car => println(c)
  }

  /** *
   * alternatives
   */

  c match {
    case cc@(_: Car | _: MyCar) => println(cc)
  }

}