# Higher Kinded Types (HKT) 
  
## Type constructors 
 A type constructor is anything that has a type parameter
We distinguish between regular types that have no holes and “type constructors” that have holes we can fill to produce types.

## Examples of type constructors 
 List is a type constructor (takes one parameter and produces a type: List is a type constructor for List[String] or List[Int])

 List[Int] is a type (produced using a type constructor with Int as parameter)
If we use the * as notation for "type"  then * -> * is a type constructor (i.e. based on a type produces another type)

## Kinds 
These kinds of types (ie. type, type constructor) are known as kinds  

## Discovering kinds in REPL
Investigating these types of types in REPL can be done like: 

```sbtshell
scala> :kind String
String's kind is A

scala> :kind List
List's kind is F[+A]

scala> :kind List[Int]
List[Int]'s kind is A

scala> :kind -v List[Option[String]]
List[Option[String]]'s kind is A

scala> :kind -v String
String's kind is A
*
This is a proper type.

scala> :kind -v List
List's kind is F[+A]
* -(+)-> *
This is a type constructor: a 1st-order-kinded type.

scala> :kind -v List[Option[String]]
List[Option[String]]'s kind is A
*
This is a proper type.

scala> :kind -v Map
Map's kind is F[A1,+A2]
* -> * -(+)-> *
This is a type constructor: a 1st-order-kinded type.
```
 
## Higher Kinded types 
 A higher kinded type is an abstraction over parameterized types. 
 They are type constructors that take/return type constructors as parameters. 
 HKT gives us is the ability to generalize across type constructors

## Examples
```scala mdoc
trait HigherKindType[F[_]]{
}

trait Functor[F[_]]{ //this new type takes a kind as a parameter
  def map[A, B](fa: F[A])(f: A => B) : F[B]
}

trait Container[M[_]]{
    def put[A](x: A) : M[A]
    def get[A](m: M[A]) : A
}
```

## Discovering HKT in REPL
```sbtshell
scala> trait HigherKindType[F[_]]
defined trait HigherKindType

scala> :kind HigherKindType
HigherKindType's kind is X[F[A]]

scala> :kind -v HigherKindType
HigherKindType's kind is X[F[A]]
(* -> *) -> *
This is a type constructor that takes type constructor(s): a higher-kinded type.
```

## Higher kinded types can be seen as high order functions of types
A higher order function: a function which takes and/or returns another function. 
Same with type constructors: you can have a type constructor, which takes and/or returns another type constructor.

Type | Kind | Comment
--- | --- | ---
String | * | this is a simple type
List | * -> * | type constructor 
HigherKindType | (* -> *) -> * | higher kinded type

Usually the implementation of such higher kinded type is done with implicits

```scala mdoc
implicit val listContainer = new Container[List]{
    def put[A](x: A) = List(x)
    def get[A](m: List[A]) = m.head
}

implicit val optionContainer = new Container[Some]{
    def put[A](x: A) = Some(x)
    def get[A](m: Some[A]) = m.get
}
```
so that functions taking HKTs as parameters 
```scala mdoc
def tupelize[M[_]: Container, A, B](first: M[A], second:M[B]) = {
    val c = implicitly[Container[M]]
    c.put(c.get(first), c.get(second))
}
```
will be able to handle all types of HKTs 
```scala mdoc
tupelize(Some(1), Some(2))
tupelize(List(1), List(2))    
```

## Utility
Say we have some standard pattern for our data-structures where we want to be able to consistently apply an operation of the same shape. Functors are a nice example, the covariant functor allows us to take a box holding things of type A, and a function of A => B and get back a box holding things of type B.
In Java, there is no way to specify that these things share a common interface, or that we simply want transformable boxes. We need to either make this static eg. Guava’s Lists and Iterables, or bespoke on the interface, eg: fugue’s Option or atlassian-util-concurrent’s Promise. There is simply no way to unify these methods on either some super interface or to specify that you have – or require – a “mappable/transformable” box.

## Can we define the same HKT with normal types
Yes we can, but is far less descriptive and there are some 

## Links
[Scala: types of a higher kind](https://www.atlassian.com/blog/archives/scala-types-of-a-higher-kind)
[Kinds of types in Scala, part 2](https://kubuszok.com/2018/kinds-of-types-in-scala-part-2/)