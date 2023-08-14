package lectures.lecture_4

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object FutureConstructors extends App {

  /** successful ~ unit */
  val f1: Future[String] = Future.successful {
    Thread.sleep(1000)
    println("thread " + Thread.currentThread().getName)
    "user1"
  }

  val f2: Future[String] = Future.apply {
    Thread.sleep(1000)
    println("thread " + Thread.currentThread().getName)
    "user1"
  }

  /** await */
  val res = Await.result(f1, Duration.Inf)

  println(res)
}
