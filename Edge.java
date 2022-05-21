public class Edge
{
	private String vtx1;
	private String vtx2;
	private Float label;
	private boolean visited;
	private boolean directed;

	public Edge(String vtx1, String vtx2, Float label,
	boolean directed)
	// post: edge associates vtx1 and vtx2; labeled with label
	// directed if "directed" set true
	{
		this.vtx1 = vtx1;
		this.vtx2 = vtx2;
		this.label = label;
		this.directed = directed;
		visited = false;
	}
	/**
	 * 
	 * Can be zero or infinity
	 * @param infinity
	 */
	public Edge(boolean isInfinity){
		if (isInfinity) {
			this.label = Float.POSITIVE_INFINITY;
		} else {
			this.label = 0f;
		}
		this.directed = true;
		visited = false;
	}

	public String here()
	// post: returns first node in edge
	{
		return vtx1;
	}

	public String there()
	// post: returns second node in edge
	{
		return vtx2;
	}
	
	public void setLabel(Float label)
	// post: sets label of this edge to label
	{
		this.label = label;
	}
	
	public Float label()
	// post: returns label associated with this edge
	{
		return label;
	}

	public boolean visit()
	// post: visits edge, returns whether previously visited
	{
		boolean prev = visited;
		visited = true;
		return prev;
	}

	public boolean isVisited()
	// post: returns true iff edge has been visited
	{
		return visited;
	}

	public boolean isDirected()
	// post: returns true iff edge is directed
	{
		return directed;
	}


	public void reset()
	// post: resets edge's visited flag to initial state
	{
		visited = false;
	}

	public boolean equals(Edge o)
	// post: returns true iff edges connect same vertices
	{
		boolean eq = true;
		if (!o.here().equals(vtx1)||!o.there().equals(vtx2)) {
			eq = false;
		}
		return eq; 
	}

	@Override
	public String toString() {
		if (label.equals(Float.POSITIVE_INFINITY)) {
			return " & ";
		} else {
			return label().toString();
		}
	}
}