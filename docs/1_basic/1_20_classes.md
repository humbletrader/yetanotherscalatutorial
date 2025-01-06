# class definition 

```scala mdoc
class Calculator {
val brand = "DELL" //public by default
def compute(a: Int, b: Int) = a+b //also public
}

val calc = new Calculator
calc.brand
calc.compute(1,2)

```

# VALS, VARS in CONSTRUCTORS

```scala mdoc
class Computer (brand : String) {
  //a constructor in scala = all code outside methods
  val color = if (brand == "APPLE") "white" else "black"

  //instance method (nothing to do with the constructor)
  def compute(a: Int, b: Int) = a + b

  //this is also part of the constructor
  val chipMaker = if (brand == "DELL") {
    println("trying to detect the manufacturer of the chip in the constructor")
    "Intel"
  } else {
    println("trying to detect the manufacturer of the chip in the constructor")
    "some other manufacturer"
  }
}

val anotherCalc = new Computer("APPLE")
anotherCalc.color
anotherCalc.chipMaker
//brand ( the parameter send to the constructor is not available)
//anotherCalc.brand
```

## val in constructor  
please note the val below (which makes the brand available as an instance value)

```scala mdoc 
class ComputerWithBrand( val brand: String) {

}

val thirdTypeOfComputer = new ComputerWithBrand("HP")
thirdTypeOfComputer.brand

```

```scala mdoc
class ComputerWithVarBrand( var brand: String){

}

val fourthTypeOfComputer = new ComputerWithVarBrand("blah")
fourthTypeOfComputer.brand
fourthTypeOfComputer.brand = "APPLE"
fourthTypeOfComputer.brand

```





##  AUXILIARY CONSTRUCTORS

```scala mdoc
class Person(firstName: String, lastName: String){

  //this is an auxiliary constructor
  def this(firstName: String) = {
    this(firstName, "Unknown")
  }
}
```


## constructors with defaults

```scala mdoc
class PersonWithDefaults( firstName: String = "Unknown", lastName: String = "Unknown"){

}

//constructors with default encourage the named parameters
val me = new PersonWithDefaults(lastName = "Dragos")
```


 # inheritance

```scala mdoc
class Animal (val name: String, val legCount:Int) {}

class Cat (name: String) extends Animal(name, legCount = 4) {}

val myCat = new Cat("Sisi")
println(myCat.legCount)
```



## How to define methods

```scala mdoc
class VariousWaysToDefineMethods{

  //the common case
  def test1(param : String) : String = "test with equal"

  def test2(param: String) = { // no equal sign if the method returns Unit
    print("blah")
  }

  def test3 : String => String = (str : String) => "function"
}

```
