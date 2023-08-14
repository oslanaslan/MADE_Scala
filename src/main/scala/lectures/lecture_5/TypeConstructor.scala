package lectures.lecture_5

object TypeConstructor extends App {

  def zip[A, B](a: List[A], b: List[B]): List[(A, B)] = a.zip(b)

  def zip[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = a.zip(b)

  def zipI[F[_], A, B](fa: F[A], fb: F[B]): F[(A, B)] = ???

  def implicitly[T](implicit e: T): T = e

  trait Zippable[F[_]] {
    def zip[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  }

  def zipF[F[_] : Zippable, A, B](fa: F[A], fb: F[B]): F[(A, B)] = {
    val Z = implicitly[Zippable[F]]
    Z.zip(fa, fb)
  }

  def zipF2[F[_], A, B](fa: F[A], fb: F[B])(implicit Z: Zippable[F]): F[(A, B)] = {
    Z.zip(fa, fb)
  }

  implicit val listZ = new Zippable[List] {
    override def zip[A, B](fa: List[A], fb: List[B]): List[(A, B)] = fa.zip(fb)
  }

  implicit val listO = new Zippable[Option] {
    override def zip[A, B](fa: Option[A], fb: Option[B]): Option[(A, B)] = fa.zip(fb)
  }

  val zippedL = zipF(List(1, 2, 3), List("a", "b", "c"))

  val zippedO = zipF(Option(1), Option("a"))

  println(zippedL)

  println(zippedO)

}