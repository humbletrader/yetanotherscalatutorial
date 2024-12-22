# Type classes in scala

    Type class is a class (group) of types, which satisfy some contract defined in a trait with. Aditionally such functionality (trait and implementation) can be added without any changes to the original code.

## Defining type classes


### Fist let's define some domain objects

```scala mdoc
//domain classes
case class Person(firstName: String, lastName: String)

//i'm pretty lazy so i'll use String instead of Json
type JSON = String
```

## Define the type class

```scala mdoc
//the one and only type class
trait JsonWriter[A]{
  def writeAsJson(obj: A) : JSON
}

//type class instances
object Instances{
  implicit val personJsonWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def writeAsJson(obj: Person): JSON = s"JSON[${obj.firstName}]"
  }
}
```

## Consuming type classes

### Option 1:  Interface object

```scala mdoc
object Json{
  def toJson[A](value: A)(implicit writer: JsonWriter[A]) : JSON =
    writer.writeAsJson(value)

  def fancierToJson[A:JsonWriter](value : A) : JSON =
    implicitly[JsonWriter[A]].writeAsJson(value)
}

import Instances._

Json.toJson(Person("Jon", "Doe"))
```

### Option 2: Interface Syntax (extension methods / type enrichment / pimping )

```scala mdoc
object JsonSyntax {
  implicit class JsonWriterOps[A: JsonWriter](value: A) {
    def toJson: JSON = implicitly[JsonWriter[A]].writeAsJson(value)
  }
}

import Instances._
import JsonSyntax._

Person("Jane", "Doe").toJson

```

## Combining type classes for an even better experience

### Adding more instances 

```scala mdoc
//type class instances
object EvenMoreInstances{
  implicit val personJsonWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def writeAsJson(obj: Person): JSON = s"JSON[${obj.firstName}]"
  }
  
  implicit def listJsonWriter[T: JsonWriter] : JsonWriter[List[T]] = new JsonWriter[List[T]] {
    override def writeAsJson(src: List[T]) : JSON = {
      val jsonWriterForInternalValue = implicitly[JsonWriter[T]]
      s"JSList[${src.map(v => jsonWriterForInternalValue.writeAsJson(v))}]"
    }
  }
}

```

### Combining more instances with the help of scala compiler

```scala mdoc 
import EvenMoreInstances.personJsonWriter
import EvenMoreInstances.listJsonWriter
import JsonSyntax._

val listAsJson = List(Person("john", "doe"), Person("Jane", "Doe")).toJson 
println(listAsJson)

```



