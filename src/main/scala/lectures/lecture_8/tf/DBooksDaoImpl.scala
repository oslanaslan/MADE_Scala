package lectures.lecture_8.tf

import cats.effect.{BracketThrow, Resource}
import doobie.{ConnectionIO, Transactor}
import lectures.lecture_8.tf.domain.Book
import doobie.implicits._
import doobie.util.fragment
import doobie.util.query.DefaultChunkSize
import doobie.util.update.Update
import lectures.lecture_8.tf.DBooksDaoImpl.ddl
import fs2.Stream

object DBooksDaoImpl {

  def make[F[_] : BracketThrow](transactor: Transactor[F]): Resource[F, DBooksDaoImpl[F]] = {
    Resource.pure[F, DBooksDaoImpl[F]](new DBooksDaoImpl[F](transactor))
  }

  val ddl: fragment.Fragment =
    sql"""
         | CREATE TABLE books (
         | id int not null,
         | title varchar(100) not null,
         | author varchar(100) not null
         |)
         |""".stripMargin
}

class DBooksDaoImpl[F[_] : BracketThrow](xa: Transactor[F]) extends BooksDao[F] {
  /**
   * val t: ConnectionIO[Vector[Book]] = sql"select * from books"
   * .query[Book]
   * .to[Vector]
   * .transact(xa)  // ConnectionIO -> F
   */

  def createTable(xa: Transactor[F]): F[Int] = {
    ddl.update.run.transact(xa)
  }

  override def getBooksCIO: ConnectionIO[Vector[Book]] =
    sql"select id, title, author from books"
      .query[Book]
      .to[Vector]

  override def getBooks: F[Vector[Book]] =
    sql"select id, title, author from books"
      .query[Book]
      .to[Vector]
      .transact(xa)

   def getBooksStream: Stream[F, Book] =
    sql"select id, title, author from books"
      .query[Book]
      .stream
      .transact(xa)

  override def addBooksCIO(newBooks: Vector[Book]): ConnectionIO[Int] =
    Update[Book]("insert into books (id, title, author) values (?, ?, ?)")
      .updateMany(newBooks)

  override def addBooks(newBooks: Vector[Book]): F[Int] = {
    Update[Book]("insert into books (id, title, author) values (?, ?, ?)")
      .updateMany(newBooks)
      .transact(xa)
  }

  def putAndGetCIO(newBooks: Vector[Book]): ConnectionIO[Vector[Book]] = for {
    _ <- addBooksCIO(newBooks)
    res <- getBooksCIO
  } yield res

  def putAndGet(newBooks: Vector[Book]): F[Vector[Book]] =
    putAndGetCIO(newBooks).transact(xa)

}
