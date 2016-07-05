package net.janvsmachine.simpletypeclasses

import cats.std.option._
import cats.std.int._
import cats.syntax.semigroup._
import cats.syntax.option._
import cats.syntax.eq._
import cats.Monoid


object Superadder extends App {

  def addInts(items: List[Int]): Int = items.fold(0)(_ |+| _)

  def addOptInts(items: List[Option[Int]]): Option[Int] =
    items.foldLeft(0.some)((x: Option[Int], y: Option[Int]) => x |+| y)

  def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

  assert(add(List[Option[Int]]()) === None)
  assert(add(List[Option[Int]](None)) === None)
  assert(add(List(42.some)) === 42.some)
  assert(add(List(1.some, 2.some, 3.some)) === 6.some)

  assert(add(List[Int]()) === 0)
  assert(add(List(42)) === 42)
  assert(add(List(1, 2, 3)) === 6)

}
