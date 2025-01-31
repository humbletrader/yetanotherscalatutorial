## PARTIAL APPLICATION

```scala mdoc 
//given a function like 
def addTwoNumbers(a: Int, b: Int) : Int = a + b

// we can partially apply the function for one argument
val addSeven = addTwoNumbers(7, _ : Int)
addSeven(8)

// we can do the same partial application for functions with more than two arguments
def multiply3(a: Int, b: Int, c: Int) : Int = a * b * c

val multiply2 = multiply3(1, _, _)

//using a partially applied function to get the reference to a method
val addTwoNumbersAsValFunction = addTwoNumbers _
//having the method translated into a function you can curry it 
addTwoNumbersAsValFunction.curried

```