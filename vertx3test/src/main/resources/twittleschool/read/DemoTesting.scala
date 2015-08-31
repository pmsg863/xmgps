package twittleschool.read

/*
 Demo:   A lightweight unit-testing framework.
 Author: Matthew Might
 Site:   http://matt.might.net/
         http://www.ucombinator.org/

 A home-grown unit-testing library, demonstrating the utility of
 by-name parameters.
 */


/**
 Thrown when checkThat fails.
 */
case class CheckException extends RuntimeException

/**
 Thrown when a check fails and there is information as to why.
 */
case class ReasonedCheckException(reason : String) extends CheckException



/**
 The Check object contains methods for testing.
 */
object Check {

  /**
   Determines whether checks happen at runtime.
   */
  var checksEnabled = true
  
  /**
   The === equality throws an exception on false.
   */
  abstract class CheckEquality[A] {
    def === (a : A) : Boolean
  }

  /**
   Implicitly adds === to every object in Scala.
   */
  implicit def checkEqualable[A] (a : A) : CheckEquality[A] = 
    new CheckEquality[A] {
      def === (b : A) : Boolean = {
        if (a != b) {
          throw new ReasonedCheckException(a + " != " + b) 
        }
        return true
      }
    }


  /**
   Throws an execption if its argument doesn't evaluate to true.
   */
  def checkThat (action : => Boolean) {
    if (checksEnabled) {
      if (!action)
        throw new CheckException
    }
  }
}



import twittleschool.read.Check._


/**
 Unit test suites should inherit from TestSuite.
 */
trait TestSuite {

  /**
   The test method defines new tests.
   */
  def test[A] (description : String) (action : => A) {

    try {
      action
      println ("test passed: " + description) 
      return ()
    }
    catch {
      case (e : Exception) => {
        println("test failed: " + description)
        println("exception thrown: " + e)
      }
    }
  }


}



class ArithmeticTests extends TestSuite {

  test ("2 equals 2") {
    checkThat (2 === 2)
  }

  test ("2 plus 2 equals 4") {
    checkThat (2 + 2 === 4)
  }

  test ("2 equals 3") {
    checkThat (2 === 3)
  }

}





object DemoTesting extends Application {
  (new ArithmeticTests)
}
