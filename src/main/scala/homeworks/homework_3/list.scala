package homeworks.homework_3


object list extends App {

  def getOrder(id: Int): List[String] =
    id match {
      case 1 => List("Apples", "Cherries", "Bananas")
      case 2 => List("Milk", "Yogurt", "Butter", "Salt")
      case 3 => List("Sugar")
      case _ => Nil
    }

  /**
   * Реализуйте метод getItems, который возвращает все наименования из заказов из списка `ids`,
   * которые начинаются на букву `firstLetter`
   *
   *  Примеры:
   *  assert(getItems(List(1, 2, 3), 'B') == List("Bananas", "Butter"))
   *  assert(getItems(List(2, 3, 4), 'B') == List("Butter"))
   *
   *  Отдел аналитики продуктового интернет-магазина хочет получать список наименований,
   *  которые начинаются на заданную букву и присутствуют в заказах с определенными id.
   *  Метод `getOrder` возвращает список наименований в заказе по id.
   *
   * */
  def getItems(ids: List[Int], firstLetter: Char): List[String] = ???


}
