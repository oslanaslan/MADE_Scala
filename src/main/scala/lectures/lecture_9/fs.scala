package lectures.lecture_9

import cats.effect.{ExitCode, IO, IOApp}
import fs2.{Chunk, Pure, Stream}

object fs extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val ss: Stream[IO, Int] = Stream.eval(IO.pure(1))


    ss.cons(Chunk.seq(List(1, 2, 3)))

    ss.flatMap(elem => Stream.repeatEval(IO.pure(elem)))

    ss.map(println)

    val r: Stream[IO, Unit] = ss.evalMap(e => IO.delay(println(e)))

    for {
      i <- Stream.eval(IO.pure(1))
      _ <- Stream.empty
      _ = println(i)
    } yield ()


/*    Stream.repeatEval(IO.sleep(1.seconds))
      .evalMap(e => IO.delay(println(e)))
      .compile
      .drain
      .map(_ => ExitCode.Success)*/

    (for {
      i <- Stream.eval(IO.pure(1))
      _ <- Stream.eval(IO.unit)
      _ = println(i)
    } yield ())
      .compile
      .drain
      .map(_ => ExitCode.Success)
  }

  //building

  //pure

  //for comprehension

  //combinators

  //file parsing


}
