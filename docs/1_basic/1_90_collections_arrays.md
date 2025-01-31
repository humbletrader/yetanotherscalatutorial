# Arrays

Arrays preserve order, can contain duplicates, and are mutable.

```scala mdoc
val arr = Array(1,2,3,2,3,1)
println(arr(4)) 

//mutability
arr(4) = 10
println(arr(4))

//multi dimensional arrays
val matrix = Array.ofDim[Int](2,2)
matrix(0)(0) = 0
matrix(1)(0) = 1
matrix(0)(1) = 2
matrix(1)(1) = 3
```
