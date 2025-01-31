# Views for collections
There is a systematic way to turn every collection into a lazy one and vice versa, which is based on collection views.
 
A view is a special kind of collection that represents some base collection, but implements all transformers lazily.

```scala mdoc 
val viewOfAList = List(0,1,2,3,4,5).view //SeqView

val viewWithTransformations = viewOfAList.map(_*2).map(_+1) //still a SeqView

val backToList = viewWithTransformations.force //Seq
```


## Use cases for views

### use case 1 (lazy evaluation)

```scala mdoc
(0 to 9999).view.find(_.toHexString.startsWith("a")) //this is much faster than
(0 to 9999).find(_.toHexString.startsWith("a"))
```

### use case 2 - providing views into arrays (reference objects)

```scala mdoc:fail
val originalArray = (0 to 9).toArray
val partialViewIntoArray = originalArray.view.slice(2,5)

def add100(xs: collection.mutable.Seq[Int]): Unit =
for(i <- xs.indices) xs(i) = xs(i) + 100

add100(partialViewIntoArray)

println(originalArray.mkString(",")) //0,1,102,103,104,5,6,7,8,9
```
