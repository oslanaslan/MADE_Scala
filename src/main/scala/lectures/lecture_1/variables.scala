package lectures.lecture_1

object variables extends App {

  val i = 0 // final
  var j = 0 // mutable

  //val i = 0

  lazy val k1 = {
    throw new Exception("boom")
  }

  k1

  // val k2 = throw new Exception("boom")
}
