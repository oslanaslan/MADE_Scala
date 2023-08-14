package lectures.lecture_9

import cats.effect.{Blocker, Clock, ExitCode, IO, IOApp}

import java.nio.file.{Path, Paths}
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.DurationInt

object fs_file extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    Clock[IO].realTime(TimeUnit.MILLISECONDS).flatMap {
      start =>
        fs2.io.file.readAll[IO](
          Paths.get("/Users/a.s.korolkov/repos/made-scala/src/main/resources/file.txt"),
          Blocker.liftExecutionContext(executionContext),
          1024
        ).through(fs2.text.utf8Decode)
          .through(fs2.text.lines)
          .filter(_.nonEmpty)
          .parEvalMapUnordered(10)(e => IO.sleep(e.toInt.seconds).as(e))
          .evalMap(e => IO.delay(println(e)))
          .compile
          .drain
          .flatMap(_ => Clock[IO].realTime(TimeUnit.MILLISECONDS))
          .flatMap(finished => IO.delay(println(finished - start)))
          .map(_ => ExitCode.Success)
    }
  }

  //building

  //pure

  //for comprehension

  //combinators

  //file parsing

}