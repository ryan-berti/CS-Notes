// * ---DIGRAPH DATA TYPE---

// ? Attributes:
// - int V: no. of vertices
// - int E: no. of edges
// - Bag<Integer>[] adj: Bags of adjacent vertices

// ? Constructors:
// - Digraph(int V)
// - Digraph(In in)

// ! NB
// ? Methods:
// - Digraph(int V): Initializes an empty digraph with V vertices.
// - Digraph(In in): Initializes a digraph from an In in
// - int V(): Returns the number of vertices in the digraph.
// - int E(): Returns the number of edges in the digraph.
// - void addEdge(int v, int w): Adds the directed edge v -> w to the digraph.
// - Iterable<Integer> adj(int v): Returns the out-neighbors of vertex v
// - Digraph reverse(): Returns the reverse of the digraph.
//
// - String toString(): Returns a string representation of the digraph, showing
// the number of vertices, the number of edges, and the adjacency list

// * ---DIGRAPHS VS UNDIRECTED GRAPHS---

// ? Edges:
// - Undirected edges connect two vertices in both directions
// - Directed edges connect two vertices in one direction

// ? addEdge(int v, int w):
// - Graph: calls adj[v].add(w) and adj[w].add(v) (v-w and w-v)
// - Digraph: only calls adj[v].add(w) (v -> w)

// ? Same methods:
// - Digraph class contains all the methods Graph class
// - Digraph class contains 1 additional method: reverse()

// * ---REACHABILITY---
// - Use DFS in the same manner as for undirected graphs, except during DFS, the
// only instance variable mutated is marked[] (no edgeTo[] array)

// ? Attributes:
// - boolean[] marked: marked[v] = true if vertex v is reachable from source

// ? public class DirectedDFS:
// - DirectedDFS(Digraph G, int s): Initializes DirectedDFS with digraph G and
// single source vertex s.
//
// - DirectedDFS(Digraph G, Iterable<Integer> sources): Initializes DirectedDFS
// with digraph G and multiple source vertices.
//
// - void dfs(Digraph G, int v): Performs depth-first search (DFS) on digraph G
// starting from vertex v.
//
// - boolean marked(int v): Returns true if vertex v has been visited (marked)
// during DFS, otherwise false.

// ? Mark-and-sweep garbage collection:
// - Two-phase memory management technique:
// 1. Mark: Traverses a directed graph of objects and references, marking
// reachable objects from root objects.
//
// 2. Sweep: Collects unmarked objects, returning their memory for future use.

// ? Path-finding
// - Paths in directed graphs can be found using DFS or BFS
// - Path-finding algorithms are the same for directed and undirected graphs

// * ---CYCLES AND DAGS---

// ? Terminology:
// - Directed acyclic graph (DAG): a digraph with no directed cycles
//
// - Topological sort: Topological sort orders vertices of a directed acyclic
// graph (DAG) so that for each edge (u, v), u comes before v, or reports
// impossibility if cycles exist.
//
// - Precedence-constrained scheduling: Precedence-constrained scheduling
// arranges tasks while respecting dependencies, where some tasks must finish
// before others start.

// ? Proposition E:
// - A digraph has a topological order iff it is a DAG (Must be acyclic)

// ? public class DirectedCycle:
// - DirectedCycle(Graph G)
// - boolean hasCycle()
// - Iterable<Integer> cycle()

// ? Topological class methods:
// - Topological(Digraph G)
// - void dfs(Digraph G, int v)

// ? DepthFirstOrder class methods:
// - DepthFirstOrder(Digraph G)
// - void dfs(Digraph G, int v)

// ? Using DFS to order vertices:
// 1. Pre-order: Put the vertex on a queue before the recursive calls
// 2. Post-order: Put the vertex on a queue after the recursive calls
// 3. Reverse post-order: Put the vertex on a stack after the recursive calls
