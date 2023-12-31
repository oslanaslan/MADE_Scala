package homeworks.homework_2

/**
 * Раскладывание вещей по коробкам
 *
 * У Вас есть вещи 3-х разных типов:
 * * * * Watches - часы
 * * * * Boots - кроссовки
 * * * * Book - книги
 *
 * Вы хотите привести свои вещи в порядок и оставить только нужные
 * Для этого Вы завели себе коробку с отделениями для каждого типа вещей
 * * * * * StuffBox
 *
 * В StuffBox вы кладете вещи таким образом:
 * * * * * Все часы дороже 1000 - в StuffBox.watches
 * * * * * Все кроссовки от Converse или Adidas - в StuffBox.boots
 * * * * * Все интересные книги - в StuffBox.books
 * * * * * Остальное - в StuffBox.junk
 *
 * После того, как Вы разложили вещи, вы пытаетеь найти свой любимый нож,
 * который вы могли случайно положить в junk.
 *
 * После запуска SortingStuff должен напечатать ответ на вопрос:
 * "Is the knife in a junk? - "
 *
 * Чтобы выполнить задание, раскомментируйте код и следуйте подсказкам
 *
 */

object pattern_matching extends App {

  object Knife extends Stuff

  trait Stuff

  case class Boots(brand: String, size: Int = 43) extends Stuff

  case class Watches(brand: String, cost: Float) extends Stuff

  case class Book(name: String, isInteresting: Boolean = false) extends Stuff

  val stuff = List(
    Boots("Adidas", 42),
    Book("Game of thrones 1", true),
    Boots("Reebok", 42),
    Watches("Nautica", 10000),
    Boots("Converse", 43),
    Boots("Converse", 43),
    Watches("Breitling", 100000),
    Watches("Electronika", 1000),
    Boots("Skorohod", 50),
    Boots("Noname", 45),
    Watches("Zarya", 15000),
    Book("Game of thrones 3"),
    Book("Game of thrones 4"),
    Watches("Casio", 5000),
    Watches("Citizen", 5000),
    Book("Game of thrones 2", true),
    Book("Idiot", true),
    Book("Lubovnaya lubov"),
    Knife
  )

  case class StuffBox(books: List[Book] = Nil,
                      watches: List[Watches] = Nil,
                      boots: List[Boots] = Nil,
                      junk: List[Stuff] = Nil)

  def sortJunk(stuff: List[Stuff]): StuffBox = sort(stuff, StuffBox())
  // // Замените знаки вопроса подходящим кодом
  // // Поправьте логику метода
  private def sort(stuff: List[Stuff], stuffBox: StuffBox): StuffBox = ??? //{
  //    case _ => stuffBox
  //    case ??? =>
  //      val newBox = putStuffInRightBox(item, stuffBox)
  //      sort(rest, newBox)
  //
  //  }
  //  // Метод должен положить вещь в правильную коробку
  private def putStuffInRightBox(item: Stuff, stuffBox: StuffBox) = ??? //{
  //    case ??? => stuffBox.copy(watches = it :: stuffBox.watches)
  //    case junk@_ => stuffBox.copy(junk = junk :: stuffBox.junk)
  //    case ??? => stuffBox.copy(boots = it :: stuffBox.boots)
  //  }
  //
  def findMyKnife(stuffBox: StuffBox): Boolean = ??? // stuffBox match {
  //    case ??? if junk.contains(Knife) => true
  //    case _ => false
  //  }

  // //вместо вопросов подставьте композицию функций  sortJunk и findMyKnife
  // val knifeIsInJunk = (???) (stuff)

  //print(s"Is knife in a junk? - $knifeIsInJunk")
}
