package lectures.lecture_8

import cats.Monad

import scala.concurrent.Future

object MonadExample extends App {

  val m = new Monad[Future] {

    override def pure[A](x: A): Future[A] = ???

    override def flatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] = ???

    // map = flatmap(x => pure(x))

    override def tailRecM[A, B](a: A)(f: A => Future[Either[A, B]]): Future[B] = ???
  }


}