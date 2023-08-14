package lectures.lecture_2

import scala.annotation.tailrec

object recursion_2 extends App {

  /**
   * factorial n! = 1 * 2 * 3 * 4 * 5 * 6 * ... * n
   * inner functions
   */

  def fact(n: Int): Int = {
    var _n = 1
    var i = 2

    while (i <= n) {
      _n = _n * i
      i = i * i
    }
    _n
  }

  ///println(fact(10) == fact2(10))

  /**
   * tailrec
   */

  def fact2(n: BigInt): BigInt = if ( n <= 1 ) 1 else fact2(n-1) * n

  //fact2(100000)

  def fact3(n: BigInt): BigInt = {
    @tailrec
    def loop(n: BigInt, acc: BigInt): BigInt = {
      if (n <= 1) acc
      else loop(n - 1, acc * n)
    }
    loop(n, 1)
  }

  println(fact3(100000))

}
