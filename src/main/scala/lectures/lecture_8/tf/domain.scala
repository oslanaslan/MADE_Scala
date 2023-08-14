package lectures.lecture_8.tf

object domain {

  case class Author(name: String)

  case class Book(id: Int, title: String, author: Author)

}
