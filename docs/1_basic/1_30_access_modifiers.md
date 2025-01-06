# Access Modifiers

//inspired from https://jesperdj.com/2016/01/08/scala-access-modifiers-and-qualifiers-in-detail/

## default access modifiers - the equivalent of java public 


```scala mdoc
class Test {
  def foo(): Unit = println("called foo ") //this has public access
}

class AccessModifiersTest extends App {

  val test = new Test
  test.foo() // access to public method

}
```

## package level access modifiers

```scala
package parent {

class InsideParent{
  private def fullPrivate() : Unit = println("accessing full private method of a class defined in foo package")

  private[parent] def parentPrivate() : Unit = println("accessing private[foo] method of a class defined in foo package")

  protected def fullProtected() : Unit = println("access full protected method defined in package foo")

  protected[parent] def parentProtected() : Unit = ???
}

  package child { //inside parent package

    class InsideChild{
      private def fullPrivateMethod() : Unit = println("accessing a full private methods of a class defined in foo.bar package")
      private[child] def childPrivate() = println("private only inside bar package")
      private[parent] def privateUpToParentPackage = println("private up to bar package")

      protected def fullProtected : Unit = ???
      protected[child] def childProtected : Unit = ???
    }

    class TesterClass{

      val insideChildInstance = new InsideChild()
      //insideChildInstance.fullPrivateMethod() // compilation error
      insideChildInstance.childPrivate() //we can access same-package private methods
      insideChildInstance.privateUpToParentPackage

      val insideParentInstance = new InsideParent()
      //insideParentInstance.fullPrivate() // compilation error
      insideParentInstance.parentPrivate() // foo is the parent of bar (where this class resides) so we can access private methods in foo classes
      insideParentInstance.parentProtected() // the same as private
      //insideParentInstance.fullProtected() //compilation error
    }

  }//end child package
} //end parent package


package blah {

  import parent.InsideParent

  class TryingToAccessOtherPackage{
    val definedInsideOtherPackage = new InsideParent()
    definedInsideOtherPackage.toString //access only public methods
    //definedInsideOtherPackage.parentPrivate() //compilation error
    //definedInsideOtherPackage.parentProtected() //compilation error
  }
}
```


# Qualified access to ‘this’

```scala mdoc:fail
class Counter{
  private var total : Int = 0
  private[this] var inaccessibleOutsideThisInstance : Int = 0

  def add(i: Int) : Unit = {
    total += 1 //access to private member
    inaccessibleOutsideThisInstance += 1 // //access to private[this] member
  }

  def copyFrom(other: Counter) : Counter = {
    this.total = other.total //no problem accessing the total in the other instance
    this.inaccessibleOutsideThisInstance = other.inaccessibleOutsideThisInstance //compilation error
  }
}
```



