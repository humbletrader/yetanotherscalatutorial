# Basic Data Types in Scala

![the type hierarchy in scala](https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg)


## Byte, Short, Int, Long, Double, Float, Char, String, Boolean
```scala mdoc
//8 bit signed value. Range from -128 to 127
val b: Byte = 1 

//16 bit signed value. Range -32768 to 32767
val s: Short = 1

//32 bit signed value. Range -2147483648 to 2147483647 
val x: Int = 1  

//64 bit signed value. -9223372036854775808 to 9223372036854775807
val l: Long = 1  

//64 bit IEEE 754 double-precision float
val d: Double = 2.0 

//32 bit IEEE 754 single-precision float 
val f: Float = 3.0f 

//16 bit unsigned Unicode character. Range from U+0000 to U+FFFF
val c: Char = 'c' 

//A sequence of Chars
val str: String = "abc" 

//Either the literal true or the literal false
val truth : Boolean = true 
```

## Unit
Unit is a subtype of scala.AnyVal.
There is only one value of type Unit, (), and it is not represented by any object
in the underlying runtime system.
A method with return type Unit is analogous to a Java method which is declared void.
```scala mdoc
val noValue: Unit = ()
```

## Null
Null is a trait whose single instance is null
```scala
val nullValue : Null = null //null or empty reference
```

## Nothing
Nothing is The subtype of every other type; includes no values
 It is characterized by two properties:
 Nothing is a subtype of every other type (including Null).
  There exist no instances of this type.
One other use of Nothing is as a return type for methods that never return.
It makes sense if you think about it. If a method’s return type is Nothing, and there exists absolutely no instance of Nothing, then such a method must never return.

```scala
val notin : Nothing  // this one cannot be instatiated
//notin.isInstanceOf[String] // should have been true (if an instance could have been created)
```

While Nothing is a subtype of everything, it does not inherit
any method except for those in Any. This is because Nothing is more geared
toward the functional end of the language. 
It's necessary for things like Option and List, but only as a type, not as a class.


## Any
Any is the supertype of the entire scala system
```scala mdoc
val any: Any = 234
```

## AnyRef
AnyRef is the supertype of any reference type. It's the closest to java.lang.Object

## AnyVal
```scala mdoc
val anyRef: AnyRef = "abc"

val anyVal: AnyVal = 1
```





