// * ---DEFINITION---
// - A shortest path from vertex s to t in an edge-weighted digraph is a
// directed path with the lowest weight compared to any other path from s to t.

// * ---SHORTEST PATH PROPERTIES---

// ? Paths are directed:
// - A shortest path must respect the direction of its edges.

// ? The weights are not necessarily distances:
// - Weights may represent various variables, not just distances

// ? Not all vertices need to be reachable from one another:
// - If t is not reachable from s, there is no path at all, and therefore
// there is no shortest path from s to t.

// ? Assume weights are non-negative

// ? Shortest paths may not be unique:
// - There may be multiple paths of the lowest weight from vertex to w (any of
// them will suffice)

// ? Parallel edges and self-loops may be present:
// - Only the lowest-weight among a set of parallel edges will play a role
// - No shortest path contains a self-loop

// * ---DEFINITION: SHORTEST PATH TREE---
// - A shortest-paths tree for a source s is a subgraph containing s and all the
// vertices reachable from s that forms a directed tree rooted at s such that
// every tree path is a shortest path in the digraph.

// * ---DATA TYPES---

// ? public class EdgeWeightedDigraph:
// Attributes:
// - int V: no. of vertices
// - int E: no. of edges
// - Bag<Integer>[] adj: Bag of adjacent vertices ?!?!?!
//
// Methods:
// - EdgeWeightedDigraph(int V): Creates an empty V-vertex graph
// - EdgeWeightedDigraph(In in): Reads a graph from In in
// - int V(): Returns the number of vertices
// - int E(): Returns the number of edges
// - void addEdge(DirectedEdge e): Adds the edge 'e' to this digraph
// - Iterable<DirectedEdge> adj(int v): Returns edges pointing from vertex v
// - Iterable<DirectedEdge> edges(): Returns all edges in this digraph
// - String toString(): Returns a string representation of the digraph

// ? public class DirectedEdge
// Attributes:
// - int v: Edge source
// - int w: Edge target
// - double weight: Edge weight
//
// Methods:
// - DirectedEdge(int v, int w, double weight): Constructs a directed edge
// (v->w) with a specified weight
//
// - double weight(): Returns the weight of this edge
// - int from(): Returns the vertex this edge points from
// - int to(): Returns the vertex this edge points to
// - String toString(): Returns a string representation of the directed edge

// ? public class SP:
// - SP(EdgeWeightedDigraph G, int s): Constructor for shortest path in Graph
// G starting from vertex s
//
// - double distTo(int v): Returns the distance from vertex s to v; returns ∞ if
// there is no path
//
// - boolean hasPathTo(int v): Checks if there is a path from s to v
//
// - Iterable<DirectedEdge> pathTo(int v): Returns the path from s to v or
// returns null if there is no path

// ? Data structures for shortest paths:
// - Edges on the shortest-paths tree: Utilizing a vertex-indexed array edgeTo[]
// with DirectedEdge objects, each vertex is connected to its parent in the tree
//
// - Distance to the source: A vertex-indexed array distTo[] stores the length
// of the shortest known path from the source s to each vertex v.
//
// ---SIDE NOTE--- : A parent refers to a vertex that is directly connected to
// another vertex via an edge on a specific path or tree.

// ! CONTINUE FROM pg646