package net.janvsmachine.simpletypeclasses

import cats.Eq
import cats.syntax.option._
import cats.std.all._
import cats.syntax.eq._

object TestEquality extends App {

  final case class Cat(name: String, age: Int, owner: Option[String])

  implicit val catEqual = Eq.instance { (cat1: Cat, cat2: Cat) =>
    cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.owner === cat2.owner
  }

  val cat1 = Cat("Bunny", 2, "Anna".some)
  val cat2 = Cat("Juno", 1, "Jan".some)

  val optionCat1: Option[Cat] = Some(cat1)
  val optionCat2: Option[Cat] = None

  assert(cat1 === cat1)
  assert(cat1 =!= cat2)
  //assert(cat === optionCat1) // Doesn't compile
  assert(optionCat1 === optionCat1)
  assert(optionCat1 =!= optionCat2)

}
