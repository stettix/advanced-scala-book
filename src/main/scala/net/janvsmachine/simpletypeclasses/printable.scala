package net.janvsmachine.simpletypeclasses

trait Printable[A] {

  def format(value: A): String

}

object PrintDefaults {

  implicit val stringPrintable = new Printable[String] {
    def format(value: String) = value
  }

  implicit val intPrintable = new Printable[Int] {
    def format(value: Int) = value.toString
  }

}

object Print {

  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(printable.format(value))
}

object PrintSyntax {

  implicit class PrintOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = Print.format(value)
    def print(implicit printable: Printable[A]): Unit = Print.print(value)
  }

}

object TestPrint extends App {

  import PrintDefaults._
  import Print._
  import PrintSyntax._

  final case class Cat(name: String, age: Int, colour: String)

  val bunny = Cat("Bunny", 2, "grey")

  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat): String = {
      val name = Print.format(cat.name)
      val age = Print.format(cat.age)
      val colour = Print.format(cat.colour)
      s"$name is a $age year-old $colour cat"
    }
  }

  print("Yo")
  print(42)
  //print(3.14) // Won't compile, yay!
  print(bunny)

  bunny.print

}
