package lectures.lecture_9

import fs2.{Chunk, Pure, Stream}
import zio.interop.catz.taskConcurrentInstance
import zio.{IO, Task, URIO}

object fs_zio extends zio.App {

  //building

  //pure

  //for comprehension

  //combinators

  //file parsing

  override def run(args: List[String]): URIO[zio.ZEnv, zio.ExitCode] = {
    Stream.repeatEval(Task.effect(1))
      .evalMap(e => Task.effect(println(e)))
      .compile
      .drain
      .exitCode
  }
}
