package lectures.lecture_4

import lectures.lecture_4.FutureMapping.User

import java.util.concurrent.Executors
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object ExecutionContextsExamples extends App {

  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)

  trait SocialService {
    def getUser(id: String)(implicit ex: ExecutionContext): Future[User] = {
      Future {
        println(Thread.currentThread().getName)
        Thread.sleep(1000)
        User(id, 100)
      }
    }

    def getUserFriends(id: String): Future[List[User]]
  }

  /** map flatMap */
  /** for comprehension */

  val ss = new SocialService {
    override def getUserFriends(id: String): Future[List[User]] = {
      Future.failed(new Exception("not implemented"))
    }
  }

  val ex = ExecutionContext.fromExecutor(Executors.newWorkStealingPool(1))

  val ex2 = ExecutionContext.fromExecutor(Executors.newWorkStealingPool(2))

  val timeS = System.currentTimeMillis()

  val user1F = ss.getUser("id1")(ex)
  val user2F = ss.getUser("id2")(ex2)
  val user3F = ss.getUser("id3")(ex)

  implicit val exx = ex

  val fres2: Future[(User, User, User)] = for {
    user1 <- user1F
    user2 <- user2F
    user3 <- user3F
  } yield (user1, user2, user3)

  Await.result(fres2, Duration.Inf)

  val timeF = System.currentTimeMillis()

  println("time took " + (timeF - timeS))


  /** single thread thread pool */

  /** Count runnable in for comprehension */

}
