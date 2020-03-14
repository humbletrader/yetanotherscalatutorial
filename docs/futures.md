# Scala futures

## Execution context
When working with Futures an ExecutionContext is needed. 
```scala mdoc
implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
```

```scala mdoc

case class RawCoffee()
case class GroundCoffee()
case class CupOfCoffee()
case class Poop()

import scala.concurrent.Future

def makeCoffeeCup(groundCoffee : GroundCoffee) : Future[CupOfCoffee] = {
    Future{
        println("started brewing coffee")
        Thread.sleep(1000)
        println("coffee cup ready")
        CupOfCoffee()        
    }    
}

def drink(coffee: CupOfCoffee) : Poop = {
    println("drinking coffee makes me poop")
    Poop()
}
```

## map (continuation of a Future)
Transforms a successful Future[A] into another Future[B] after the first has completed

```scala mdoc
val potentialResult = makeCoffeeCup(GroundCoffee()).map(drink)
```

## flatMap (sequencing of futures)
turns a successful Future[A] into another Future[B]

## zip (combination of futures)
zip combines 2 successful Future into one single Future. 
The evaluation of two futures is done in parallel

```scala mdoc
    val groundCoffee = GroundCoffee()
    makeCoffeeCup(groundCoffee).zip(makeCoffeeCup(groundCoffee)).foreach(println)
```

## recover, recoverWith (recovering from Errors)


## lifting a function to a Future via Promise


## sequencing


## for comprehensions for Futures
Ensures the top-down approach for async programs


## promises
Helps you transform the continuation-passing-style functions into Futures
