package net.janvsmachine.simpletypeclasses

import cats.Monoid

object booleanMonoids extends App {

  implicit val constantBoolMonoid1 = new Monoid[Boolean] {
    val empty = false
    def combine(x: Boolean, y: Boolean) = empty
  }

  implicit val constantBoolMonoid2 = new Monoid[Boolean] {
    val empty = true
    def combine(x: Boolean, y: Boolean) = empty
  }

  assert(constantBoolMonoid1.combine(constantBoolMonoid1.empty, constantBoolMonoid1.empty) == constantBoolMonoid1.empty)
  assert(constantBoolMonoid1.combine(constantBoolMonoid1.combine(constantBoolMonoid1.empty, constantBoolMonoid1.empty), constantBoolMonoid1.empty) == constantBoolMonoid1.empty)

  case object BooleanMonoid2 extends Monoid[Boolean] {
    val empty = true
    def combine(x: Boolean, y: Boolean) = x && y
  }

  case object BooleanMonoid3 extends Monoid[Boolean] {
    val empty = false
    def combine(x: Boolean, y: Boolean) = x || y
  }

  case object BooleanMonoid4 extends Monoid[Boolean] {
    val empty = true
    def combine(x: Boolean, y: Boolean) = x || y
  }

  // There's also the ones which only use x or y, but they won't be associative (or will they?)

  // So: the trivial case of always returning empty is presumably a monoid.
  // As is the one where empty is true and the operation is ||

 // class
}

