# Partial functions
A partial function is a subclass of Function1
it only covers partially the domain of objects

```scala mdoc
val partialFunction = new PartialFunction[Int, String]{
  def apply(i: Int): String = {
    return "one"
  }
  override def isDefinedAt(x: Int): Boolean = x == 1
}
```

scala provides a shorthand for partial functions:  ( using case )
```scala mdoc
val one: PartialFunction[Int, String] = { case 1 => "one"}
one.isDefinedAt(1) 
one.isDefinedAt(2) 
one(1) 

val two : PartialFunction[Int, String] = { case 2 => "two"}
val other : PartialFunction[Int, String] = { case _ => "other value"}

val composedPartialFunction = one orElse two orElse other
composedPartialFunction(5)
```

partial functions are accepted in SOME collection methods
```scala mdoc
List(1,2,3).collect(composedPartialFunction)
```

using partial functions instead of normal ones results in compilation errors

```scala mdoc:fail 
val partial : PartialFunction[Int, String] = {case 1 => "one"}
List(1,2,3).map(partial) 
```

inline partial functions
```scala mdoc
List(1,2,3, "four").collect{case i: Int => i * 2}
```
notice how the partial function filtered the string value
as opposed to a similar method which does not require a partial function

## Seq, Map, Set are partial functions

```scala mdoc
val seqAsPartialFunction = Seq("cat", "dog", "frog")
seqAsPartialFunction.isDefinedAt(0) 
seqAsPartialFunction(0) 
seqAsPartialFunction.isDefinedAt(5)

Seq(1,2,100) collect seqAsPartialFunction 

Seq(1,2,100) collect Seq("cat", "dog", "frog") //List(dog, frog)
```

lifting partial functions

```scala mdoc
val partialFnc : PartialFunction[Int, String] = Map(1 -> "one", 2 -> "two", 3 -> "three")
val totalFunction = partialFnc.lift
totalFunction(10) //None
totalFunction(2) //two
```