package lectures.lecture_10

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors, LoggerOps}
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, SpawnProtocol}

object actors2 {
  /** Два основных компонента актора:
   * 1. Behaviour
   * 2. Context
   *
   * Для создания можно воспользоваться фабричными методами объекта Behaviours или заэкстендить AbstractBehavior
   */

  object Supervisor {
    def apply(): Behavior[SpawnProtocol.Command] = Behaviors.setup { ctx =>
      ctx.log.info(ctx.self.toString)
      SpawnProtocol()
    }
  }

  object behaviours_factory_methods {

    object Echo {
      def apply(): Behavior[String] = Behaviors.setup { ctx =>

        Behaviors.receiveMessage {
          msg =>
            ctx.log.info(msg)
            Behaviors.same
        }
      }
    }
  }

  object abstract_behaviour {
    object Echo {

      def apply(): Behavior[String] = Behaviors.setup { ctx =>
        new Echo(ctx)
      }

      class Echo(ctx: ActorContext[String]) extends AbstractBehavior[String](ctx) {
        def onMessage(msg: String): Behavior[String] = {
          ctx.log.info(msg)
          this
        }
      }
    }

  }

  /** Реализовать актор, который будет менять свое поведение в ответ на сообщения
   */

  object change_behaviour {

    object Worker {

      sealed trait WorkerProtocol

      object WorkerProtocol {
        case object Start extends WorkerProtocol

        case object StandBy extends WorkerProtocol

        case object Stop extends WorkerProtocol
      }

      import WorkerProtocol._

      def apply(): Behavior[WorkerProtocol] = idle()

      def idle(): Behavior[WorkerProtocol] = Behaviors.setup { ctx =>

        Behaviors.receiveMessage {
          case msg@Start =>
            ctx.log.info(msg.toString)
            workInProgress()
          case msg@StandBy =>
            ctx.log.info(msg.toString)
            idle()
          case msg@Stop =>
            ctx.log.info(msg.toString)
            Behaviors.stopped
        }
      }

      def workInProgress(): Behavior[WorkerProtocol] = Behaviors.setup { ctx =>
        Behaviors.receiveMessage {
          case Start =>
            Behaviors.unhandled
          case StandBy =>
            ctx.log.info("Перехожу в standby")
            idle()
          case Stop =>
            ctx.log.info("Останавливаюсь")
            Behaviors.stopped
        }
      }
    }
  }

  /** *
   * 1. Реализовать актор который будет считать полученные им сообщения
   * 2. Доработать актор так, чтобы он мог возвращать текущий Counter
   */

  object handle_state {
    object Counter {

      sealed trait CounterProtocol

      object CounterProtocol {
        final case object Inc extends CounterProtocol

        final case class GetCounter(replyTo: ActorRef[Int])
          extends CounterProtocol
      }

      import CounterProtocol._

      def apply(init: Int): Behavior[CounterProtocol] = inc(init)

      def inc(counter: Int): Behavior[CounterProtocol] =
        Behaviors.receiveMessage {
          case Inc =>
            inc(counter + 1)
          case GetCounter(replyTo) =>
            replyTo ! counter
            Behaviors.same
        }

    }
  }
}
