package lectures.lecture_4

object ContextII {
  implicit val ctx = Context("MyContext")
}

import ContextII.ctx

trait ContextI {
  def message: String
}

case class Context(message: String) extends ContextI

object ImplicitParameters extends App {

  def printContext(implicit ctx: ContextI): Unit = {
    println(ctx.message)
  }

  printContext

}