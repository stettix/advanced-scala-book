package net.janvsmachine.simpletypeclasses

import cats.Show

object ShowCat extends App {

  import cats.std.int._
  import cats.std.string._
  import cats.syntax.show._

  final case class Cat(name: String, age: Int, colour: String)

  val bunny = Cat("Bunny", 2, "grey")

  implicit val showCat = Show.show[Cat] { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val colour = cat.colour.show
    s"$name is a $age year-old $colour cat"
  }

  println(bunny.show)

}
