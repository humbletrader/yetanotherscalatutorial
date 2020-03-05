# COLLECTIONS - PART 1 (TRAITS)

## collection hierarchy
![Collections Hierarchy](http://docs.scala-lang.org/resources/images/collections.png)

 * Seq (an ordered sequence of elements)
 * Set (a set of un-ordered elements)
 * Map (a map of key-value pairs)

## Traversable (deprecated in scala 2.13)
List is the default implementation returned by apply
```scala mdoc 
val traversable = Traversable(1, 2, 3, 4, 5) 
```

### important methods in traversables

#### head / tail
```scala mdoc
traversable.head 
traversable.tail 
```
#### map / flatMap
```scala mdoc
traversable.map{ nbr => nbr * 2}
traversable.flatMap (nbr => Traversable(nbr, nbr+1, nbr+2))
```

#### partition
```scala mdoc
//splits a traversable into two halves ( a tuple )
traversable.partition(nbr => nbr % 2 == 0) 
```

#### find
```scala mdoc
//finds first
traversable.find(nbr => nbr % 2 == 0)
```

#### group by
```scala mdoc
//creates a map with keys (0,1,2) 
traversable.groupBy(nbr => nbr % 3) 
```
#### ++ (aka concat / append )
```scala mdoc
traversable.++(Traversable(6, 7, 8, 9)) 
traversable ++ Traversable(6, 7, 8, 9)  
```

#### ++: ( prepend)
Please note the right associative operation ++:
```scala mdoc 
traversable.++:(Traversable(6, 7, 8, 9))
```
which can also be written in a more readable manner
```scala mdoc
Traversable(6,7,8,9) ++: traversable
```

#### fold/reduce/scan
```scala mdoc
traversable.reduceLeft((acc, elem) => acc + elem)   

//the same as reduce but starting with 10
traversable.foldLeft(10)((acc, elem) => acc + elem) 

//shortcut for fold left
traversable./:(10)((acc, elem) => acc + elem)       

//create a list of intermediary results (steps)
traversable.scanLeft(10)((acc, elem) => acc + elem)  

Traversable(Traversable(1,2), Traversable(3, 4, 5)).flatten
```

### important sub-traits 
 Iterable

### important concrete implementations 
 none

## Iterable - Iterator
Iterable is a factory of Iterators

List is the default implementation returned by Iterable.apply
```scala mdoc 
val iterableTest = Iterable(1, 2, 3, 4, 5, 6, 7)         
```

the most important method in the iterable trait
```scala mdoc
val iterator = iterableTest.iterator
```

Grouping
```scala mdoc
val groupsOfThree = iterableTest.grouped(3)   //returns an iterator
groupsOfThree.next() 
groupsOfThree.next() 
groupsOfThree.next() 
groupsOfThree.hasNext 
```

Sliding windows
```scala mdoc
val slidingWindowOfThree = iterableTest.sliding(3) 
slidingWindowOfThree.next() 
slidingWindowOfThree.next() 
```

Zipping 
zipping is the equivalent of joining two sets by their element's index
```scala mdoc
val anotherIterable = Iterable('a', 'b', 'c', 'd')
anotherIterable.zip(iterableTest) 
anotherIterable.zipWithIndex  
```

### important sub-traits
Seq, Set, Map

### important concrete implementations
see below

## Sequences
A Sequence is an iterable with positions for each element and fixed length

Vector as default implementation returned by apply
```scala mdoc 
val sequence = Seq(1, 3, 5, 7)
```

### important methods
```scala mdoc
sequence(1) 
sequence.indexOf(3) 
sequence.length 
sequence.:+(9)
//same as above 
sequence :+ 9 

9 +: sequence 
// same as above
sequence.+:(9) 

sequence union Seq(2, 4, 6, 8)
sequence intersect Seq(1, 2, 3, 4, 5)
sequence.sorted
sequence.reverse
```

### notable sub-traits : IndexedSeq and LinearSeq
LinearSeq : optimized for head and tail operations
IndexedSeq: optimized for apply, length, update

### important concrete implementations:
List, Stream : implementations for LinearSeq
Array, ArrayBuffer, Vector: implementation for IndexedSeq

Seq implements PartialFunction[Int, A] so you can easily write
```scala mdoc:crash
sequence(3) 
sequence(100)
```

## Sets
sets are iterables that contain no duplicates and do not preserve order

Default implementation returned by apply
```scala mdoc 
val myPets = Set("cat", "dog", "mouse")
```

### important methods
```scala mdoc
myPets.contains("cat") 
myPets("cat") //returns true ( because Set extends Function1 A => Boolean)
myPets + "bear"
myPets ++ (Set("bear", "deer", "monkey"))
myPets + ("bear", "deer") //adding the elements of a tuple
```

### important sub-traits: 
SortedSet

### important implementations: 
HashSet, BitSet, TreeSet (via SortedSet)


## Maps
```scala mdoc
val sampleMap = Map(1 -> "one", 2 -> "two", 3 -> "three")
//same as above
val sampleMap2 = Map( (1, "one"), (2, "two"), (3, "three")) 
```


### important methods:
```scala mdoc
//ugly way of filtering/mapping a map
sampleMap.filter( pair => pair._1 < 3) 

//nicer way of doing the same thing
sampleMap.filter({case (int, str) => int < 3})

//even more simple without the parenthesis 
sampleMap.filter {case (int, str) => int < 3}
```

Map.get returns an Option
```scala mdoc 
sampleMap.get(1) 
sampleMap.get(1).get
sampleMap.get(5)    //returns None
sampleMap.get(3) match {
  case Some(n) => println(s"consuming $n")
  case None => println(" the number does not exist in the map")
}
```

apply does not return an option but the exact element or NoSuchElementException
```scala mdoc:crash
sampleMap(1)
sampleMap(5)
```

iterating over keys and values
```scala mdoc
for ((key, value) <- sampleMap) println(s"key=$key, value=$value")
```

### important sub-traits
SortedMap

### important concrete implementations: 
HashMap, IntMap, TreeMap (via SortedMap)
