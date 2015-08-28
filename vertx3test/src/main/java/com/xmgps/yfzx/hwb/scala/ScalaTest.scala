package com.xmgps.yfzx.hwb.scala

/* An object with a main method is a program. */

object ScalaTest {
  class Animal {
    def printName () {
    }

    def apply(x : Int) = x+2

    // case blocks also act as functions:
    val myCaseID : Int => Int = {
      case x => x+3
    }
  }

  trait Bear extends Animal {
    val color = 'brown ;

    override def printName() {
      super.printName() ;
      println("Bear") ;
    }
  }

  class BearPigMan extends Bear
  (new BearPigMan).printName ;
  println( (new BearPigMan).apply(12))
  println( (new BearPigMan).myCaseID(3));


  def main (args : Array[String]) {

    // Integers:
    val anInt = 3
    // Floating point:
    val aDouble = 4.0
    // Charaters:
    val aCharacter = 'c'
    // Strings:
    val aString = "Google"
    // Symbols:
    val aSymbol = 'foo
    // XML:
    val anXMLElement = <a href="http://www.google.com/">{aString}</a>
    // Tuples:
    val aPair = (aString,aDouble)
    // Lists:
    val aList = List(1,2,3,4)
    // Ranges:
    val aRange = 1 to 5
    // Maps:
    val aMap = Map(3 -> "foo", 4 -> "bar")
    // Sets:
    val aSet = Set(8,9,10)
    // Arrays:
    val anArray = Array(1,2,3,5)
    // Unit:
    val unit = ()
    // Null:
    val nullValue = null
    // Functions:
    def incImplicit(x : Int ) = x + 1

    val incAnonymous = ((x : Int) => x + 1)
    println("Hello, World!"+incAnonymous(2))
    anArray.foreach(println)
    anArray.map(n => n + 1) foreach println
    // Duplicate the arguments array.
    val anStringArray = Array("1","2","3","4","5")
    val myArgs = new Array[String](anStringArray.length)
    (0 until args.length) foreach (i => myArgs(i) = anStringArray(i))
  }

}
