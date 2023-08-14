package homeworks.homework_4

import scala.concurrent.{ExecutionContext, Future}

object par_traverse {

  /**
   * Реализуйте аналог Future.traverse,
   * у которго будет ограничено число одновременно выполняющихся Future,
   * разрешается использовать Future.traverse внутри реализации
   *
   * @param parallelism парарллелизм
   * @param futures     список асинхронных задач
   * @return асинхронную задачу с кортежом из двух списков
   */
  def parTraverse[A, B](parallelism: Int)(xs: List[A])(fa: A => Future[B])
                       (implicit ex: ExecutionContext): Future[List[B]] = ???
}
