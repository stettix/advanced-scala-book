package net.janvsmachine.simpletypeclasses

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, age: Int)

object DefaultJsonWriters {

  implicit val stringJsonWriter = new JsonWriter[String] {
    def write(value: String) = JsString(value)
  }

  implicit val intJsonWriter = new JsonWriter[Int] {
    def write(value: Int) = JsNumber(value.toDouble)
  }

  implicit val personJsonWriter = new JsonWriter[Person] {
    def write(value: Person) = JsObject(Map("name" -> JsString(value.name), "age" -> JsNumber(value.age.toDouble)))
  }
}

object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json =
    writer.write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit writer: JsonWriter[A]): Json = writer.write(value)
  }
}

object TestJson extends App {
  import DefaultJsonWriters._
  import JsonSyntax._

  val jan = Person("Jan Stette", 42)
  val json = Json.toJson(jan)
  val json2 = jan.toJson

  println(json)
}
