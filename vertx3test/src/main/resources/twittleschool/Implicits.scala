#!/bin/bash
scala $0 $@
exit
!#

/*
 A demonstration of implicits for embedding 
 domain-specific languages in Scala.  In this
 case, the DSL creates an AST for regular expressions.

 Exercise: Implement a matchesString method for RegEx
 */


abstract class RegEx {
  def ~ (right : RegEx) = Sequence(this,right)
  def || (right : RegEx) = Alternation(this,right)
  def * = Repetition(this)

  def matchesString(s : String) : Boolean =
    throw new Exception("An exercise for the reader!")
}

// Charaters:
case class CharEx(val c : Char)
 extends RegEx

// Sequences:
case class Sequence (val left : RegEx, val right : RegEx)
 extends RegEx

// Alternation:
case class Alternation (val left : RegEx, val right : RegEx) 
 extends RegEx

// Kleene repetition:
case class Repetition (val exp : RegEx)
 extends RegEx

// Empty:
case object Empty extends RegEx


// Building regex's manually is cumbersome:
val rx1 = Sequence(CharEx('f'),Sequence(CharEx('o'),CharEx('o')))


// Automatically convert strings into regexes:
implicit def stringToRegEx(s : String) : RegEx = {
  var ex : RegEx = Empty
  for (c <- s) {
    ex = Sequence(ex,CharEx(c))
  }
  ex
}

// Implicits + operator overloading makes the syntax terse:
val rx2 = "baz" ~ ("foo" || "bar") * ;
println(rx2)
// Prints:
// Repetition(Sequence(Sequence(Sequence(Sequence(Empty,CharEx(b)),CharEx(a)),CharEx(z)),Alternation(Sequence(Sequence(Sequence(Empty,CharEx(f)),CharEx(o)),CharEx(o)),Sequence(Sequence(Sequence(Empty,CharEx(b)),CharEx(a)),CharEx(r)))))
