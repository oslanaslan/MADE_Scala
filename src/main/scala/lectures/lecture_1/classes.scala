package lectures.lecture_1

object classes extends App {

  trait Foo[T] {
    def foo(t: T): T = {
      println("Foo")
      t
    }
  }

  trait FooExt[T] extends Foo[T] {
    override def foo(t: T): T = {
      println("FooExt")
      super.foo(t)
    }
  }

  trait Bar[T] {
    def foo(t: T): T = {
      println("Bar")
      t
    }
  }

  class FooBarImpl[T] extends Bar[T] with FooExt[T] {
    override def foo(t: T): T = super.foo(t)
  }

  val fb = new Bar[Int] with FooExt[Int] {
    override def foo(t: Int): Int = super.foo(t)
  }

  fb.foo(1)

}
