# Avoid abstract val in traits                
//copied from "Scala in depth" chapter 4 - Utilizing object orientation, section 4.1.2, page 71

```scala mdoc
trait Property{
  val name: String = "caca"
  override val toString = "Property(" + name + ")"
  
  println("during construction of Property "+name +" "+toString)
}

val x = new Property{ override val name = "Hi"} 
x.toString
x.name
```
# Explanation
A ‘strict’ or ‘eager’ val is one which is not marked lazy.

In the absence of “early definitions”, initialization of strict vals is done in the following order.

* Superclasses are fully initialized before subclasses.
* Otherwise, in declaration order.

Naturally when a val is overridden, it is not initialized more than once. So though x2 in the above example is seemingly defined at every point, this is not the case: an overridden val will appear to be null during the construction of superclasses, as will an abstract val.

## Another example and solutions on scala-lang.org
[copied from here](https://docs.scala-lang.org/tutorials/FAQ/initialization-order.html)

```scala mdoc
abstract class A {
  val x1: String
  val x2: String = "mom"

  println("A: " + x1 + ", " + x2)
}

class B extends A {
  val x1: String = "hello"

  println("B: " + x1 + ", " + x2)
}

class C extends B {
  override val x2: String = "dad"
  println("C: " + x1 + ", " + x2)
}

new C

new A{
  val x1 : String = "blah"
  println("Anon: "+x1 + ", "+x2)
}
```