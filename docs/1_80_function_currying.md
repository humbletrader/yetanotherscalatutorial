# CURRIED FUNCTIONS
curried functions are functions that return functions

Currying is a special version of the partially applied function. Not all partially applied functions are curried functions but all curried functions are partially applied function.

[check this](https://www.scalamatters.io/post/partially-applied-functions-and-currying)

```scala mdoc 
//given an existing function
def multiply(a: Int, b: Int) : Int = a * b

//we can curry it using the "curried" method in Function2, Function3 ...etc
val curriedMultiply = (multiply _).curried
val timesTwo = curriedMultiply(2)
timesTwo(3)

//or we can define a function with multiple parameter list 
//this is also a curried function but there's a slight difference see below
def alreadyCurriedMultiply(a: Int)(b: Int) : Int = a * b
val timesThree = alreadyCurriedMultiply(3) _
timesThree(7)
```

## Difference between function.curried and multi param list functions 

The difference appears in Multi stage computations [see StackOverflow for details](https://stackoverflow.com/questions/4915027/two-ways-of-currying-in-scala-whats-the-use-case-for-each)
```scala mdoc

//given an expensive computation
def retrieveUser(userId: Int) : String = {
  println("network and heavy db stuff here")
  "Dragos"
}

//and some non heavy work
def addPrefix(firstName: String, prefix: String) : String = 
  prefix + " " + firstName

def useBothFunctions(userId: Int, prefix: String) : String = {
  val name = retrieveUser(userId)
  addPrefix(name, prefix)
} 

//and you need to call it multiple times 
//therefore doing the expensive stuff multiple times
useBothFunctions(1, "Mr")
useBothFunctions(1, "Sir")


//a better approach would be to curry
def curriedVersion(userId: Int) : String => String = { 
  val expensiveResult = retrieveUser(userId)
  (b: String) => addPrefix(expensiveResult, b)
}

//please note the single expensive call over the network
val prefixerCurried = curriedVersion(7)
prefixerCurried("Mr")
prefixerCurried("Sir")

//a solution based on multi-params-list will not work 
def multiParamListVersion(userId: Int)(prefix: String) : String = {
  val user = retrieveUser(userId)
  addPrefix(user, prefix)
}

//please note the two expensive calls below
val retrieverAndPrefixerMultiParamList = multiParamListVersion(7) _
retrieverAndPrefixerMultiParamList("Mr")
retrieverAndPrefixerMultiParamList("Sir")

```

