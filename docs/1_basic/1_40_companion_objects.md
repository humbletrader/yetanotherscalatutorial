# Singleton objects and Companion objects

## Objects / Singletons
Objects are used to hold single instances of a class
we can use them like utility classes in java

```scala mdoc
import java.text.SimpleDateFormat
import java.util.Calendar

object DateUtils {
    def getCurrentDate: String = {
        val dateFormat = new SimpleDateFormat("dd MMM yyyy")
        dateFormat.format(Calendar.getInstance().getTime())
    }
}

DateUtils.getCurrentDate
```

## companion objects

```scala mdoc
object Pizza{
    val CRUST_TYPE_THIN = "thin"
    val CRUST_TYPE_THICK = "thick"
}

class Pizza (val crustType: String){
    override def toString(): String = {
        s"This pizza has ${crustType}"
    }
}

val pizza = new Pizza(Pizza.CRUST_TYPE_THIN)
```

Note: a class and its companion object can access each other’s private members


## Companion objects as factories

```scala mdoc 
object Person{
    def apply(name: String): Person = {
        new Person(name)
    }
}

class Person (val name: String){

}

val person = Person("John") //please note the missing new operator
```