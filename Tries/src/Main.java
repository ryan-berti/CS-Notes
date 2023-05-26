// * ---(R-WAY) TRIE DATA STRUCTURE: TrieST<Value>---
// - A trie is a type of tree data structure containing Nodes (technically only
// the root Node, which has links to other nodes)

// ? Definition:
// - A data structure built from the characters of the string keys that allows
// us to use the characters of the search key to guide the search.

// ? Side note - implicit storage:
//
// - Each node represents a character, and the path from the root node
// to any other node forms a string (THE KEY!). Each node does not store its
// corresponding string as an explicit piece of data; instead, you can obtain
// the string by traversing the trie.
//
// - ie Keys are not stored directly in Nodes

// ? Attributes:
// - private static int R = 256 ; (radix)
// - private Node root

// ? private static class Node:
//
// Attributes:
// - private Object val
// - private Node[] next = new Node[R]
//
// Node representation:
// - Every node has R links, one for each possible character.
// - Characters and keys are implicitly stored in the data structure.

// ? Methods:
// - StringST(): Creates a symbol table
//
// - void put(String key, Value val): Puts key-value pair into the table
// (removes key if value is null)
//
// - Value get(String key): Returns value paired with key (returns null if key
// is absent)
//
// - void delete(String key): Removes key (and its value)
// - boolean contains(String key): Checks if there is a value paired with key
// - boolean isEmpty(): Checks if the table is empty
//
// - String longestPrefixOf(String s): Returns the longest key that is a prefix
// of s
//
// - Iterable<String> keysWithPrefix(String s): Returns all the keys having s as
// a prefix
//
// - Iterable<String> keysThatMatch(String s): Returns all the keys that match s
// (where . matches any character)
//
// - int size(): Returns the number of key-value pairs
// - Iterable<String> keys(): Returns all the keys in the table

// ? Basic properties:
//
// 1. Nodes and Links:
// - Tries are made up of nodes, each node holds an array of links (of size R).
// - Links can either be null or references to other nodes.
// - Every node has precisely one parent node (root Node has none).
// - Each node possesses R links, where R is the size of the alphabet.
//
// 2. Root Node:
// - The root node does NOT have a parent node.
//
// - Unlike other nodes, it doesn't correspond to any character value since no
// link points to it.
//
// 3. Character value:
// - Links within a node correspond to character values.
// - Each link points to one distinct node.
//
// - Characters are not stored directly, the characters rather indexed in the
// links array using their ASCII value.
//
// - Nodes are "labelled" (but not directly stored) with character values that
// correspond to the links pointing to them.
//
// 4. Node Values:
// - Every node in a trie holds a value.
//
// - This value could be null or the value associated with a string key in the
// symbol table.
//
// - The value linked to each key is housed in the node corresponding to the
// last character of that key.
//
// 5. Null Nodes:
// - There exist nodes with null values within the trie.
// - These nodes aid in facilitating searches within the trie.
//
// - Such nodes don't correspond to keys and often represent the end of a string
// key in the trie.
// - ie leaf nodes
//
// 6. Null Links:
// - Tries frequently contain a large number of null links.
//
// - A null link represents a character that does not appear as a subsequent
// character at a specific position in any of the keys in the Trie
//
// - When visualizing a trie, these null links are typically omitted for the
// sake of simplicity.
//
// 7. Search Operation:
// - The search for a key in a trie commences from the root node.
// - The operation follows links that correspond to characters in the key.
//
// - If a link that corresponds to a character doesn't exist, the search
// concludes that the key isn't present in the trie.

// ? Searching - get():
// - Return the value of a given string Key using the RECURSIVE get() method
//
// Process:
//
// 1. Start at the root:
// - Every search in a trie begins at the root node. This node is the starting
// point for all keys stored in the trie.
//
// 2. Follow the links:
// - For each character in the key, find the corresponding link in the current
// node and follow it to the next node. For example, if you're looking for the
// key "sea", you'd first follow the link for 's', then the link for 'e', and
// finally the link for 'a'.
//
// 3. Check for search termination (after each "recursive iteration" of int d):
//
// 3a. Search hit:
// - If you've followed all the characters in your key and the node you've
// arrived at has a non-null value, the search is a hit. The value associated
// with the key is the value in the node corresponding to its last character.
//
// 3b. Search miss due to null value (leaf node):
// - If you've followed all the characters in your key and the node you've
// arrived at has a null value, the search is a miss. The key is not present in
// the trie.
//
// 3c. Search miss due to null link:
// - If while following the characters of your key, you encounter a null link
// (i.e., there is no link corresponding to the next character in your key), the
// search is a miss. The key is not present in the trie.

