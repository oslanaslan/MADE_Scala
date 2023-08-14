package homeworks.homeworks_8

import cats.effect.Resource
import doobie.implicits.toSqlInterpolator
import doobie.util.fragment
import doobie.util.transactor.Transactor

import java.util.UUID

/**
 * createUser - метод должен делать вставки пользователя
 * updateUser - метод должен обновлять данные о пользователе
 * deleteUser - метод должен удалять пользователя из базы и возвращать удаленного пользователя, с учетом того, что его может не быть
 * findByLastName - метод должен возвращать массив пользователей с заданной фамилией
 * findAll - метод должен возвращать всех пользователей
 */

case class User(id: UUID,
                firstName: String,
                lastName: String,
                age: Int)

object UserDao {

  val ddl: fragment.Fragment =
    sql"""
         | CREATE TABLE users (
         | id varchar(32) not null,
         | firstName varchar(100) not null,
         | lastName varchar(100) not null
         | age int not null
         |)
         |""".stripMargin

}

trait UserDao[F[_]] {
  def createUser(user: User): F[User]

  def updateUser(user: User): F[Unit]

  def deleteUser(id: UUID): F[Option[User]]

  def findByLastName(lastName: String): F[List[User]]

  def findAll(): F[List[User]]
}

object UserDaoImpl {
  def make[F[_]](xa: Transactor[F]): Resource[F, UserDao[F]] = ???
}

class UserDaoImpl[F[_]] extends UserDao[F] {
  override def createUser(user: User): F[User] = ???

  override def updateUser(user: User): F[Unit] = ???

  override def deleteUser(id: UUID): F[Option[User]] = ???

  override def findByLastName(lastName: String): F[List[User]] = ???

  override def findAll(): F[List[User]] = ???
}
