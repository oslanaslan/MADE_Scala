package lectures.lecture_1

object func extends App {

  //curried function
  val foo2: Int => String => String = i => { s =>
    i + s
  }

  //partially applied function
  val fres: String => String = foo2(1)

  val res: String = null //Option

  //  val res2: Option[String] = ??? //Option

  val uu: Unit = {
    "123"
  }

}
