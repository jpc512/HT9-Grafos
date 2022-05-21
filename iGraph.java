import java.util.Iterator;

public interface iGraph
{

	public void add(String label);
	// pre: label is a non-null label for vertex
	// post: a vertex with label is added to graph
	// if vertex with label is already in graph, no action
	
	public void addEdge(String vtx1, String vtx2, Float label);
	// pre: vtx1 and vtx2 are labels of existing vertices
	// post: an edge (possibly directed) is inserted between
	// vtx1 and vtx2.
	
	public String remove(String label);
	// pre: label is non-null vertex label
	// post: vertex with "equals" label is removed, if found
	
	public Float removeEdge(String vLabel1, String vLabel2);
	// pre: vLabel1 and vLabel2 are labels of existing vertices
	// post: edge is removed, its label is returned
	
	public String get(String label);
	// post: returns actual label of indicated vertex
	
	public Edge getEdge(String label1, String label2);
	// post: returns actual edge between vertices
	
	public boolean contains(String label);
	// post: returns true iff vertex with "equals" label exists
	
	public boolean containsEdge(String vLabel1, String vLabel2);
	// post: returns true iff edge with "equals" label exists
	
	public boolean visit(String label);
	// post: sets visited flag on vertex, returns previous value
	
	public boolean visitEdge(Edge Float);
	// pre: sets visited flag on edge; returns previous value
	
	public boolean isVisited(String label);
	// post: returns visited flag on labeled vertex
	
	public boolean isVisitedEdge(Edge e);
	// post: returns visited flag on edge between vertices
	
	public void reset();
	// post: resets visited flags to false
	
	public int size();
	// post: returns the number of vertices in graph
	
	// public int degree(String label);
	// // pre: label labels an existing vertex
	// // post: returns the number of vertices adjacent to vertex
	
	// public int edgeCount();
	// // post: returns the number of edges in graph
	
	// public Iterator<String> iterator();
	// // post: returns iterator across all vertices of graph
	
	// public Iterator<String> neighbors(String label);
	// // pre: label is label of vertex in graph
	// // post: returns iterator over vertices adj. to vertex
	// // each edge beginning at label visited exactly once
	
	// public Iterator<Edge<String,E>> edges();
	// // post: returns iterator across edges of graph
	// // iterator returns edges; each edge visited once
	
	// public void clear();
	// // post: removes all vertices from graph
	
	// public boolean isEmpty();
	// // post: returns true if graph contains no vertices
	
	// public boolean isDirected();
	// // post: returns true if edges of graph are directed
}