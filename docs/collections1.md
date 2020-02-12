# COLLECTIONS - PART 1 (TRAITS)

## collection hierarchy
http://docs.scala-lang.org/resources/images/collections.png

     Traversable ( foreach, head, tail, map, groupBy, partition, find )
           ^
           |
         Iterable    ( iterator() over the elements, hasNext, next )
     |      |      |
    Seq    Set     Map

 * Seq ( an ordered sequence of elements )
 * Set ( a set of un-ordered elements )
 * Map ( a map of key-value pairs)

## Traversable (deprecated in scala 2.13)
List is the default implementation returned by apply
```scala mdoc 
val traversable = Traversable(1, 2, 3, 4, 5) 
```

### important methods in traversables
```scala mdoc
traversable.head 
traversable.tail 

traversable.map{ nbr => nbr * 2}

//splits a traversable into two halves ( a tuple )
traversable.partition(nbr => nbr % 2 == 0) 

//finds first
traversable.find(nbr => nbr % 2 == 0)

//creates a map with keys (0,1,2) 
traversable.groupBy(nbr => nbr % 3) 

traversable.++(Traversable(6, 7, 8, 9)) 
traversable ++ Traversable(6, 7, 8, 9)
traversable.++:(Traversable(6, 7, 8, 9))  

traversable.reduceLeft((acc, elem) => acc + elem)   
traversable.foldLeft(10)((acc, elem) => acc + elem) //the same as reduce but starting with 10
traversable./:(10)((acc, elem) => acc + elem)       //shortcut for fold left

// create a list of intermediary results (steps)
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
val iterableSample = Iterable(1, 2, 3, 4, 5, 6, 7)         
```

the most important method in the iterable trait
```scala mdoc
val iterator = iterableSample.iterator

//which allows you        
iterator.hasNext
iterator.next()
```

Grouping
```scala mdoc
val groupsOfThree = iterableSample.grouped(3)   //returns an iterator
groupsOfThree.next() 
groupsOfThree.next() 
groupsOfThree.next() 
groupsOfThree.hasNext 
```

Sliding windows
```scala mdoc
val slidingWindowOfThree = iterableSample.sliding(3) 
slidingWindowOfThree.next() 
slidingWindowOfThree.next() 
```

Zipping 
zipping is the equivalent of joining two sets by their element's index
```scala mdoc
val anotherIterable = Iterable('a', 'b', 'c', 'd')
anotherIterable.zip(iterableSample) 
anotherIterable.zipWithIndex  
```

### important sub-traits
TODO

### important concrete implementations
TODO

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
```scala mdoc
sequence(3) 
//sequence(100) //IndexOutOfBounds
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
HashSet, BitSet, TreeSet


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

//get returns an Option 
sampleMap.get(1) 
sampleMap.get(1).get
sampleMap.get(5)    //returns None
sampleMap.get(3) match {
  case Some(n) => println(s"consuming $n")
  case None => println(" the number does not exist in the map")
}

//apply does not return an option but the exact element or NoSuchElementException
sampleMap(1)
//sampleMap(5)//throws NoSuchElementException

//iterating over keys and values
for ((key, value) <- sampleMap) println(s"key=$key, value=$value")
```

### important sub-traits
todo

### important concrete implementations: 
HashMap, IntMap
