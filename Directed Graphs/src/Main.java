// * ---TERMINOLOGY---
// - Directed graph (digraph)
// - Directed path
// - Directed cycle
// - in-degree
// - out-degree

// * ---DIGRAPH DATA TYPE---

// ? Attributes:
// - int V
// - private int E
// - private Bag<Integer>[] adj

// ? Constructors:
// - Digraph(int V)
// - Digraph(In in)

// ? Methods:
// - Digraph(int V): Initializes an empty digraph with V vertices.
// - Digraph(In in): Initializes a digraph from an In input
// - Digraph(Digraph G): Initializes a new digraph that is a deep copy of G.
// - int V(): Returns the number of vertices in the digraph.
// - int E(): Returns the number of edges in the digraph.
// - void addEdge(int v, int w): Adds the directed edge v -> w to the digraph.
// - Iterable<Integer> adj(int v): Returns the out-neighbors of vertex v
// - Digraph reverse(): Returns the reverse of the digraph.
//
// - String toString(): Returns a string representation of the digraph, showing
// the number of vertices, the number of edges, and the adjacency lists.

// * ---REACHABILITY---

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
//
// - main(String[] args): Reads digraph from input file and prints all reachable
// vertices from given source vertices.

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
// - DAGs: Directed acyclic graphs
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

// ? Finding directed cycles
// - Refer to DirectedCycle class (pg577)
// - BFS can be used to find cycles

// ? Vertex order:
// 1. Pre-order: Put the vertex on a queue before the recursive calls
// 2. Post-order: Put the vertex on a queue after the recursive calls
// 3. Reverse post-order: Put the vertex on a stack after the recursive calls

// * ---STRONG CONNECTIVITY---
// - Vertices v and w are strongly connected if they are mutually reachable
// - A graph is strongly connected if all vertices are strongly connected

// ? Strong components:
// - A strongly connected component (SCC) has the following properties:
// 1. Reflexive : Every vertex v is strongly connected to itself
// 2. Symmetric : If v is strongly connected to w, then w is also to v

// 3. Transitive : If v is strongly connected to w and w is strongly connected
// to x, then v is also strongly connected to x

// ? public class SCC
// - SCC(Digraph G): Preprocessing constructor
// - boolean stronglyConnected(int v, int w): Return true if v and w are
// strongly connected
//
// - int count(): no. of strong components
// - int id(int v): component identifier for v (between 0 and count() - 1)

// ! CONTINUE FROM KOSARAJU'S ALGORITHM (pg 586)
