# COLLECTIONS - PART 1 (COLLECTION TRAITS)

[scala official documentation](https://docs.scala-lang.org/overviews/collections-2.13/overview.html)

## collection hierarchy
![Collections Hierarchy](https://docs.scala-lang.org/resources/images/tour/collections-diagram-213.svg)

 * Seq (an ordered sequence of elements)
 * Set (a set of un-ordered elements)
 * Map (a map of key-value pairs)

## Iterable / Traversable (deprecated in scala 2.13)
List is the default implementation returned by apply
```scala mdoc 
//val traversable = Traversable(1, 2, 3, 4, 5) 
val iterable = Iterable(1,2,3)
```

### important methods in traversables

#### head / tail
```scala mdoc
iterable.head 
iterable.tail 
```
#### map / flatMap
```scala mdoc
iterable.map{ nbr => nbr * 2}
iterable.flatMap (nbr => Iterable(nbr, nbr+1, nbr+2))
```

#### partition / span
Partition will put all "true" elements in one list, and the others in the second list.
Span will put all elements in one list until an element is "false" (in terms of the predicate). 
From that point forward, it will put the elements in the second list.

```scala mdoc
//splits a iterable into two halves ( a tuple )
iterable.partition(nbr => nbr % 2 == 0)
iterable.span(nbr => nbr % 2 == 0) 
```

#### find
```scala mdoc
//finds first
iterable.find(nbr => nbr % 2 == 0)
```

#### group by / grouped
```scala mdoc
//creates a map with keys (0,1,2) 
iterable.groupBy(nbr => nbr % 3)

// grouped 
iterable.grouped(3).mkString("[", ",", "]")
```
#### ++ (aka concat / append )
```scala mdoc
iterable.++(Iterable(6, 7, 8, 9)) 

//same as above but using infix notation
iterable ++ Iterable(6, 7, 8, 9)  
```

#### fold/reduce/aggregate/scan
```scala mdoc
iterable.reduceLeft((acc, elem) => acc + elem)   

//the same as reduce but starting with 10
iterable.foldLeft(10)((acc, elem) => acc + elem) 

//create a list of intermediary results (steps)
iterable.scanLeft(10)((acc, elem) => acc + elem)  
```

#### flatten
```scala mdoc
Iterable(Iterable(1,2), Iterable(3, 4, 5)).flatten
```

#### slice
```scala mdoc
iterable.slice(from=2, until=4)
```

### important sub-traits 


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

sequence concat Seq(2, 4, 6, 8)  // union in scala 2.12 (deprecated now) 
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
