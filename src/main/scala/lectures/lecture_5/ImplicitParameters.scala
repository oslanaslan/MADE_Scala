package lectures.lecture_5

trait Ctx {
  def message: String
}

case class Context(message: String) extends Ctx

object ImplicitParameters extends App {

  def printContext(implicit ctx: Ctx): Unit = println(ctx.message)

  //printContext

}