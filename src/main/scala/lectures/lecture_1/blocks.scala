package lectures.lecture_1

object blocks extends App {

  //if expression
  val flag = true
  val condition = true

  val result: Any = if (flag && condition) {
    0
  } else {
    "123"
  }

  var v: Int = 0

  val t: Unit = v += 1

  // if ((v+=1) == 1) {...} - в скале низя ?

  if ((v += 1) == 1) {}

  val range: Range = 1 to 20 // List(1,2,3,4,5,6,7,8...20)

  //while loop
/*  while (true) {
    println(System.currentTimeMillis())
  }*/

  //try catch
  try {
    throw new Exception("")
  } catch {
    case e: Throwable => println(e)
  }

  //for loop
  val ff: Unit = for (i <- 1 to 20) {
    println(i)
  }

  //for comprehension
  val fc = for {
    i <- 1 to 20
  } yield i

  //pattern matching
  /*fc match {
    case Seq(1, 2, 4) =>
  }*/

}
