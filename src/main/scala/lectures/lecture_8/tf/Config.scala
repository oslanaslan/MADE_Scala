package lectures.lecture_8.tf

import cats.effect.{Bracket, IO, Sync}
import cats.implicits.catsSyntaxApply

import java.io.{File, FileReader}
import java.util.Scanner

case class Config(url: String, username: String, password: String)

object Config {

  def loadConfig[F[_] : Sync]: F[Option[Config]] = {
    val path = "src/main/resources/config.conf"
    Sync[F].bracket[Scanner, Option[Config]](open(path))(read(_))(close(_))
  }

  private def open[F[_] : Sync](path: String): F[Scanner] = {
    Sync[F].delay(new Scanner(new FileReader(new File(path))))
  }

  private def read[F[_] : Sync](sc: Scanner): F[Option[Config]] = {
    Sync[F].delay(fromString(sc.nextLine()))
  }

  private def close[F[_] : Sync](sc: Scanner): F[Unit] = {
    Sync[F].delay(println("closing resource")) *> Sync[F].delay(sc.close())
  }

  private def fromString(str: String): Option[Config] = {
    val keys = str.split(";").map { tuple =>
      val s = tuple.split("=")
      (s.head, s.tail.head)
    }.toMap

    for {
      url <- keys.get("url")
      username <- keys.get("username")
      password <- keys.get("password")
    } yield Config(url, username, password)
  }

}