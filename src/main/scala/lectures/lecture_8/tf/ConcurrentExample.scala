package lectures.lecture_8.tf

import cats.effect.{Blocker, Clock, Concurrent, ExitCode, IO, IOApp, LiftIO}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.DurationInt

object ConcurrentExample extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    for {
      start <- Clock[IO].realTime(TimeUnit.MILLISECONDS)
      fiber1 <- getUser(1).start
      fiber2 <- Concurrent[IO].start(getUser(1))
      _ <- fiber1.cancel
      _ <- fiber2.join
      finish <- Clock[IO].realTime(TimeUnit.MILLISECONDS)
      _ = println(s"Finished ${finish - start}")
      either <- Concurrent[IO].race(getUser(1), getUser(2))
      _ = either match {
        case Left(value) => println(value)
        case Right(value) => println(value)
      }
    } yield ExitCode.Success

  }

  def getUser(i: Int): IO[Int] = IO.sleep(1.seconds) *> IO.delay(println(Thread.currentThread())).map(_ => i)

  def timeout: IO[ExitCode] = {
    for {
      either <- Concurrent[IO].race(getUser(1), IO.sleep(2.seconds))
      _ = either match {
        case Left(value) => println(value)
        case Right(value) => println(value)
      }
    } yield ExitCode.Success
  }
}
