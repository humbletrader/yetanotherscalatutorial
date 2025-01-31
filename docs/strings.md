# String

## String interpolation

### s strings
```scala mdoc
val name = "Dragos"
val salutation = "Hello $name"
val salutationAsSString = s"Hello $name"

//escaping the dollar sign with $$
val netWorth = 10000
val stringWithDollarSign = s"my net worth is $netWorth$$"

//evaluating expressions 
val evaluated = s"one plus one equals ${1+1}"

//method calls 
case class Person(firstName: String, lastName: String, age: Int)
val person = Person("Dragos", "B", 43)
val personDescription = s"${person.firstName} is a ${person.age} year old scala developer"
```

### f strings (scala's attempt at printf)
```scala mdoc
val age = 43
val presentationAsSString = s"My name is $name, I'm $age years old"

val height = 1.85
val presentationAsFString = f"My name is $name%s, I'm $age%2d years old and my height is $height%2.3f meters"  
```

### raw strings
```scala mdoc
val sStringWithSpecialChars = s"line\nlien"
val rawString = raw"line\nline"
```