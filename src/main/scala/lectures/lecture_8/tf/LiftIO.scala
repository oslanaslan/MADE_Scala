package lectures.lecture_8.tf

import cats.effect.{IO, LiftIO}

import scala.concurrent.Future

object LiftIO {

  type MyEffect[A] = Future[Either[Throwable, A]]

  implicit def myEffectLiftIO: LiftIO[MyEffect] =
    new LiftIO[MyEffect] {
      override def liftIO[A](ioa: IO[A]): MyEffect[A] = {
        ioa.attempt.unsafeToFuture()
      }
    }
}
