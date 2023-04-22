
// * ---BINARY SEARCH TREES (BST)---
// - A binary search tree (BST) is a recursive data structure where each node can have 2 children at most.

// Properties:
// 1. Each node has a Comparable key, an associated value, a left node and a right node
// 2. Each node's key is larger than all the node's keys in the left subtree 
// 3. Each node's key is larger than all the node's keys in the left subtree 
// 4. If a node does not have a left/right child, their associated left/right node must be null
// 5. Duplicate nodes are NOT ALLOWED

// ---SIDE NOTE---
// - Technically all a BST contains is a root node, that root node has children nodes, which have children, etc
// - This is why a BST is a RECURSIVE data structure

// * ---BST METHODS---
// 1.  isEmpty()
// 2.  size()
// 3.  contains(Key key)
// 4.  get(Key key)                 *
// 5.  put(Key key, Value val)      *
// 6.  deleteMin()
// 7.  deleteMax()
// 8.  delete(Key key)
// 9.  min()
// 10. max()
// 11. floor(Key key)               *
// 12. ceiling(Key key)             *

// * private Value get(Node x, Key key)
// - Uses BINARY SEARCH algorithm to find a Key's value
// 
// Algorithm:
// 1. Check if the current node is null. If it is, return null.
// 2. Compare the key to the current node's key using the compareTo method.
// 3. If the key is less than the current node's key, recursively search the left subtree by calling get(x.left, key).
// 4. If the key is greater than the current node's key, recursively search the right subtree by calling get(x.right, key).
// 5. If the key is equal to the current node's key, return the value associated with that key at the current node.

// * private Node put(Node x, Key key, Value val) 
// 1. If the current node is null create a new node with the given key and value, and return it.
// 2. Compare the key to the current node's key using the compareTo method.
// 3. If the key is less than the current node's key, recursively call the "put" operation on the left subtree by calling put(x.left, key, val).
// 4. If the key is greater than the current node's key, recursively call the "put" operation on the right subtree by calling put(x.right, key, val).
// 5. If the key is equal to the current node's key, update the value associated with that key at the current node to the given value.
// 6. Update the size of the current node by setting x.N to the sum of the sizes of the left and right subtrees plus 1.
// 7. Return the current node x.

// * floor(Key key)
// The floor of a given key is the largest key in the tree that is less than the given key


// * ceiling(Key key)
// The ceiling of a given key is the smallest key in the tree that is greater than the given key

// * Time-complexity of binary tree search
// - Average case: O(1.39 log n)
// - Worst case: O(n)

// * Inserting into a BST array
// - Average case: O(1.39 log n)
// - Worst case: O(n)

// * ---SIDE NOTE---
// In a BST, all operations take time proportional to the height of the tree, in the worst case.


public class Main {

    public static void main(String[] args) {
    
        BST<Integer, String> bst = new BST<Integer, String>();
        bst.put(5, "five");
        bst.put(2, "two");
        bst.put(8, "eight");
        bst.put(1, "one");
        bst.put(3, "three");
        bst.put(7, "seven");
        bst.put(9, "nine");
    
        System.out.println("Size of BST: " + bst.size()); // should print 7
        System.out.println("Value associated with key 3: " + bst.get(3)); // should print "three"
        System.out.println("Value associated with key 6: " + bst.get(6)); // should print null
        System.out.println("Max key: " + bst.max()); // should print 9
        System.out.println("Ceiling of key 6: " + bst.ceiling(6)); // should print 7
        System.out.println("Min key: " + bst.min()); // should print 1
        System.out.println("Floor of key 6: " + bst.floor(6)); // should print 5
        System.out.println("Key of rank 3: " + bst.select(3)); // should print 5
        System.out.println("Rank of key 3: " + bst.rank(3)); // should print 2
    
        bst.deleteMin();
        System.out.println("Size of BST after deleting min: " + bst.size()); // should print 6
        System.out.println("New min key: " + bst.min()); // should print 2
    
        bst.deleteMax();
        System.out.println("Size of BST after deleting max: " + bst.size()); // should print 5
        System.out.println("New max key: " + bst.max()); // should print 8
    
        bst.delete(5);
        System.out.println("Size of BST after deleting key 5: " + bst.size()); // should print 4
        System.out.println("Value associated with key 5: " + bst.get(5)); // should print null

    }

}
