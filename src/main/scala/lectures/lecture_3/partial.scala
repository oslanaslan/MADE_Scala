package lectures.lecture_3

object partial extends App {

  val list: List[Int] = List(1, 2, 3)

  println(list.collect {
    case x if (x + 1) % 2 == 0 => x + 1
  })

  val f: PartialFunction[Int, Int] = {
    case x if (x + 1) % 2 == 0 => x + 1
  }

  println(list.collect(f))

  //if(f.isDefinedAt(2)) println(f(2)) else println("not")

}
