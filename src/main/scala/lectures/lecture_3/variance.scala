package lectures.lecture_3

object variance extends App {

  trait Vehicle

  trait Car extends Vehicle

  class Ford extends Car

  class Bmw extends Car

  trait Bike extends Vehicle

  class Honda extends Bike

  class Suzuki extends Bike

  //val var

  /*  type Myt = (Int, String, String)

    val a : Myt = ???*/

  type isSubtype[A, B <: A]

  type isSupertype[A, B >: A]

  lazy val is1: isSubtype[Car, Bmw] = ???

  //lazy val is2: isSupertype[Bmw, Car] = ???


  // Car       <: Vehicle
  // Bike      <: Vehicle
  // List[Car] <: List[Vehicle]
  // Container[+T] ->  Container[Car] <: Container[Vehicle]
  // Container[-T] ->  Container[Car] >: Container[Vehicle]
  // Container[T]  ->  Container[Car] != Container[Vehicle]

  val vl: List[Vehicle] = List[Car]()

  //case class Container[T](t: T)

  //val cl: Container[Vehicle] = Container[Car](new Bmw())

  //val cars: Garage[Vehicle] = new Garage[Car]
  //val moreCars = cars.add(new Honda)

/*  List[Car](new Honda)

  val c: Vehicle = new Bmw()

  val cars: List[Vehicle] = List[Car]()

  val moreCars = cars :+ new Honda

  // Car <: Vehicle => Garage[Car] ??? Garage[Vehicle]

  //val bmw7 = new Bmw
  val honda = new Honda

  case class Garage[-T](t: T)

  val hondaG: Garage[Vehicle] = new Garage[Vehicle](honda)

  // Garage[-T] -> Garage[Car] >: Garage[Vehicle]
  val carGarage: Garage[Car] = hondaG

  val _: Car = hondaG.t // Car = Bike*/

  /*
    // Car <: Vehicle => Garage[Car] ??? Garage[Vehicle]

    //val bmw7 = new Bmw
    val honda = new Honda

    val hondaG = new Garage[Vehicle](honda)

    val carGarage: Garage[Car] = hondaG

    val _: Car = hondaG.t

    class Garage2[+T](var t: T)

    //val bmw7 = new Bmw
    val honda: Honda = new Honda

    val g2: Garage2[Vehicle] = new Garage2[Bike](honda)

    g2.t = new Bmw {}

    val _: Car = g1.t


    class Garage2[T] {
      def park(t: T) = ???
      def get(): T = ???
    }*/
}
