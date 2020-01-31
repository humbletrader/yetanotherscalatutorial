# Values and Variables In Scala

## Definition of an immutable value
```scala mdoc
val one: Int = 1
```

## Definition of a mutable value
```scala mdoc
var mutableOne : Int = 1
```

## immutable value definition having the type inferred from the right hand side
```scala mdoc:fail
val two =  1+1
two = 3

var three = 1+2 //type inferred from right
three = 4
```

## the third kind of variables is def
```scala mdoc:fail
def four = 4
four = 5
```

## Vals, vars, defs together
* val: defines an immutable label for the right side content which is eagerly/immediately evaluated only once (i.e. evaluated by value).
* var: defines a mutable variable, initially set to the evaluated right side content
* def: defines an immutable label for the right side content which is lazily evaluated every time it is called (e.g. evaluation by name)
* lazy val- immutable label for the right hand side content which is eagerly evaluated the first time it is called

### val evaluated immediately and only once
```scala mdoc
val proofVal = {
  println("the proofVal expression is being evaluated")
  util.Random.nextInt
}
```
### var evaluated immediately only once
```scala mdoc
var proofVar = {
  println("the proofVar expression is being evaluated")
  util.Random.nextInt
}
```

### lazy val evaluated at the fist use (in REPL may not be obvious)
```scala mdoc
lazy val proofLazyVal = {
  println("the proofLazyVal expression is being evaluated")
  util.Random.nextInt
}
//this should trigger the evaluation of lazyVal
println(proofLazyVal) 

//same value as above
println(proofLazyVal) 
```

### def evaluated every time

```scala mdoc
def proofDef = {
  println("the proofDef expression is being evaluated")
  util.Random.nextInt
}
println(proofDef) //first use returns a number
println(proofDef) //second use returns another number ( proof that the expression is evaluated every time)
```


