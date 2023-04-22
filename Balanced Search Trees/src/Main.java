
// * ---FORE NOTE---
// - BSTs are problematic when the tree looks like a linked-list (becomes linear rather than logarithmic)
// - But it is costly to balance a BST :(
// - Thus, there is a need for trees that balance themselves during insertion...

// * ---2 3 SEARCH TREES---
// - A 2-3 is similar to a binary tree, but has a few crucial differences
// The main difference being that 2-3 trees can contain nodes with 2 keys and 3 children

// Properties:
// - A 2-3 search tree is a tree that contains either:
//      1. Empty (null link)
//      2. A 2-node, with one key and two children
//      3. A 3-node, with two keys and Three children
// - All children of a 2-3 search tree node must be 2-3 trees themselves
// - All null links are the same distance from the root node (Perfectly balanced)
// - Values in the left sub-tree of a 2-node are smaller than the left key, values to the right are larger
// - The middle-child of a 3-node is greater than the left key and smaller than the right key

// * ---3 NODE VISUALIZATION---
//               [x, y]
//            /    |    \
//        [  a ] [  b ] [ c  ]
//    
//      (a) < x
//  x > (b) < y
//  x > (c)

// * ---SEARCHING IN A 2-3 TREE---
// - Searching in a 2-3 tree is essentially the same as a binary search
// - EXCEPT FOR 3-NODES:
//      1. If the target key is smaller than the node's left key, recursively search the left sub-tree
//      2. If the target key is larger than the node's right key, recursively search the right sub-tree
//      3. If the target key is larger than the node's left key and smaller than the nodes right key, recursively search the middle sub-tree
// (Basically there is just one more comparison in the search method)

// * ---INSERTING INTO A 2-3 TREE---
// - Similar to BST insertion but nodes must be MANIPULATED to maintain perfect balance
//
// - Inserting <Key> into a 2-node:
//      1. Find last node that would've been checked when searching for <Key>
//      2. Turn that 2-node into a 3-node (merging)
//
// - Inserting <Key> into a 3-node:
//      1. Find last node that would've been checked when searching for <Key>
//      2. Turn that 3-node into a temporary 4-node (3 Keys)
//      3. Move middle key to PARENT node
//      4. Split remaining 3-node into TWO separate 2-nodes

// * ---2 3 TREE PERFORMANCE---
// - Search and insert operations in a 2-3 tree are guaranteed to visit at most lg n nodes.


// * ---RED BLACK SEARCH TREES---+
// - A red-black tree is a binary implementation of a 2-3 tree using "red" and "black" links
// - Using a red-black tree allows us to use our get() code for standard BST search without modification

// Properties:
// 1. Red links lean left
// 2. No node has two red links connected to it
// 3. Perfect black-balance: every path from the root to a null link has the same number of black links.

// * ---VISUALIZATION OF RED LINK---
// Transforming 3-nodes:
// 1. The 3-node is transformed into TWO 2-nodes
// 2. The 2-node with the larger key becomes the parent of the 2-node with the smaller key
// 3. The two 2-nodes are connected by a left-leaning right link
//
// EXAMPLE:
// 
//?     [1,4]                   4
//      / | \     BECOMES     */ \                   */ represents a red-link
//     0  3  6                1   6
//                           / \
//                          0   3
//
// (view red-link as the "glue" holding the 3-node together)


// * ---COLOR REPRESENTATION---
// - If a child's link to their parent is red: child.color = RED
//      - View node as a "red" node
// - All other nodes are "black" (including null nodes)


// * ---OPERATIONS---
// Methods not in standard BST:
// - private boolean isRed(Node x)
// - private Node rotateLeft(Node h)
// - private Node rotateRight(Node h)
// - private void flipColors(Node h)
// - private Node moveRedLeft(Node h)
// - private Node moveRedRight(Node h)
// - private Node balance(Node h)

// * ---SIDE NOTE---
// - Notice how all ADDITIONAL methods to normal BST are private
// - ie the client implementation is identical for red-black trees and BSTs

// * ---ROTATION---
// Left rotation:
// - Orient a (temporarily) right-leaning red-link to the left
// - Used to fix INCORRECT leaning red-links (that occasionally form during insertion)
//
// EXAMPLE:
//
//          INCORRECT             CORRECT
//           ------                -----
//?            1                     4
//            / \*                 */ \
//           0   4      --->       1   6
//              / \               / \
//             3   6             0   3


// Right rotation:
// - Orient a (temporarily) left-leaning red-link to the right
// - Used to create (temporary) INCORRECT leaning red-links (sometimes required in insertion)
//
// EXAMPLE:
//
//          CORRECT             INCORRECT
//           -----                ------
//?            4                    1
//.          */ \                  / \*
//           1   6      --->      0   4
//          / \                      / \
//         0   3                    3   6

// * ---COLOR FLIPPING---
// - Re-color to split a temporary 4-node
// - Nodes are NOT MOVED
//
// Process:
// 1. Make both links from middle-key node BLACK
// 2. Make the link from middle-key to their parent RED
// 
// EXAMPLE
// 
//            |                    *|
//?           6                     6
//.        */  *\                 /   \
//         2     10     --->     2     10   
//        / \   /  \            / \   /  \    
//       1   4 8   12          1   4 8   12
//?    (2 red links from)     (1 red links to)


// * ---SEARCHING ANALYSIS---
// Average case: O(log n)
// Worst case: O(2 log n)


// * ---INSERTING ANALYSIS---
// Average case: O(log n)
// Worst case: O(2 log n)


// * ---SIDE NOTE---
// The 'HOLY GRAIL' of search/insertion time-complexity is O(log n)


// * ---THEOREMS---
// - The height of a red-black BST with N nodes is no more than 2 log N.
// - The average length of a path from the root to a node in a red-black BST with N nodes is ~1.00 lg N. 
