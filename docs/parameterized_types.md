# Parameterized and Abstract types 

## Parameterized types
Are the equivalent of Java Generics only that it's specified as
```scala mdoc
val lst: List[String] = "a"::"b"::"c"::Nil
```

## Abstract types
```scala mdoc
abstract class BulkReader{
    type In
    val source : In
    def read: String 
}

class StringBulkReader(val source: String) extends BulkReader {
    type In = String
    def read: String = source
}

println(new StringBulkReader("Hello Scala!").read)
```
