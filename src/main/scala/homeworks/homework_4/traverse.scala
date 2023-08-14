package homeworks.homework_4

import scala.concurrent.{ExecutionContext, Future}

object traverse {

  /**
   * Реализуйте traverse c помощью метода Future.sequence
   */
  def traverse[A, B](in: List[A])(fn: A => Future[B])
                    (implicit ex: ExecutionContext): Future[List[B]] = ???
}
