// * ---INTRODUCTION---
// - A minimum spanning tree (MST) of an edge-weighted graph is a spanning tree
// whose weight (the sum of the weights of its edges) is no larger than the
// weight of any other spanning tree
//
// - A spanning tree of a graph is a connected subgraph with no cycles that
// includes all the vertices.

// ? Basic properties of a (normal) tree:
// 1. Adding an edge between two vertices creates a unique cycle
// 2. Removing an edge breaks the tree into 2 separate sub-trees
//

// ? Assumptions:
// 1. The graph is connected
// 2. Edge weights are not necessarily distances
// 3. Edge weights may be 0 or negative
// 4. Edge weights are all unique

// ? Cut property:
// - A cut is a partition of a graph's vertices into two nonempty disjoint sets
// - A crossing edge of a cut is an edge that connects two sets
//
// - Given any cut in an edge-weighted graph, the crossing edge of minimum
// weight is in the MST of the graph (NB !!!)

// * ---EDGE DATA TYPE---

// ? public class Edge implements Comparable<Edge>

// ? Constructors:
// - Edge(int v, int w, double weight)

// ? Methods:
// - double weight(): Returns the weight of this edge
// - int either(): Returns either of this edge's vertices
// - int other(int v): Returns the other vertex
// - int compareTo(Edge that): Compares this edge to 'that' edge
// - String toString(): Returns the string representation of the Edge

// * ---EDGE WEIGHTED GRAPH DATA TYPE---

// ? Constructors:
// - EdgeWeightedGraph(int V): Creates an empty V-vertex graph
// - EdgeWeightedGraph(In in): Reads a graph from In in

// ? Methods:
// - int V(): Returns no. of vertices
// - int E(): Returns no. of edges
// - void addEdge(Edge e): Adds Edge e to the graph
// - Iterable<Edge> adj(int v): Returns the edges incident to v
// - Iterable<Edge> edges(): Returns all of this graph's edges
// - String toString(): Returns the String representation of the graph

// * ---LAZY PRIM'S ALGORITHM---

// ? Data Structures:
// - Vertices: Use a vertex-indexed boolean array marked[], where marked[v] is
// true if v is on the tree.
//
// - Edges: Use either a queue mst to collect MST edges or a vertex-indexed
// array edgeTo[] of Edge objects, where edgeTo[v] is the Edge that connects v
// to the tree.
//
// - Crossing edges: Use a MinPQ<Edge> priority queue comparing edges by weight

// ? Attributes:
// - double weight: The total weight of the MST
// - Queue<Edge> mst: The edges in the MST
// - boolean[] marked: marked[v] = true if v is on tree
// - MinPQ<Edge> pq: Edges with one endpoint in tree

// ? Maintaining set of crossing edges:
// - Each time an edge is added, we also add a vertex to the tree
//
// - To maintain the set of crossing edges, we need to add to the priority queue
// all edges from that vertex to any non-tree vertex
//
// - Any edge connecting the vertex just added to a tree vertex that is already
// on the priority queue now becomes ineligible

// ? "Lazy" algorithm:
// - Do not update the priority queue every time we add a new vertex to the MST
// - Instead, defer eligibility test to when we remove edges from priority queue

// ? Algorithm steps:
// 1. Start with any single vertex as a tree. Mark this vertex as visited.
// 2. Add all the edges connected to this vertex to a MinPQ of crossing edges.
//
// 3. While MinPQ is not empty and the MST has less than V-1 edges:
// 3a. Dequeue the minimum-weight edge from the priority queue
// 3b. Check the vertices connected by this edge (v and w)
//
// 3c. If both vertices v and w are already marked (visited), discard the edge
// and continue to the next iteration (edge is ineligible)
//
// 3d. If either vertex v or w is not marked, add the edge to the MST, and mark
// the unvisited vertex.
//
// 3e. Add all the edges connected to the newly marked vertex to the priority
// queue of crossing edges.
//
// 4. Once the loop terminates, the MST is formed.

// * ---EAGER PRIM'S ALGORITHM---

// ? Attributes:
// - Edge[] edgeTo: edgeTo[v] = shortest edge from tree vertex to v
// - double[] distTo: distTo[v] = weight of such edge
// - boolean[] marked: marked[v] = true if v is on on tree
//
// - IndexMinPQ<Double> pq: A priority queue storing non-tree vertices,
// prioritized by the weight of their shortest edge

// ? Algorithm steps:
// 1. Start with an arbitrary vertex, mark it as visited, set its distance equal
// to 0 and add this vertex to the index minimum priority queue with distance 0.
//
// 2. While the priority queue is not empty:
// 2a. Dequeue the vertex with the minimum distance from the priority queue.
// 2b. Mark the dequeued vertex as visited
// 2c. Iterate through all the edges connected to the dequeued vertex:
// 2c(i). Find the other vertex (w) connected by the edge.
// 2c(ii). If vertex w is already visited, skip this edge (ineligible)
//
// 2c(iii). If the edge weight is less than the current distance to vertex,
// update edgeTo[w] distTo[w] and priority queue
//
// 3. Once the loop terminates, the MST is formed

// * ---KRUSKAL'S ALGORITHM---

// ? Algorithm steps:
// 1. Create a MinPQ pq and add all the edges from Graph G
//
// 2. Initialize a union-find (UF) data type to keep track of connected
// components
//
// 3. Initialize a Queue mst to store the edges of the MST
// 4. While the MinPQ is not empty and the MST has less than G.V() - 1 edges:
// 4a. Remove the edge with the smallest weight from the MinPQ.
//
// 4b. Check if the two vertices connected by the edge are in the same connected
// component using the union-find data structure uf.
//
// 4c. If they are not connected (i.e., adding the edge won't create a cycle),
// add the edge to the MST, update the MST's total weight, and merge the
// connected components in the union-find data structure.
//
// 5. After processing all edges, return the MST edges using the edges() method
// and the total weight of the MST using the weight() method.

// ! PRIM AND KRUSKAL PERFORMANCE