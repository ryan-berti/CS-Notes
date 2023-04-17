// * ---TERMINOLOGY---
// - Self-loop
// - Parallel edges
// - Degree of a vertex
// - Path
// - Cycle
// - Tree
// - Subtree

// * ---GRAPH DATA TYPE---

// ? Attributes:
// - int V: no. of vertices
// - int E: no. of edges
// - Bag<Integer>[] adj: Bag of adjacent vertices ?!?!?!

// ? Constructors:
// - Graph(int V)
// - Graph(In in)

// ? Methods:
// ! EXPLAIN METHODS
// ---SIDE NOTE--- Some of these are additional methods from pg523 in textbook
//
// - int degrees(int V)
// - int maxDegree()
// - int avgDegree()
// - int numberOfSelfLoops()
// - String toString()
//
// Operations to consider:
// - Adding a vertex
// - Deleting a vertex
// - Deleting an edge
// - Checking whether the graph contains edge v-w

// ? Representation alternatives:
// - Adjacency matrix
// - Array of edges
// - Array of adjacency lists (NB)
//
// Performances for alternatives are on pg527

// * ---ADJACENCY-LISTS DATA STRUCTURE---

// ? ARRAY OF LINKED-LISTS !!

// ? Performance:
// - Space complexity: S(E + V)
// - Add edge v-w: O(1)
// - Check whether v is adjacent to w: O(degree(v))
// - Iterate through vertices adjacent to v: O(degree(v))

// ? Properties:
// - Parallel edges and self-loops are allowed
// - Adjacency lists are not unique to a graph (order of edges can be different)

// * ---DEPTH FIRST SEARCH---

// ? ---FORE NOTE--- Searching a graph is analogous to searching through a maze:
// 1. Take unmarked passages leaving breadcrumbs behind you
// 2. Mark all intersections and passages
// 3. Retrace steps when approaching a marked intersection
// 4. Retrace steps when no unvisited options remain at an intersection

// ? Overview:
// - Recursive method that visits vertices
//
// - To visit a vertex:
// 1. Mark it as visited
// 2. Recursively visit non-visited vertices that are adjacent to it
//
// - Imagine graph is constructed using only one-way edges (1 in each direction)
// - Order in which vertices/edges are examined depends on input order.

// ? private void dfs(Graph G, int v):
// 1. marked[v] is set to true to mark the current vertex as visited
// 2. Method iterates over all the adjacent vertices of v using G.adj(v)
// 3. Set the last vertex on path to w as v in edgeTo[w]
// 4. Return to the previous vertex and continue loop until all vertices visited
// 5. hasPathTo(int v) checks whether the target vertex v has been marked

// ? public Iterable<Integer> pathTo(int v)
// 1. If v has not been marked during the dfs() search, the method returns null

// 2. Otherwise, a new Stack object called path is created to store the path
// from the source vertex to v
//
// 3. Method iterates backwards from v to the source vertex, pushing each vertex
// onto the stack (NB!!!)
//
// 4. Iterate backwards from v to s, each iteration sets x to the prev. vertex
// on path using edgeTo[x].
//
// 5. Each vertex x is pushed onto the stack
// 6. When iteration is complete, the source vertex s is pushed onto the stack
// 7. The stack path is then returned as an Iterable object

// * ---DEPTH FIRST SEARCH---

// ? BFS vs DFS
// - BFS all the vertices at each level before moving on to the next level
// - Whereas DFS explores as far as possible down each path before backtracking
//
// - BFS uses a queue to keep track of the vertices to visit
// - Whereas DFS typically uses a stack
//
// - BFS can be used to find the shortest path between two vertices
// - Whereas DFS may not find the shortest path

// ? public void bfs(Graph G, int v)
// 1. A Queue object is created to keep track of the vertices to visit.
// 2. The source vertex s is marked as visited and added to the queue.
// 3. The method enters a while-loop that continues until the queue is empty.
//
// 4. For each iteration of the while-loop, the next vertex v is dequeued from
// the queue.
//
// 5. The method then iterates over adjacent vertices of v using G.adj(v).
//
// 6. For each unmarked adjacent vertex w, update edgeTo[w] to set v as the last
// vertex on a shortest path to w, mark w as visited, and add it to the queue.
//
// 7. Once all adjacent vertices of v have been processed, the while-loop
// continues with the next vertex in the queue.

// ? ---SIDE NOTE---
// public Iterable<Integer> pathTo(int v) for BFS is the SAME as DFS method

// * ---USING DFS TO FIND CONNECTED COMPONENTS---
// - Involves exploring the graph in a depth-first manner, marking the visited
// nodes, and assigning them to connected components.

// ? private void dfs(Graph G, int v):
// 1. Mark the current vertex v as visited
//
// 2. Assign the current node (v) to the current connected component (count),
// by setting id[v] equal to the count
//
// 3. Iterate through all adjacent vertices w of the current vertex v
// 4. If an adjacent vertex w is not visited, call dfs() recursively with w
// 5. When dfs() returns, backtrack and continue exploring other adjacent nodes

// * ---SYMBOL GRAPH DATA TYPE---

// ? Input format:
// - Vertex names are Strings
// - A specified delimiter separates vertex names
//
// - Each line represents a set of edges, connecting the first vertex name on
// the line to each of the other vertices named on the line
//
// - The number of vertices V and the number of edges E are implicitly defined

// ? Attributes:
// ST<String, Integer> st: Maps vertex names to unique integer indices.
// String[] keys: Array of vertex names (at correct index)
// Graph G: Underlying graph structure that uses adjacency lists

// ? Methods:
// - SymbolGraph(String stream, String sp): Initializes a graph from a file with
// a delimiter.
//
// - public boolean contains(String s): Checks if graph contains vertex "s"
// - public int index(String s) : Returns the index associated with vertex "s"
// - public String name(int v): Returns the name associated with an index
// - public Graph G(): Returns the underlying undirected graph

// ? Degrees of separation:
// - A concept used to measure the social distance between two individuals in a
// network (shortest path)