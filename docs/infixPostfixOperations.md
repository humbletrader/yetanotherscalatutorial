
# Infix, prefix, postfix operations
Notation borrowed from mathematics:
* infix operator notation :  3 + 4
* prefix operator notation:  + 3 4
* postfix operator notation:  3 4 +

since every operation in scala is a method, we should write
```scala mdoc
3.+(4) 
```
pretty awkward. 

a better notation is the infix notation (supported by scala for methods with one argument)
```scala mdoc 
3 + 4
```

This means that any method that takes one single parameter can be written using infix notation.
```scala 
Vector(1,2,3).mkString(",")
```
we can write in infix notation
```scala
Vector(1,2,4) mkString ","
```
for multiple params methods we can avoid the . but we should use the parentheses
```scala 
Vector(1,2,3) mkString ("<", ",", ">")
```

for methods that take no arguments we can avoid the . notation but it's not a good thing
instead of
```scala
Vector(1,2,3).toList
```
we can write
```scala 
Vector(1,2,3) toList //called also postfix notation
```

see the [recommendations](https://docs.scala-lang.org/style/method-invocation.html) from scala

# Right associative operations
If a method is used in operator notation, such as a * b, 
the method is invoked on the left operand, as in a.*(b) — unless the method name ends in a colon.
If the method name ends in a colon, the method is invoked on the right operand.
Therefore, in 1 :: twoThree, the :: method is invoked on twoThree, passing in 1, like this: twoThree.::(1).

So, when we write
```scala mdoc
1::2::3::Nil
```
after de-sugaring, it's the same as
```scala mdoc
Nil.::(3).::(2).::(1)
```
which is much less readable than the infix version and sometimes more efficient 
(in the case of list prepending values is much more efficient O(1) than appending them O(n))

One last example: 
while a >> b is desugared into a.>>(b), a >>: b is desugared into b.>>:(a). So I had to define >>: as