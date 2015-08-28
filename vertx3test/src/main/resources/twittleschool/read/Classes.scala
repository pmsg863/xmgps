#!/bin/bash
scala $0 $@
exit
!#

/*
 Classes in Scala are similar to classes in Java, with
 several convenient shorthands.
 */


// Create an empty class A:
class A


// Create a subclass of A, B:
class B extends A


// A class with an immutable field:
class C {
  val field = 3
}


// Classes take parameters:
class D(x : Int) {
  val field = x
}


// Parameters can also be fields:
class E(val field : Int) 


// Parameters can be mutable fields:
class F(var field : Int)
val f = new F(3)
f.field = 20


// Parameters and fields can also be private:
class G(private val initialX : Int) {
  private var myPrivateX = initialX
}


// Fields may be artificial:
class H {
  private var realX = 0 ;

  def x = realX

  // called for "this.x = <value>":
  def x_=(newX : Int) {
    this.realX = newX
  }
}

val h = new H

h.x = 3
println(h.x) // prints 3


// Fields can be lazy:
class I {
  lazy val x = { println("called x") ; 3 }
}

val i = new I
println(i.x) // prints "called x"; "3"
println(i.x) // prints 3


// Case classes allow pattern matching:
class JK

case class J(x : Int) extends JK 
case class K(s : String) extends JK 

val jk : JK = J(3) // Note the lack of new

jk match {
  case K("foo") => println("foo") 
  case J(0)     => println("0")
  case J(n)     => println(n+1)
}

// Prints:
// 4


// Classes can extend multiple traits:
trait L {
  def f() { println("foo") }
}
trait M {
  def g() { println("bar") }
}

class N extends L with M

val n = new N
n.f()
n.g()
// Prints:
// foo
// bar


// Companion objects take the place of static fields:
class O(val value : String) 

object O {
  def from(value : String) : O = new O(value)
}


// Objects can have a type too:
class P {
  def f() {
    println("foo") 
  }
}

object myObject extends P   
myObject.f()
// Prints:
// foo


// Case objects work in pattern matching:
class Bool

case object TRUE extends Bool
case object FALSE extends Bool

val b : Bool = TRUE

b match {
  case FALSE => println("It was false!")
  case TRUE  => println("It was true!")
}
// Prints:
// It was true!

