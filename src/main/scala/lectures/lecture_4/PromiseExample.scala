package lectures.lecture_4

import scala.concurrent.{Future, Promise}

object PromiseExample extends App {

  trait Callback[A] {
    def onSuccess(payload: A): Unit
    def onError(ex: Throwable): Unit
  }

  trait SocialServiceAsync {
    def getUserInfoAsync(userId: Long, cb: Callback[String]): Unit
  }

  val ss: SocialServiceAsync = ???

  def getUserInfoF: Future[String] = {
    val p: Promise[String] = Promise[String]()

    ss.getUserInfoAsync(1, new Callback[String] {
      override def onSuccess(payload: String): Unit = p.success(payload)
      override def onError(ex: Throwable): Unit = p.failure(ex)
    })

    p.future
  }

}