// ? Insertion - put():
// - Insert a string Key (and its associated value) using put() method
//
// Process:
//
// 1. Start with a search:
// - Similar to binary search trees, insertion in a trie begins with a search
// operation.
// - Using the characters of the key, navigate through the trie.
//
// 2. Navigate through the trie:
// - The search operation moves down the trie, following links corresponding to
// the characters in the key.
//
// - This process continues until reaching the last character of the key or a
// null link.
//
// 3. Check the current condition:
//
// 3a. Encountered a null link:
// - If a null link is encountered before reaching the last character of the
// key, it indicates there's no trie node corresponding to the last character in
// the key.
//
// - In this case, create nodes for each of the characters in the key not yet
// encountered.
//
// - The value in the last node created is set to the value to be associated
// with the key.
//
// 3b. Encountered the last character:
// - If the last character of the key is encountered before reaching a null
// link, the node's value corresponding to this character is set to the value
// associated with the key.
//
// - This value could be a null, following the associative array convention.
//
// 4. Examine or create nodes:
// - Regardless of the outcome, a node in the trie is examined or created for
// each character in the key.
//
// - This ensures that the entire key is represented in the trie following the
// insertion.

// ? Deletion - delete():
//
// Process:
//
// 1. Start at root:
//
// 2. Follow links:
// - Traverse nodes for each character in key.
//
// 3. Locate node to delete:
//
// 3a. Key found:
// - If node's value is non-null, set it to null.
//
// 3b. Key not found:
// - No action needed if a null link or node with null value is encountered.
//
// 4. Delete nodes if needed:
//
// 4a. Node has non-null children:
// - No further action needed.
//
// 4b. Node has null children:
// - Remove node and parent nodes recursively until a node with non-null links
// or root is encountered.

// ? Propositions:
//
// - There is a unique trie for any given set of keys (ie the order of
// insertion/deletion does NOT matter).
//
// - The number of array accesses when searching in a trie or inserting a key
// into a trie is at most 1 plus the length of the key.
//
// - The no. of links in a trie is between RN and RNw; w = avg key length.

// * ---TERNARY SEARCH TREE (TST) DATA STRUCTURE: TST<Value>---
//
// - A TST is a data structure containing nodes, each node in a TST contains a
// character, three pointers (left, middle, and right) representing subtrees,
// and a value.
//
// - On the other hand, in a TRIE data structure, each node contains 256 links -
// this is a huge waste of space if there are a large no. of null links.

// ? TST vs Trie:
//
// 1. Links:
// - Each node in a TST contains exactly 3 links
// - Each node in a Trie contains 256 links (for extended ASCII)
//
// 2. Nodes:
// - Nodes in a TST contain a character, a value and 3 Nodes (Each a subtree)
// - Nodes in a Trie contain a value and an array of Nodes (links)
//
// 3. Root node:
// - The root node in a TST contains a character (ie it is NOT "blank")
//
// - The root node of a Trie is "blank" (since the character of a node is
// represented by its incoming link; and the root node has none)

// ? Attributes:
// - private Node root

// ? private class Node:
// - char c
// - Node left, mid, right
// - Value val

// ? Searching:
// - Return the value of a given string Key using get() method
//
// Process:
//
// 1. Start at the root
//
// 2. For each character in the key, compare it with the character in the node:
//
// 2a. If the key character is less, follow the left link.
// 2b. If the key character is greater, follow the right link.
//
// 2c. If they are equal, follow the middle link and move to the next key
// character.
//
// 3. Check for search termination:
//
// 3a. Search hit:
// - If all the key's characters are followed and we arrive at a non-null value
// node, it's a hit. Node's value is the value associated with the key.
//
// 3b. Search miss due to null value:
// - If all the key's characters are followed and we arrive at a node with a
// null value, it's a miss. The key isn't present in the TST.
//
// 3c. Search miss due to null link:
// - If a null link is encountered while following the key's characters, it's a
// search miss.

// ? Insertion:
// - Insert a new key-value pair using the put() method
//
// Process:
//
// 1. Start at the root
//
// 2. For each character in the key, compare it with the character in the node:
//
// 2a. If the key character is less, follow the left link. If the left link is
// null, create a new node.
//
// 2b. If the key character is greater, follow the right link. If the right link
// is null, create a new node.
//
// 2c. If they are equal, follow the middle link and move to the next key
// character. If the middle link is null, create a new node.
//
// 3. Set the value:
// - Once all the key's characters have been processed, set the value in the
// final node to the value associated with the key.
