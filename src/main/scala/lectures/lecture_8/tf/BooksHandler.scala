package lectures.lecture_8.tf

import cats.effect.{Async, ContextShift, Sync}
import lectures.lecture_8.tf.domain.Book

import scala.concurrent.ExecutionContext

trait BooksHandler[F[_]] {
  def getBooks: F[Vector[Book]]

  def addBooks(books: Vector[Book]): F[Int]
}

class BooksHandlerImpl[F[_] : Async : ContextShift](booksDao: BooksDao[F])
                                                   (implicit ec: ExecutionContext) extends BooksHandler[F] {
  override def getBooks: F[Vector[Book]] = {
    booksDao.getBooks
  }

  override def addBooks(books: Vector[Book]): F[Int] = {
    booksDao.addBooks(books)
  }
}
