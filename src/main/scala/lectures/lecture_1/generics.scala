package lectures.lecture_1

object generics extends App {

  //def sum(i: Int, j: Int): Int = ???

  def sortInt(l: List[Int]): Int = ???

  def sortString(l: List[String]): Int = ???

  trait Order { //interface
    def compare(ord: Order) = ???
  }

  def sort[T >: Order](l: List[T]): List[T] = {
    //l.head.compare(???)
    ???
  }

  def sort2[T <: Order](l: List[T]): List[T] = {
    l.head.compare(???)
  }


}
