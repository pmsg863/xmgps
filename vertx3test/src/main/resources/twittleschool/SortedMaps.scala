#!/bin/bash
scala $0 $@
exit
!#


/*
 Sorted maps require a comparison procedure.  

 Sorted data structures will use an 'implicit' function for converting
 to Ordering if one is in scope.

 If one is not in scope, it must be specified.
*/


import scala.collection.immutable.{SortedMap,TreeMap} ;

/**
 A Person is a social security number and a name.
 */
case class Person(val ssn : Int, val name : String) 


// val db1 : SortedMap[Person,Symbol] = TreeMap[Person,Symbol]()
// ERROR!
// Person is not ordered!


/**
 Implicitly converts a Person to an Ordered[Person], 
 using SSNs to compare.
 */
implicit object OrderingBySSN extends Ordering[Person] {
  def compare (p1 : Person, p2 : Person) : Int = p1.ssn - p2.ssn
}


val db1 : SortedMap[Person,Symbol] = TreeMap[Person,Symbol]()

val db2 = db1 + ((Person(1,"Matt")) -> 'Chicken)
val db3 = db2 + ((Person(2,"Matt")) -> 'Mouse)

println(db3)
// Prints:
// Map(Person(1,Matt) -> 'Chicken, Person(2,Matt) -> 'Mouse)




/**
 Explicitly converts a Person to an Ordering[Person],
 using names to compare.
 */
object OrderingByName extends Ordering[Person] {
  def compare (p1 : Person, p2 : Person) : Int = p1.name compare p2.name
}


val dbX : SortedMap[Person,Symbol] = 
 TreeMap[Person,Symbol]()(OrderingByName)

val dbY = dbX + ((Person(1,"Matt")) -> 'Chicken)
val dbZ = dbY + ((Person(2,"Matt")) -> 'Mouse)

println(dbZ)
// Prints:
// Map(Person(2,Matt) -> 'Mouse)







