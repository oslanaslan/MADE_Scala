package lectures.lecture_8.tf

import cats.effect.{Async, Blocker, ContextShift, Resource}
import doobie._
import doobie.h2._


object Transactor {
  // Resource yielding a transactor configured with a bounded connect EC and an unbounded
  // transaction EC. Everything will be closed and shut down cleanly after use.
  def makeTransactor[F[_] : Async : ContextShift](config: Config): Resource[F, H2Transactor[F]] = for {
    ce <- ExecutionContexts.fixedThreadPool[F](4) // our connect EC
    be <- Blocker[F] // our blocking EC
    xa <- H2Transactor.newH2Transactor[F](
      url = config.url, // connect URL
      user = config.username.trim, // username
      pass = config.password.trim, // password
      ce, // await connection here
      be // execute JDBC operations here
    )
  } yield xa

}