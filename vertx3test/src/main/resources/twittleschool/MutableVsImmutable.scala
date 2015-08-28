#!/bin/bash
scala $0 $@
exit
!#


/*
 Note the difference between how the update behaves for immutable and
 mutable data structures. 
 */


val mutHashMap = new scala.collection.mutable.HashMap[String,Int]
val immHashMap = new scala.collection.immutable.HashMap[String,Int]

mutHashMap("Foo") = 1
immHashMap("Foo") = 1

println(mutHashMap)
// Prints:
// Map(Foo -> 1)

println(immHashMap)
// Prints:
// Map()

println(immHashMap("Foo") = 1)
// Prints
// Map(Foo -> 1)

