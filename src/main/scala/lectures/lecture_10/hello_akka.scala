package lectures.lecture_10

import akka.actor.typed.SupervisorStrategy
import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Props, SpawnProtocol}
import akka.util.Timeout

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object hello_akka extends App {

  sealed trait PingProtocol

  case class Ping(sender: ActorRef[PingProtocol]) extends PingProtocol

  case class Pong(sender: ActorRef[PingProtocol]) extends PingProtocol

  case class Stop(sender: ActorRef[PingProtocol]) extends PingProtocol

  object PingBehaviour {
    def apply(): Behavior[PingProtocol] = Behaviors.supervise(count(0))
      .onFailure[Exception](SupervisorStrategy.resume)

    def count(counter: Int): Behavior[PingProtocol] = {
      Behaviors.setup[PingProtocol] { ctx =>
        Behaviors.receiveMessage {
          case Ping(sender) =>
            if (counter > 3) {
              ctx.children.foreach(ac => println(ac.path))
              Behaviors.stopped
            } else if (counter == 2) {
              sender ! Pong(sender = ctx.self)
              throw new Exception("boom")
            }
            else {
              ctx.log.info("Ping accepted")
              sender ! Pong(sender = ctx.self)
              ctx.spawn(PingBehaviour(), "ping" + counter, Props.empty)
              count(counter + 1)
            }
          case Pong(sender) =>
            if (counter > 3) {
              Behaviors.stopped
            } else if (counter == 2) {
              sender ! Pong(sender = ctx.self)
              throw new Exception("boom")
            }
            else {
              ctx.log.info("Pong accepted")
              sender ! Ping(sender = ctx.self)
              count(counter + 1)
            }
        }
      }
    }
  }

  val guardian = Behaviors.setup[SpawnProtocol.Command] { _ =>
    SpawnProtocol()
  }

  implicit val actorSystem: ActorSystem[SpawnProtocol.Command] = ActorSystem(guardian, "system")

  implicit val ex: ExecutionContext = actorSystem.executionContext

  implicit val timeout: Timeout = Timeout(1, TimeUnit.SECONDS)

  //actorSystem.tell(SpawnProtocol.Spawn(PingBehaviour(), "pingActor", Props.empty, _))

  val pingActorF: Future[ActorRef[PingProtocol]] =
    actorSystem.ask(SpawnProtocol.Spawn(PingBehaviour(), "pingActor", Props.empty, _))

  val pongActorF: Future[ActorRef[PingProtocol]] =
    actorSystem.ask(SpawnProtocol.Spawn(PingBehaviour(), "pongActor", Props.empty, _))

  val fut = pingActorF.zip(pongActorF).map {
    case (pingActor, pongActor) =>
      pingActor.tell(Ping(pongActor))
  }

  Await.result(fut, Duration.Inf)

  //actorSystem.terminate()

}
