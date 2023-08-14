package lectures.lecture_2

object classes_1 extends App {

  /** *
   * class members
   * constructors
   * */

  class Person(name: String, age: Int) {
    def shortName: String = ???
  }

  val p = new Person("Aleksey", 29) {
    override def shortName: String = ???
  }

  /**
   * abstract class
   * */

  abstract class AbstractPerson(var name: String, var age: Int) {
    def shortName: String = ???
  }

  abstract class Animal(age: Int) {
    def sound: String
  }

  class Monkey(age: Int) extends Animal(age) {
    def sound: String = "mmm"
  }

  class Tiger(age: Int) extends Animal(age) {
    def sound: String = "rrrr"
  }


  /**
   * object
   * */

  object PersonExtension {
    val DefaultAge = 100

    def person(name: String = "name", age: Int = 100): Person = new Person(name, age) {
      override def shortName: String = ???
    }
  }

  PersonExtension.person("Aleksey", 29)

  /**
   * case classes/objects
   * apply
   * unapply
   */

  case class Car(color: String, cost: BigDecimal, issueDate: Int)

  val car1 = Car("red", 1000, 2021)

  val car2 = car1.copy(cost = 900)

  println(car1 == car2.copy(cost = 1000))


  class MyCar(val color: String, val cost: BigDecimal, val issueDate: Int) {
    def copy(color: String = this.color, cost: BigDecimal = this.cost, issueDate: Int = this.issueDate): MyCar =
      new MyCar(color, cost, issueDate)
  }

  object MyCar {

    val DefaultIssueDate = 2000

    def apply(color: String, cost: BigDecimal, issueDate: Int): MyCar = new MyCar(color, cost, issueDate)

    def apply(color: String, cost: BigDecimal): MyCar = new MyCar(color, cost, DefaultIssueDate)

    def unapply(mc: MyCar): Option[(String, BigDecimal, Int)] = Some((mc.color, mc.cost, mc.issueDate))

  }

  val myCar = MyCar("red", 1000, 2021)

}