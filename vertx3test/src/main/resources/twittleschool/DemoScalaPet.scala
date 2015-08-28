package com.xmgps.yfzx.hwb.scala

/*
 Demo:     Interacting with Java classes, and creating Scala classes.
 Author:   Matthew Might
 Site:     http://matt.might.net/
           http://www.ucombinator.org/

 See also: Pet.java
 */


// Interacting with a Java class:
object DemoScalaPet extends Application {

  val javaPet = new Pet("Steve")

  println(javaPet.getName())
  // Prints:
  // Steve
}


// With a Java-like accessor:
class ScalaPet(petName: String) {
  private val name = petName

  def getName() = name
}


// Without the superfluous private field:
class ScalaPet1(petName: String) {
  def getName() = petName
}


// Without the () on the accessor:
class ScalaPet2(petName: String) {
  def getName = petName
}


// Using val to generate the accessor:
class ScalaPet3(val name: String)


// Using var to generate the mutator:
class ScalaPet4(var name: String)


// Enumeration via pattern-matchable objects:
abstract class Breed

case object Chihuahua extends Breed

case object Collie extends Breed

case class Pinscher(isMiniature: Boolean) extends Breed

// A pattern-matchable Dog class:
case class Dog(override val name: String, breed: Breed)
  extends ScalaPet3(name)


// Playing with Dogs:
object DogMatcher extends Application {

  val d1 = Dog("Penny", Pinscher(true))

  val d2 = Dog("Chico", Chihuahua)

  val d2name = d2 match {
    case Dog(name, _) => name
  }

  println(d2name)

  // Prints:
  // Chico

  def isMinPin(d: Dog) = d match {
    case Dog(_, Pinscher(true)) => true
    case _ => false
  }

  println(isMinPin(d1))
  // Prints:
  // true

  println(isMinPin(d2))
  // Prints:
  // false
}
