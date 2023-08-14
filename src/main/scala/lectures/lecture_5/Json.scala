package lectures.lecture_5

import lectures.lecture_5.JsonWriter.JsonWriteOps

sealed trait JsValue

final case class JsObject(value: Map[String, JsValue]) extends JsValue

final case class JsString(value: String) extends JsValue

final case class JsNumber(value: Double) extends JsValue

final case class JsArray(list: List[JsValue]) extends JsValue

case object JsNull extends JsValue


trait JsonWriter[T] {
  def toJson(t: T): JsValue
}

object JsonWriter {
  implicit class JsonWriteOps[T](t: T) {
    def toJson(implicit jsonWriter: JsonWriter[T]): JsValue = jsonWriter.toJson(t)
  }
}

object JsonPrimitives {

  implicit val stringJsWrite = new JsonWriter[String] {
    override def toJson(t: String): JsValue = JsString(t)
  }

  implicit val doubleJsWrite = new JsonWriter[Double] {
    override def toJson(t: Double): JsValue = JsNumber(t)
  }

  implicit def optionJsonWriter[A](implicit jsonWriter: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    override def toJson(opt: Option[A]): JsValue = opt match {
      case Some(a) => a.toJson
      case None => JsNull
    }
  }

  implicit def listJsonWriter[A](implicit jsonWriter: JsonWriter[A]): JsonWriter[List[A]] = new JsonWriter[List[A]] {
    override def toJson(l: List[A]): JsValue = l match {
      case Nil => JsArray(List())
      case _ => JsArray(l.map(_.toJson))
    }
  }

}

import JsonPrimitives._

object Json extends App {

  val map: Map[String, String] = Map()

  "123".toJson

  val optionS = Option("123")

  val optionD = Option(123.0)

  val listD = List(123.0, 1)

  println(optionS.toJson)

  println(optionD.toJson)

  println(listD.toJson)

  case class Person(name: String, age: Int) //

  //Person("p", 123).toJson

}