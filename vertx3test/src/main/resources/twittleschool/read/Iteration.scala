// Duplicate the arguments array.
val myArgs = new Array[String](args.length) ;

// Copy method 1:
for (i <- 0 until args.length) 
  myArgs.update(i,args(i))

// Syntactic sugar:
// e1(e2) = e3
//    ==>
// e1.update(e2,e3)

// Copy method 2:
for (i <- 0 until args.length) 
  myArgs(i) = args(i)

// Copy method 3:
(0 until args.length).foreach(i => myArgs(i) = args(i))

// Copy method 4:
(0 until args.length) foreach (i => myArgs(i) = args(i))


// o.m(a) == o m a
val a = 3
val b = 4
val n = a.+(b)
println(n)
// Prints:
// 7


// Create new array of chars:
val alphabet = Array("a","b","c")

val alphabetChars : Array[String] = Array[String]("a","b","c")


// Create a list:
val numbers = List(42,1701,13)
numbers foreach println
// Prints:
// 42
// 1701
// 13

// Create a new list:
val moreNumbers = 1981 :: numbers

numbers
// Prints:
// 42
// 1701
// 13

moreNumbers foreach println
// Prints:
// 1981
// 42
// 1701
// 13

def myMap[A,B] (f : A => B) (list : List[A]) : List[B] = 
  if (list.isEmpty) 
    { Nil }
  else
    { f(list.head) :: myMap (f) (list.tail) }


(myMap ((n:Int) => n + 1) (moreNumbers)) foreach println
// Prints:
// 1982
// 43
// 1702
// 14    


moreNumbers.map(n => n + 1) foreach println
// Prints:
// 1982
// 43
// 1702
// 14


moreNumbers map (_ + 1) foreach println
// Prints:
// 1982
// 43
// 1702
// 14


class Ship(val x : Int, val y : Int) extends Iterable[(String,Int)] {

  def elements = new Iterator[(String,Int)] {
    private var nextVar = 'x
    
    def next : (String,Int) = nextVar match {
      case 'x => { nextVar = 'y ; ("x",x) }
      case 'y => { nextVar = 'z ; ("y",y) }
    }

    def hasNext : Boolean = nextVar != 'z
  }

}


val Galactica = new Ship(42,1701)

for ((coord,value) <- Galactica) {
  println(coord + ": " + value)
}
// Prints:
// x: 42
// y: 1701

