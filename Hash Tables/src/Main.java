// * ---FORE THOUGHT: GREED---
// - What if we want a better search-time complexity than log n?
// - Is there a data structure that can achieve this...

// * ---HASH TABLES---
// - Interpret a key as an array index
// - We can store the value associated with Key i in a[i]
// - aka 'Hash Map'

// * ---HASH FUNCTIONS---
// - The hash function is responsible for mapping keys to their corresponding indices in the array
// - ie if we have 10 key-value pairs, we need a hash function that maps any key to a value in [0,n-1]
// - We seek a hash function that is easy to compute and uniformly distributes the keys
//
// Important properties:
// 1. Consistency
// 2. Efficiency
// 3. Uniform distribution of keys
//
// Umiform hashing ussumption:
// - The hash function UNIFORMLY and INDEPENDENTLY distributes keys among the integer values [0...M-1].


//? Positive integers
// - Use "modular hashing":
//      1. Choose array size M (must be a prime number)
//      2. Choose hash function: Value = Key % M

//? Strings
// Horner’s method:
// 
// int hash = 0; 
// for (int i = 0; i < s.length(); i++) {
//     hash = (R * hash + s.charAt(i)) % M;
// }
//
// (essentially turns string into huge int and uses *modular hashing*)

//? Floating-point numbers
// 1. Multiply multiply key (floating point) with M and convert to an integer
// Value will be an int between [0...n-1]


//? hashCode() method:
// - Every data type inherits a method called hashCode() that returns a 32-bit integer
// - hashCode() must be implemented in a way that is consistent with equals():
//      1. If two objects are equal according to the equals() method, then their hash codes must be the same.
//      2. If two objects have different hash codes, then they are not equal according to the equals() method.
//      3. Thus, to use a user-defined type in a hash table, implement CUSTOM implementations of equals() and hashCode()  to ensure they are consistent.

//? Modifying hashCode():
// - We want an array index NOT a 32-bit integer, use:
// - Use: private int hash(Key x) { return (x.hashCode() & 0x7fffffff) % M; } 

//? Software caching:
// - Computing the hash code for each key can be COSTLY
// - Thus, it can be beneficial to we maintain an *instance variable: hash, in the key type that contains the value of hashCode()
//      - This is known as "cache the hash"


// * ---COLLISIONS---
// - A collision occurs when two or more keys are mapped to the same index in the hash table
// - This can happen due to the finite size of the hash table or due to the nature of the hash function.
//
// Solutions:
// 1. Hashing with seperate chaining
// 2. Hashing with linear probing
// 3. Array resizing


// * ---HASHING WITH SEPERATE CHAINING---
// - Each element of the hash table is a linked list of key-value pairs
// - When two keys have the SAME HASH value, they are stored in the SAME linked list

//? Process:
// 1. Initialize an array (called st) of linked-list symbol tables of a given size M.
// 2. For each key, compute its hash value and find the corresponding linked list in the array.
// 3. If the linked list is empty, insert the key-value pair at the head of the list.
// 4. If the linked list is not empty, search the list for the key:
//      - If the key is found, update its value. 
///     - Otherwise, insert the key-value pair at the head of the list.

//? Operations:
// - Resize the hash table:
//      1. Create a new hash table with new size
//      2. Re-hash all of the keys.
//
// - Delete a key-value pair


//? Search miss/insert cost:
// - In a separate-chaining hash table with M lists and N keys, the no. of compares for search miss and insert is ~N/M. 


// * ---HASHING WITH LINEAR PROBING---
// - Alternatively store N key-value pairs in a hash table of size M > N, using empty entries in the table resolve collisions.
// - Known as an "open adressing" hashing method

//? Characterized by identifying three possible outcomes:
// - Key EQUAL to search key: search hit
// - EMPTY position (null key at indexed position): search miss
// - Key not equal to search key: try next entry

//? Process:
// 1. Initialize a LinearProbingHashST object with a specified or default capacity = 16.
// 2. Compute the hash code for the key using the hash function.
// 3. Determine the index using the hash code (use linear probing to find next free entry if necessary)
// 4. Store the key-value pair at the calculated index.
// 5. (Re-size when appropriate)

//? Operations:
// - Search (ie get()): 
//      1. Compute the hash code for the key
//      2. Find the index where the key-value pair should be located
//      3. Probe linearly until the key is found or an empty spot is encountered
//
// - Delete key-value pair:
//      1. Find pair using above search
//      2. Remove pair from hash table
//      3. Re-hash all keys in the same cluster
// 
// - Upsizing:
// If the number of key-value pairs is >= half the size of the table:
//      1. Create a new table with 2x capacity
//      2. Re-hash all the key-value pairs.
//
// - Downsizing:
// If the number of key-value pairs is <= one-eigth the size of the table:
//      1. Create a new table with 0.5x capacity
//      2. Re-hash all the key-value pairs.

// * ---SPACE USAGE---
// - Separate chaining: ~ 48 N + 64 M 
// - linear probing: between ~32 N and ~128 N
// - BSTs: ~56N

public class Main {

    public static void main(String[] args) {
    
    }
}