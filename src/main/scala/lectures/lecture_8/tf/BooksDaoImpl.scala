package lectures.lecture_8.tf

import doobie.ConnectionIO
import fs2.Stream
import lectures.lecture_8.tf.domain.{Author, Book}

import java.util.concurrent.atomic.AtomicReference
import scala.concurrent.Future

trait BooksDao[F[_]] {
  def getBooks: F[Vector[Book]]

  def getBooksStream: Stream[F, Book]

  def addBooks(newBooks: Vector[Book]): F[Int]

  def getBooksCIO: ConnectionIO[Vector[Book]]

  def addBooksCIO(newBooks: Vector[Book]): ConnectionIO[Int]
}

class BooksDaoImpl extends BooksDao[Future] {

  private val books = new AtomicReference(
    Vector(
      Book(1, "The Sorrows of Young Werther", Author("Johann Wolfgang von Goethe")),
      Book(2, "Iliad", Author("Homer")),
      Book(3, "Nad Niemnem", Author("Eliza Orzeszkowa")),
      Book(4, "The Colour of Magic", Author("Terry Pratchett")),
      Book(5, "The Art of Computer Programming", Author("Donald Knuth")),
      Book(6, "Pharaoh", Author("Boleslaw Prus"))
    )
  )

  override def getBooks: Future[Vector[Book]] =
    Future.successful(books.get())

  override def addBooks(newBooks: Vector[Book]): Future[Int] = ???
    /*Future.successful(books.updateAndGet(v => v ++ newBooks)).map(_ => books.get().size)*/

  override def getBooksCIO: ConnectionIO[Vector[Book]] = ???

  override def addBooksCIO(newBooks: Vector[Book]): ConnectionIO[Int] = ???

  override def getBooksStream: Stream[Future, Book] = ???
}
