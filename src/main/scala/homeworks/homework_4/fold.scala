package homeworks.homework_4

import scala.concurrent.{ExecutionContext, Future}

object fold {

  /**
   * Реализуйте функцию, которая выполнит свертку (fold) входящей последовательности из Future,
   * используя переданный комбинатор и начальное значение для свертки.
   * Если какая-либо из исходных Future зафейлилась, то должна вернуться ошибка от нее
   * */
  def foldF[A, B](in: Seq[Future[A]], zero: B, op: (B, A) => B)
                 (implicit executionContext: ExecutionContext): Future[B] = ???

  /**
   * Реализуйте функцию, которая выполнит свертку (fold) входящей последовательности из Future,
   * используя переданный асинхронный комбинатор и начальное значение для свертки.
   * Если какая-либо из исходных Future зафейлилась, то должна вернуться ошибка от нее.
   * Если комбинатор зафейлился, то должна вернуться ошибка от него.
   * */
  def flatFoldF[A, B](in: Seq[Future[A]], zero: B, op: (B, A) => Future[B])
                     (implicit executionContext: ExecutionContext): Future[B] = ???

}
