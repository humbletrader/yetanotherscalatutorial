# Linearization

Linearization is an algorithm used by scala to solve the multiple inheritance issue. 
As opposed to java where a compile time error is thrown, in scala the linearization ensures ons single
line of inheritance from any object to the higher ones in the hierarchy

### Typical inheritance
```scala mdoc 
class Parent{
    println("parent contructor")
}

class Child extends Parent{
    println("child constructor")
}

val child = new Child
```

### Mixins 
```scala mdoc
trait First{
    println("constructor first")
}

trait Second{
    println("constructor second")
}

val mixin = new First with Second
```

## Solving the overriding of methods (and The diamond problem): 
```scala mdoc
trait Animal {
  def legCount() : Int
}

trait TwoLegged extends Animal {
  override def legCount(): Int = 2
}

trait FourLegged extends Animal {
  override def legCount(): Int = 4
}

val test = new TwoLegged with FourLegged
test.legCount()

val test2 = new FourLegged with TwoLegged
test2.legCount()
```

## How does linearization work ? 
* we start from right to left ( i.e. from the end)
* Lin (test) = Lin(FourLegged) > Lin(TwoLegged)
* Lin (FourLegged) = FourLegged > Animal > AnyRef > Any
* Lin (TwoLegged) = TwoLegged > Animal > AnyRef > Any
* FourLegged > Animal > AnyRef > Any > TwoLegged > Animal > AnyRef > Any
* remove duplicates (keep only the rightmost values)
* FourLegged > TwoLegged > Animal > AnyRef > Any
* so the FourLegged is the first to call when solving the legCount
* and the order of constructor is the other way around : Animal then TwoLegged then FourLegged


A more complex example
```scala mdoc
class C1{
    print("c1 ") 
    def identification: String = "c1 "
}

trait T1 extends C1 {
    print("t1 ") 
    override def identification : String = "t1 " + super.identification
}

trait T2 extends C1 {
    print("t2 ")     
    override def identification : String = "t2 " + super.identification
}

class C2 extends T1 with T2{
    println("c2 ")
    override def identification : String = "c2 " + super.identification
} 

val c2 = new C2
c2.identification   
```
So the linearization is : 
1. Lin(c2) = Lin(C2)
 = C2 > Lin(T2) > Lin(T1)
 = C2 > T2 > C1 > AnyRef > Any > T1 > C1 > AnyRef > Any
 = C2 > T2 > T1 > C1 > AnyRef > Any
So c2.identification = c2 t2 t1 c1 
