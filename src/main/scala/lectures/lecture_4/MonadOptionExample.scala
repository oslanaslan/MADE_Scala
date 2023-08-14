package lectures.lecture_4

object MonadOptionExample extends App {

  object Monad {
    def unit[T](x: T): Monad[T] = ??? // Some(x)
  }

  trait Monad[T] {
    def flatMap[U](f: T => Monad[U]): Monad[U]
  }

  trait SocialService {
    def getUser(id: String): Option[String]
  }

  val ss: SocialService = new SocialService {
    override def getUser(id: String): Option[String] = id match {
      case "1" => None
      case "2" => Some("user2")
    }
  }

  val o: Option[(String, String)] = for {
    user1 <- ss.getUser("1") //None
    _ = println("option for")
    user2 <- ss.getUser("2")
    user3 = user1 + "123"
  } yield (user1, user2)

  val pair: (Option[String], Option[String]) =  (ss.getUser("1"), ss.getUser("2"))

  val user1 = ss.getUser("1")
  val user2 = ss.getUser("2")

  /** Цепочки вычислений с помощью flatMap */

  def one: Option[Int] = Some(1)

  def two: Option[Int] = Some(2)

  def none: Option[Int] = None

  /** Проверка монадических законов */

  def squareFunction(x: Int): Option[Int] = Some(x * x) // f

  def incrementFunction(x: Int): Option[Int] = Some(x + 1) //g

  /**
   * Left unit law:
   * (unit(x) flatMap f) == f(x)
   * */
  def leftUnitLaw(): Unit = {
    assert(Some(1).flatMap(squareFunction) == squareFunction(1))
    println("leftUnitLaw check success")
  }

  leftUnitLaw()

  /**
   * Right unit law:
   * (monad flatMap unit) == monad
   * */
  def rightUnitLaw(): Unit = {
    val monad: Option[Int] = Option(10)
    assert(monad.flatMap(Some(_)) == monad)
    println("rightUnitLaw check success")
  }

  rightUnitLaw()

  /**
   * Associativity law:
   * ((monad flatMap f) flatMap g) == (monad flatMap (x => f(x) flatMap g))
   * */
  def associativityLaw(): Unit = {
    val monad: Option[Int] = Some(2)
    val left = monad.flatMap(squareFunction).flatMap(incrementFunction)
    val right = monad.flatMap(x => squareFunction(x).flatMap(incrementFunction))
    assert(left == right)
    println("associativityLaw check success")
  }

  associativityLaw()
}