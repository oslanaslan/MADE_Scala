package lectures.lecture_4

import lectures.lecture_4.FutureMapping.{User, ss}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Try

object FutureRecover extends App {

  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)

  /** не нарушать контракт */

  /** recover */

  val fres3: Future[List[User]] = for {
    user1 <- ss.getUser("id1")
    friends <- ss.getUserFriends(user1.id).recover {
      case th =>
        println("recover " + th.getMessage)
        List()
    }

    friends <- ss.getUserFriends(user1.id).recoverWith {
      case th =>
        println("recover " + th.getMessage)
        ss.getUserFriends(user1.id)
    }
  } yield friends

  val res = Await.ready(fres3, Duration.Inf)

  println(res)

}


