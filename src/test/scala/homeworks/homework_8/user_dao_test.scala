package homeworks.homework_8

import cats.effect.{ContextShift, ExitCode, IO, IOApp, Resource}
import homeworks.homeworks_8.{User, UserDao, UserDaoImpl}
import lectures.lecture_8.tf.{Config, Transactor}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.UUID
import scala.concurrent.ExecutionContext

class user_dao_test extends AnyFlatSpec with Matchers {

  trait mocks {
    val config = Config(url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", username = "", password = "")
    implicit val cs = IO.contextShift(ExecutionContext.global)
    val daoR: Resource[IO, UserDao[IO]] = for {
      trx <- Transactor.makeTransactor[IO](config)
      dao <- UserDaoImpl.make(trx)
    } yield dao
    val user = User(UUID.randomUUID(), "John", "Smith", 44)
  }


  "UserDao" should "create user" in new mocks {
    val res: User = daoR.use(dao => dao.createUser(user)).unsafeRunSync()
    res shouldBe user
  }


  //TODO implement tests
  "UserDao" should "update user" in new mocks {}
  "UserDao" should "delete user" in new mocks {}
  "UserDao" should "find by condition" in new mocks {}
  "UserDao" should "find by last name" in new mocks {}
  "UserDao" should "find all" in new mocks {}

}
