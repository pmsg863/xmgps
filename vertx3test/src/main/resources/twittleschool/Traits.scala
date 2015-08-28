#!/bin/bash
scala $0 $@
exit
!#


/*
 Traits allow multiple inheritance is Scala.

 The order of inheritance is important!
 */


class Animal {
  def printName () {
  }
}


trait Man extends Animal {
  def height = 6 ;

  override def printName() {
    super.printName() ;
    println("Man") ;
  }
}


trait Bear extends Animal {
  val color = 'brown ;

  override def printName() {
    super.printName() ;
    println("Bear") ;
  }
}


trait Pig extends Animal {
  val shape = 'round ;

  override def printName() {
    super.printName() ;
    println("Pig") ;
  }
}



class ManBearPig extends Man with Bear with Pig 
class BearManPig extends Bear with Man with Pig
class PigBearMan extends Pig with Bear with Man
class PigManBear extends Pig with Man with Bear
class ManPigBear extends Man with Pig with Bear
class BearPigMan extends Bear with Pig with Man

(new ManBearPig).printName ;
// Prints:
// Man
// Bear
// Pig

(new BearPigMan).printName ;
// Prints:
// Bear
// Pig
// Man

(new Animal with Man with Bear).printName ;
// Prints:
// Man
// Bear

