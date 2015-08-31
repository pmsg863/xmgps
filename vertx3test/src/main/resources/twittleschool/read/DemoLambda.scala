package twittleschool.read

/*
 Author: Matthew Might
 Site:   http://matt.might.net/
         http://www.ucombinator.org/


 This file demonstrates a domain-specific language (DSL) embedded in
 Scala.

 The function Exp.free demonstrates functional, pattern-matching-based
 tree-walking, whle Exp.reduced demonstrates object-oriented
 tree-walking.

*/


/**
 An Exp object can be either a variable reference, a lambda term or an
 application.
 */
abstract class Exp {

  /**
   Creates an application term when one term is applied to another.
   Serves as syntactic sugar for the DSL.
   */
  def apply(arg : Exp) : Exp = App(this,arg)

  /**
   Produces a term with subst._1 replaced with subst._2
   */
  def apply(subst : (Symbol,Exp)) : Exp ;

  /**
   Returns this term as fully call-by-value reduced as possible.
   */
  def reduced : Exp ;
}


/**
 A Lam term has a parameter variable and a body expression.
 */
case class Lam (v : Symbol, body : Exp) extends Exp {

  def apply(subst : (Symbol,Exp)) = 
    if (v == subst._1)
      this
    else if (Exp.free(body) contains v)
      throw new Exception("Variable capture while substituting " + 
                          subst._2 + " for " + subst._1 +
                          " in " +  body)
    else
      Lam(v,body(subst))
  
  def reduced = this

  override def toString = "(lambda ("+v.name+") "+body+")"
}


/**
 A Ref term evaluates to the value of the variable.
 */
case class Ref(v : Symbol) extends Exp {
  
  def apply(subst  : (Symbol,Exp)) = 
    if (subst._1 == v)
      subst._2 
    else
      this

  def reduced = this

  override def toString = v.name
}


/**
 An App term encodes a function call.
 */
case class App(f : Exp, e : Exp) extends Exp {
  
  def apply(subst : (Symbol,Exp)) =
    App(f(subst), e(subst))

  def reduced = f.reduced match {
    case Lam(v,body) => body (v -> e.reduced)
    case _ => this
  }

  override def toString = "("+f+" "+e+")"
}


/**
 Exp is a companion object for class Exp that contains helper methods.
 */
object Exp {

  /**
   Returns the free variables inside a term.
   */
  def free (e : Exp) : Set[Symbol] = e match {
    case Ref(v) => Set(v)
    case Lam(v,body) => free(body) - v
    case App(f,e) => free(f) ++ free(e)
  }

  /**
   Sugar for lambda.
   */
  def λ (v : Symbol) (body : Exp) : Exp = 
    Lam(v,body)


  /**
   More sugar for lambda.
   */
  implicit def λ (f : Ref => Exp) : Exp = {
    genSymCounter = genSymCounter + 1
    val s = Symbol("$v" + genSymCounter)
    Lam(s,f(Ref(s)))
  }


  /**
   A counter for generated symbols.
   */
  private var genSymCounter = 0


  /**
   Abstractable objects become lambda terms when coupled with an expression.
   */
  abstract class Abstractable {
    def :-> (body : Exp) : Lam ;
  }

  /**
   Provides a Scala-like way of writing anonymous functions.
   */
  implicit def symbolToAbstractable (s : Symbol) : Abstractable = 
    new Abstractable {
      def :-> (body : Exp) : Lam = 
        Lam(s,body)
    }

  /**
   Sugar for references.
   */
  implicit def symbolToRef(s : Symbol) : Exp = 
    Ref(s)

  
}



object DemoLambda extends Application {

  import Exp._

  // Identity function:
  val id = λ ('x) ('x)

  // U combinator:
  val U = λ (f => f(f)) 

  val U2 = λ {h => h(h)}

  val U3 : Exp = (f : Ref) => f(f)

  val U4 = 'f :-> 'f('f)


  // Identity applied to identity:
  val idid = U(id)

  // Identity applied to z:
  val appz = id('z)

  // f applied to x:
  val appfx = 'f('x)
  

  println(id)
  // Prints:
  // (lambda (x) x)

  println(idid)
  // Prints:
  // ((lambda ($v1) ($v1 $v1)) (lambda (x) x))

  println(appz)
  // Prints:
  // ((lambda (x) x) z)  
  
  println(appfx)
  // Prints:
  // (f x)
  
  println(free(appz))
  // Prints:
  // Set('z)

  println(idid.reduced)
  // Prints:
  // (lambda (x) x)
  
  println(appz.reduced)
  // z
}



