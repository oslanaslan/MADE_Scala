package lectures.lecture_5

object ImplicitClass extends App {

  class Foo {
    def f: Unit = ???
  }

  class FooExt {
    def f: Unit = ???

    def ff: Unit = ???
  }

  implicit def fExt(f: Foo): FooExt = ???

  val f: Foo = ???

  f.ff

  implicit class FooExt2(f: Foo) {
    def fff = ???
  }

  f.fff
}
