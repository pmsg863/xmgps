package com.xmgps.yfzx.hwb.scala

import scala.collection.convert.Wrappers.ConcurrentMapWrapper
import scala.collection.immutable.{HashSet, HashMap}

/* An object with a main method is a program. */

object ScalaTest {
  class Animal {
    def printName () {}

    def foo(x:Int,y:Int) = x+y

    def apply(x : Int) = x+2

    // case blocks also act as functions:
    val myCaseID : Int => Int = {
      case x => x+3
    }
  }

  class CollTest{
    def printArray (): Unit = {
      var array = new Array[String](3)
      array(0)="hello"
      array(1)="my"
      array(2)="world"
      for(i<-0 to 2){
        println(array(i))
      }
    }

    def printMap (): Unit ={
      var map = HashMap(1->"a",2->"b",3->"c")
      map.+(4->"d")
      map +(5->"e")
      map.foreach(value=>print(value+" "))
    }

    def printList (): Unit ={
      var list = List("111","222","333")
      var list2 = List("aaa","bbb")
      list2 +("ccc")
      var list3 = list ::: list2
      list3.foreach(value=>print(value+" "))
    }

    def printSet (): Unit = {
      var set = HashSet("1","2","3");
      set+="4"
      set.foreach(value=>print(value+" "))
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

    var collTest = new CollTest
    collTest.printArray()
    collTest.printList()
    collTest.printMap()
    collTest.printSet()

    def before(a: Int, b: Int, c: Int, d: Int): Unit = {
      println(a+b+c+d)
    }
    def middle(a: Int)(b: Int, c: Int, d: Int): Unit = {
      println(a+b+c+d)
    }
    def after(a: Int)(b: Int)(c: Int)(d: Int) { println(a+b+c+d)     }
    println("function value和闭包")
    var foo =after _
    //避免第一个参数的重复定义
    var bar =after(1)_
    bar(1)(3)(4)
    //可以使用{}调用
    bar(2)(3){5}

    //闭包：
    def loopThrough(number: Int)(closure: Int => Unit) {
      for (i <- 1 to number) { closure(i) }
    }
    var result = 0
    val addIt = { value:Int => result += value }


    loopThrough(10) { addIt }
    println("Total of values from 1 to 10 is " + result)

    loopThrough(5) { addIt }
    println("Total of values from 1 to 5 is " + result)
    //结果是55,75 说明作用域外的赋值影响了result

  }

}
