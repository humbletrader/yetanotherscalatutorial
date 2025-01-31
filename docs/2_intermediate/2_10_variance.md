# What is variance ?
When mixing OO with polymorphism, the question that arises is: how parameterized types behave under subtyping ?

Given U a subclass of T, is Container[U] a subclass of Container[T] ?
or
If U is a subclass of T, can Container[U] be substituted for Container[T] ?

# Covariance
* if A <: B then Container(A) <: Container(B)
* typically used for Producers of T

```scala mdoc
// Integer <: Number
// Option[Integer] <: Option[Number]  (check the implementation of Option[+A]
val optionOfInt : Option[java.lang.Integer] = Option(123)
val optionOfNumber : Option[java.lang.Number] = optionOfInt //covariance
```

# Contravariance
* if A <:B then Container(B) <: Container(A)
* typically used for Consumers of [T]


```scala modoc
class Writer[-T] {  //maybe a better naming should be ConsumerOf (for sure is better as a general rule)
  def write(value: T) = s" writing $value of type ${value.getClass}"
}
val nbrWriter : Writer[Number] = new Writer[Number]
val intWriter : Writer[Integer] = nbrWriter

intWriter.write
```

# Invariance
A <: B ->  no relation between T(A) and T(B)


# More examples or covariance

```scala mdoc
abstract class Animal{
  def name : String
}
abstract class Pet extends Animal
case class Cat (name : String) extends Pet
case class Dog (name : String) extends Pet

//the covariant containers (producer of Pets)
class Box[+A](wrapped: A){
  def getContents : A = wrapped
}

val gift : Box[Pet] = new Box(Cat("jerry"))
gift.getContents
```

What if I try to consume ? 
```scala mdoc:fail
//covariant container of A that forces the consumption of A ( covariant type occurs in contravariant position)
class AnotherBox[+A]{
  def add(another: A) : Boolean = true //compilation error
}
```

# More examples of contravariance

```scala mdoc
abstract class Keeper[-A]{ //note the contravariance in declaration
  def watch(a : A) : Unit
}

//a zookeeper is able to watch any kind of animal
class ZooKeeper extends Keeper[Animal]{
  override def watch(a : Animal) : Unit = println(s"I'm petting this animal ${a.name}")
}

//therefore the zookeeper is able to watch my dog
val dogKeeper : Keeper[Dog] = new ZooKeeper
dogKeeper.watch(Dog("blah"))
```

What if I try to produce Animals ? 

```scala mdoc:fail
abstract class AnotherKeeper[-A]{ //still contravariant
  def getWatched : A
}
```

# Difference between java and scala
Note: in java the variance is declared at call site
while is scala the variance is declared when defining the type
```java
//util.List[P <: Number] blah = util.Arrays.asList(Integer.valueOf(1), java.lang.Double.MIN_VALUE, java.math.BigDecimal.ONE)
//List<Number> numbers = blah //ie. blah is an extension of List<Number>
```

```scala
class List[+A]
```

# Bad implementation in java of array covariance 

```java
// bad implementation of java arrays
//Integer <: Number -> Integer[] <: Number[]
Integer i = Integer.valueOf(10);
Number n = i;
Integer[] intArray = new Integer[]{1,2,3};
Number[] nbrArray = intArray;
```
