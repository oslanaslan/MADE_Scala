package lectures.lecture_2


object pure_5 extends App {

  /**
   * pure functions
   * */

  def yy(x: Int): Int = x * x

  //println(y(2) + y(y(1)))

  //println((2*2) + ((1*1)*(1*1)))

  def yprint(x: Int) = {
    println("x=" + x)
    x * x
  }

  //println(yprint(2) + yprint(yprint(1)))

  val y = yprint(1)

  val yup = (y, y)

  println("____")

  val yup2 = (yprint(1), yprint(1))

  println(yup == yup2)


  //HW random generator
  Math.random()

  def rr(x: Int): Int = x * x

}