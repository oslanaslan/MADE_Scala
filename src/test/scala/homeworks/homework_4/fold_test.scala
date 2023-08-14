package homeworks.homework_4

import homeworks.homework_4.fold.{flatFoldF, foldF}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class fold_test extends AnyFlatSpec with Matchers {

  "foldF" should "fold list of futures into a single future using combining function" in {

    import scala.concurrent.ExecutionContext.Implicits.global

    val input = Seq(1, 2, 3, 4, 5, 6).map(Future.successful)

    Await.result(foldF[Int, Int](input, 0, _ + _), 1.second) shouldBe 21
    Await.result(foldF[Int, Int](input, 1, _ * _), 1.second) shouldBe 720

  }

  "flatFoldF" should "fold list of futures into a single future using async combining function" in {

    import scala.concurrent.ExecutionContext.Implicits.global

    val input = Seq(1, 2, 3, 4, 5, 6).map(Future.successful)
    val sum = (a: Int, b: Int) => Future.successful(a + b)
    val product = (a: Int, b: Int) => Future.successful(a * b)

    Await.result(flatFoldF(input, 0, sum), 1.second) shouldBe 21
    Await.result(flatFoldF(input, 1, product), 1.second) shouldBe 720

  }

}
