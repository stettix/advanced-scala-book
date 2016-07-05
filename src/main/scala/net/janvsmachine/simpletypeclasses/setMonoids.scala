package net.janvsmachine.simpletypeclasses

import cats.Monoid

object setMonoids {

  implicit def setUnionMonoid[A] = new Monoid[Set[A]] {

    override def empty: Set[A] = Set.empty

    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y

  }

}
