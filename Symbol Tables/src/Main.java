// * ---SYMBOL TABLES---
// Symbol table (ST): A data structure for key-value pairs (ie pair: Ryan-22630945)
// Given a key, search for the corresponding value

// Applications:
// - Dictionary
// - Financial account
// - Compiler
// - Filing system
// - Genomics

// * ---OPERATIONS---
// 1. put(key, value)   - inserts a new key-value pair into the table
// 2. get(key)          - returns the value associated with a given key
// 3. delete(key)       - removes a new pair into the table
// 4. contains(key)     - returns true if there is a value associated withe key
// 5. size()            - returns the no. of keys in the ST (an int)
// 6. isEmpty()         - returns true if the table is empty

// 7. keys() method:
// - Returns Iterable<Keys> (All the keys in the symbol table)
// - Thus, whatever you use in place of <Key> must be an iterable type.
// - Iterable is an interface
// - Remember that you cannot create an instance of an interface using "new"

// (3.) There are 2 types of deletion:
// - Lazy deletion: Associate the Key with null (Keys may then be removed later)
// - Eager deletion: Key is immediately removed from the table

// * ---PROPERTIES---
// 1. Both Key and Value are generic, allowing us to use any objects in their place
//    - If <Key extends ***> then Key must be of type ***
// 2. There CANNOT be duplicate keys (A key cannot be associated with more than one value)
//    - ie if there is a key-value pair Ryan-22; and Ryan-23 is inserted, Ryan-23 will REPLACE Ryan-22
// 3. A Key cannot be null
// 4. A Key's associated Value cannot be null
//    - Because get() should return null for keys not in the table

// * ---SIDE NOTE: EQUALITY---
// - If keys are primitive: use "==" to determine equality
// - If keys are non-primitive: Define an equals() method in object class (Pre-defined in String class)

// * ---ORDERED SYMBOL TABLES---
// ST<Key extends Comparable<Key>, Value> implements Iterable<Key>
// - Key must be a type that implements the Comparable interface
// - ST implements the Iterable interface (ie must implement the iterator() method)

// Ordered symbol table operations:
// - Like an unordered ST, implements put(), get(), delete(), contains(), isEmpty(), size() and keys()
//
// New operations:
// 1. min()                 - returns the smallest Key
// 2. max()                 - returns the largest Key
// 3. floor(Key)            - returns the largest Key less than or equal to (Key)
// 4. ceiling(Key)          - returns smallest Key greater than or equal to (Key)
// 5. rank(Key)             - returns the number of keys less than (Key)  ; 0 <= rank <= n-1
// 6. select(int)           - returns the Key of rank int
// 7. deleteMin()           - deletes the smallest key
// 8. deleteMax()           - deletes the largest key
// 9. size(Key lo, Key hi)  - returns the no. of keys in [lo..hi]
// X. keys(Key lo, Key hi)  - returns the Keys in [lo..hi], in sorted order

// * ---SEQUENTIAL SEARCH---
// Search for a target element by checking each element, one by one, until a search-hit or all elements have been checked

// Sequential search time-complexity
// - Average case: O(n/2)
// - Worst case: O(n)

// * ---BINARY SEARCH ST--- 
// - The key difference between BinarySearchST and ST is the way in which values are searched for (using get())
// - BinarySearchST uses the binary search algorithm in its rank() method
// - The rank() method is then called inside the get() method

// * ---BINARY SEARCH---
// Binary search algorithm:
// 1. Begin with a sorted list of elements.
// 2. Compare the target element with the middle element of the range
// 3. If greater, recursively apply this method to right half of list
// 4. If smaller, recursively apply this method to left half of list
// 5. Continue until element is found or all have been checked

// * ---BINARY SEARCH ANALYSIS---
// - Average case: O(log n)
// - Worst case: O(log n)

// * ---SIDE NOTES---
// - Best case time-complexity for search algorithms is ALMOST ALWAYS O(1) since first-time hit is possible
// - Cost model looks at compares as well as (sometimes) array accesses

public class Main {

    public static void main(String[] args) {
        
        ST<Integer,Character> table = new ST<Integer,Character>();

        table.put(000001, 'R');
        table.put(000002, 'S');
        table.put(000003, 'T');

        Iterable<Integer> it = table.keys();

        for (int i : it) {
            System.out.println(table.get(i));
        }

    }
    
}