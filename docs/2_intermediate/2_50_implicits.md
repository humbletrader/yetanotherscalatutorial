# Implicits 
 
## Implicits use cases (inspired by "Programming Scala, 2nd edition, Dan Wampler")

### USE CASE 1: implicit context
todo

### USE CASE 2: capabilities
todo

### use case 3: constraining allowed instances
todo

### use case 4: implicit evidence
todo

### use case 5: working around type erasure
todo

## Implicit conversions
[taken from here](http://www.artima.com/pins1ed/implicit-conversions-and-parameters.html)

```scala mdoc
case class Dollar(amount: Double)
case class Euro(amount: Double)

implicit def dollarsToEuros(dolar: Dollar): Euro = Euro(dolar.amount * 1.2)

//implicit conversion at work
val euroInstance: Euro = Dollar(100)
println(euroInstance.amount) // prints 120

//the code above is equivalent to
val euroInstance2: Euro = dollarsToEuros(Dollar(100))
println(euroInstance2.amount)

//another advantage is the implicit conversion
def functionWhichAcceptsOnlyEuros(euro: Euro) = println(euro.amount)

functionWhichAcceptsOnlyEuros(Dollar(10))
//which is equivalent to
functionWhichAcceptsOnlyEuros(dollarsToEuros(Dollar(10)))
```

What happens when two implicit methods can be used by the compiler ?

```scala mdoc:fail
implicit def eurosToDollars(euro: Euro) : Dollar = Dollar(euro.amount * 10/12)
implicit def eurosToNewDollars(euro: Euro) : Dollar = Dollar(1)

val dollarInstance: Dollar = Euro(120) 
```

## Implicit parameters
[see the scala official docs for this](https://docs.scala-lang.org/tour/implicit-parameters.html)



