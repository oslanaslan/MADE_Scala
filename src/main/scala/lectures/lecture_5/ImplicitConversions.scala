package lectures.lecture_5

case class A(s: String)

case class B(s: String)

case class C(s: String)

case class D(s: String)

object ImplicitConversions extends App {

  val a = A("This is A")

  def printD(d: D): Unit = println(d.s)

  implicit def a2b(a: A): B = B(a.s)

  implicit def b2d(a: B): D = D(a.s)

  implicit def b2d2(b: A)(implicit f: A => B): D = f(a)

  printD(a)

}


  /*
  implicit def a2b(a: A): B = B(a.s)

  implicit def b2d(a: B): D = D(a.s)

  implicit def b2d(a: A)(implicit f: A => B): D = f(a)

  def printD(d: D) = println(d)

  printD(A(""))
  */