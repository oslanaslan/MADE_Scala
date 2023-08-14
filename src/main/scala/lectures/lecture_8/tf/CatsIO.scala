package lectures.lecture_8.tf

import cats.Monad
import cats.data.EitherT
import cats.effect.{ExitCode, IO, IOApp, Resource}
import cats.syntax.apply._
import cats.syntax.functor._
import lectures.lecture_8.tf.domain.{Author, Book}

object CatsIO extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val resourceF: Resource[IO, DBooksDaoImpl[IO]] = for {
      maybeConfig <- Resource.eval(Config.loadConfig[IO])
      cfg <- Resource.eval(EitherT.fromOption[IO](maybeConfig, new Exception("config error")).rethrowT)
      trx <- Transactor.makeTransactor[IO](cfg)
      database <- DBooksDaoImpl.make[IO](trx)
      _ <- Resource.eval(database.createTable(trx))
    } yield database

    resourceF.use { db =>
      doSomeWork[IO](db)
    }.map(_ => ExitCode.Success)
  }

  def doSomeWork[F[_] : Monad](db: DBooksDaoImpl[F]): F[Unit] = {
    db.putAndGet(Vector(Book(100, "war and peace", Author("tolstoy")))).map(println)
  }

}