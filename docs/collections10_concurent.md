# Concurrent collections in scala

## concurrent.TrieMap
A Scala TrieMap is a trie-based concurrent scalable map implementation. Unlike normal trie maps, a Scala TrieMap has an efficient, non-blocking, O(1) time snapshot operation (and a slightly optimized readOnlySnapshot) operation.
TrieMaps are Maps using trie data structure which are essentially shallow trees. 
For example, If you have a 32 bit hash you break it up into sections for example 4 times 8 and at every level of the tree you branch to up to 256 sub trees. Obviously this gives O(1) performance due to the fixe size of hash(assuming few collisions).

## comparison with java.util.concurrent.ConcurrentHashMap
 * the java ConcurrentHashMap has better performance than TrieMap 
 *  but the advantage is that it provides consistent iterators, something that concurrent data structures typically do not have