#!/bin/bash
scala $0 $@
exit
!#

/*
 You can learn a lot just by seeing how many ways there are to write
 factorial in Scala.
 */



// Iteratively:
def fact0(n : Int) : Int = {
  var i = n ;
  var a = 1 ;
  while (i > 0) {
    a = a*i ;
    i -= 1 ;
  }
  return a ;
}

println("fact0(5) = " + fact0(5)) ;
// Prints:
// fact0(5) = 120

println("fact0(33) = " + fact0(33)) ;
// Prints:
// fact0(33) = -2147483648

println("fact0(34) = " + fact0(34)) ;
// Prints:
// fact0(34) = 0 


// Using BigInt:
def fact1(n : BigInt) : BigInt = {
  var i = n ;
  var a : BigInt = 1 ;
  while (i > 0) {
    a = a * i ;
    i -= 1 ;
  }
  return a ;
}

println("fact1(34) = " + fact1(34)) ;
// Prints:
// fact1(34) = 295232799039604140847618609643520000000 


// Recursively:
def fact2(n : Int) : Int = {
  if (n <= 0) {
    return 1 ;
  }
  else {
    return n * fact2(n - 1) ;
  }
}

// Tail-recursively:
def fact2tail(i: BigInt): BigInt = {
  def f(i : BigInt, accumulator : BigInt) : BigInt = {
    if (i == 0) {
       return accumulator ;
    } else {
       return f(i - 1, i * accumulator) ;
    }
  }
  return f(i,1) ;
}

println("fact2tail(34) = " + fact2tail(34)) ;
// Prints:
// fact2tail(34) = 295232799039604140847618609643520000000 


// Without returns:
def fact3(n : Int) : Int = {
  if (n <= 0) {
    1
  }
  else {
    n * fact3(n - 1) 
  }
}


// Without blocks:
def fact4(n : Int) : Int = 
  if (n <= 0) 
    1
  else 
    n * fact4(n - 1) 


// Using pattern-matching:
def fact5(n : Int) : Int = n match {
  case 0 => 1 
  case n => n * fact5(n - 1)
}


// Using fold:
def fact6(n : Int) : Int =
 (1 to n).foldRight (1) ((a : Int, b : Int) => a * b)


// With an inferred return type:
def fact7(n : Int) =
 (1 to n).foldRight (1) ((a : Int, b : Int) => a * b)


// With the anonymous function type inferred.
def fact8(n : Int) =
 (1 to n).foldRight (1) ((a,b) => a * b)


// With a parameterless anonymous function:
def fact9(n : Int) =
 (1 to n).foldRight (1) (_ * _)


// With reduce:
def fact10(n : Int) =
 (1 to n) reduceRight (_ * _)


// With the synonym for foldRight:
def fact11(n : Int) =
 ((1 to n) :\ 1) (_ * _)


