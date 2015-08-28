package com.xmgps.yfzx.hwb.scala

/*
 Demo:   Solving algebraic equations with custom patterns.
 Author: Matthew Might
 Site:   http://matt.might.net/
         http://www.ucombinator.org/
 */


/* 
 Scala allows programmers to define custom case constructors and
 deconstructors using the apply and unapply methods.

 Deconstructors are invoked to take a value apart during pattern
 matching with 'match' and 'case'.

 If the method apply has type X -> Y, then the method unapply should
 have the type Y -> Option[X].

 The intended contract for these methods is 

  (1) unapply(apply(x)) == Some(x), and

  (2) if unapply(y) = Some(x), then apply(x) == y.

 One use of this mechanism is to prevent grep/replace-programming when
 the definition of a case class's constructor changes.

 Custom patterns can also be used to match on the semantics of a
 value, rather than just its structure.
 */



object Succ {
  def apply (x  : Double) : Double = 
    x + 1.0

  // matches: _ + 1.0
  def unapply (x : Double) : Option[Double] = 
    Some(x - 1.0)
}


object Pred {
  def apply (x  : Double) : Double = 
    x - 1.0

  // matches: _ - 1.0
  def unapply (x : Double) : Option[Double] =
    Some(x + 1.0)  
}


object Square {
  def apply(x : Double) : Double = x * x

  // matches: _ ^ 2
  def unapply (x : Double) : Option[Double] = 
    Some(Math.sqrt(x))
}


object By2 {
  def apply(x : Double) : Double = x * 2.0

  // matches: _ * 2.0
  def unapply (x : Double) : Option[Double] = 
    Some(x / 2.0)
}


object Over2 {
  def apply(x : Double) : Double = x / 2.0

  // matches: _ / 2.0
  def unapply (x : Double) : Option[Double] = 
    Some(x * 2.0)
}



object DemoAlgebra extends Application {

  // Solve for x: 
  // 145 = x^2 + 1

  145.0 match {
    case Succ(Square(x)) => println(x)
  }
  // Prints:
  // 12.0


  // A pattern-generator:
  def NSucc(n : Double) = new Object {
    def unapply (x : Double) = Some(x - n)
  }

  val NSucc30 = NSucc(30)

  100.0 match {
    case NSucc30(x) => println(x)
  }
  // Prints:
  // 70.0

}
