package lectures.lecture_4

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object FutureMapping extends App {

  case class User(id: String, age: Int)

  trait SocialService {
    def getUser(id: String): Future[User] = {
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

  def f(s: String)(s2: String)(s3: String) = ???

  val fres: Future[(User, User, User)] = for {
    user1 <- ss.getUser("id1")
    user2 <- ss.getUser("id2")
    user3 <- ss.getUser("id3")
  } yield (user1, user2, user3)

  /** sequence */


  Await.result(fres, Duration.Inf)

  //println("time took " + (timeF1 - timeS1))

  val listF: List[Future[User]] = List(ss.getUser("id1"), ss.getUser("id2"), ss.getUser("id3"))

  val seq: Future[List[User]] = Future.sequence(List(ss.getUser("id1"), ss.getUser("id2"), ss.getUser("id3")))

  Await.result(seq, Duration.Inf)

  /** traverse */

  val tr: Future[List[User]] = Future.traverse(List("id1", "id2", "id3"))(ss.getUser)

  /** parallel start, combining */

  /** future eager */

    val timeS = System.currentTimeMillis()

    val user1F = ss.getUser("id1")
    val user2F = ss.getUser("id2")
    val user3F = ss.getUser("id3")

    val fres2: Future[(User, User, User)] = for {
      user1 <- user1F
      user2 <- user2F
      user3 <- user3F
    } yield (user1, user2, user3)

    Await.result(fres2, Duration.Inf)

    val timeF = System.currentTimeMillis()

    println("time took " + (timeF - timeS))




}