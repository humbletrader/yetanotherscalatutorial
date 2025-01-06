# Functions

## function definition

```scala mdoc 
//classic function definition
def addOne(x: Int): Int = x + 1

//function definition with inferred return type
def addTwo(x: Int) = x+2
addTwo(7)

//classic no params function definition
def makeComputation(): Int = 1+2
makeComputation()
//in scala you can skip the parenthesis for functions without params
makeComputation

//for functions without return value (i.e.Unit) we can also use a def with no equals at the end
// but this is a bad practice
def fncNoReturnValue(in: Int){
println(in)
//no return
}

//this is not a function (see the ValuesAndVariables)
def thisIsNotAFunction = 2+3
thisIsNotAFunction.getClass
```

## functions are objects

```scala mdoc 
object add extends Function2[Int, Int, Int] {
  def apply(nbr1: Int, nbr2: Int): Int = {
    nbr1+nbr2
  }
}

add(2,3)

// A nice short-hand for extends Function1[Int, Int] is extends (Int => Int)
class AddOne extends (Int => Int) {
  def apply(m: Int): Int = m + 1
}

val fnc = new AddOne
fnc(7)
```

## ANONYMOUS FUNCTIONS

```scala mdoc
(x: Int) => x + 7

//anonymous function spanning multiple lines
{i : Int =>
  println("this is the first line")
  i * 2
}

// save an anonymous function into a val
val thisIsAFunction = (i:Int) => i * 2
thisIsAFunction.getClass
```



## REFERENCES TO FUNCTIONS

```scala mdoc
def addFive(a: Int) : Int = a + 5
val referenceToAddFive = addFive _

def addTwoNbrs(a: Int, b: Int) = a + b
val referenceToAddNbrs = addTwoNbrs _

//function composition using refernces
val f = (i: Int) => i.toString
val g = (s: String) => s.toCharArray
val fog = f andThen g //infix notation used for readability
fog(10)

```

## Functions with VARIABLE LENGTH ARGS

```scala mdoc 
def yetAnotherFunction(vars : Int*) : Unit = {
  vars.foreach(println)
}

```

## Functions with multiple parameter lists



## Functions vs methods






