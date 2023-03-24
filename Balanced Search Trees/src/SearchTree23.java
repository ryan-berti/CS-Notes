// In a balanced search tree the heights of the left and right sub-trees may have a maximum DIFFERENCE of 1
// ie a height of 6,7 (left,right) is balanced. A height of 2,4 implies the tree is unbalanced

// 2-3 Search Tree:
// 1. Every node is either 2-node or 3-node (or leaf) - ie CANNOT contain nodes with only 1 link
// 2. Null links are all the same distance from the root (Perfectly balanced)

// Allowing nodes to contain 1 OR 2 keys means that the tree can always be balanced (and thus avoiding the *terrible* linear performance)  

//            [x, y]
//         /    |    \
//     [  a ] [  b ] [ c  ]
//
//     (a) < x
// x > (b) < y
// x > (c) 

// Insertion: (insert node C)
// 1. Locate the insertion position (will be the last node reached if searching for C)
// 2. Insert the node:
// 2a. If inserting into a 2-node, simply change to a 3-node
// 2b. Inserting into a 3-node can take place in a variety of ways (listed below)

// 3-Node insertion scenarios:
// - Insert into a tree consisting of a single 3-node
// - Insert into a 3-node whose parent is a 2-node
// - Insert into a 3-node whose parent is a 3-node

public class SearchTree23 {
    
}
