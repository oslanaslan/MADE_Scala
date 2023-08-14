package lectures.lecture_10

import akka.actor.typed.ActorSystem
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.ExecutionContext.Implicits.global
import java.util.Random
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object streams extends App {

  implicit val system: ActorSystem[HelloWorldMain.SayHello] = ActorSystem(HelloWorldMain(), "hello")

  // Simulate a CPU-intensive workload that takes ~10 milliseconds
  def spin(value: Int): Int = {
    val start = System.currentTimeMillis()
    while ((System.currentTimeMillis() - start) < 100) {}
    value
  }

  // Simulate a non-uniform CPU-bound workload
  def uniformRandomSpin(value: Int): Future[Int] = Future {
    val max = new Random().nextInt(1000)
    val start = System.currentTimeMillis()
    while ((System.currentTimeMillis() - start) < max) {}
    value
  }

  val t = System.currentTimeMillis()

  /*Await.result(Source(1 to 10)
    .map(spin) // 1 Flow
    .async
    .map(spin) // 2
    .run(), Duration.Inf)*/

  Await.result(Source(1 to 10)
    .mapAsyncUnordered(10)(uniformRandomSpin)
    .runWith(Sink.foreach(println)), Duration.Inf)

  println(System.currentTimeMillis() - t)

}
