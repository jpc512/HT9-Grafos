class Vertex
{
	private String label;
	private boolean visited;

	public Vertex(String label)
	// post: constructs unvisited vertex with label
	{
		this.label = label;
	}
	
	public String label()
	// post: returns user label associated w/vertex
	{
		return label;
	}
	
	public boolean visit()
	// post: returns, then marks vertex as being visited
	{
		visited = true;
		return visited;
	}
	
	public boolean isVisited()
	// post: returns true iff vertex has been visited
	{
		return visited;
	}

	public void reset()
	// post: marks vertex unvisited
	{
		visited = false;
	}

	public boolean equals(Object o)
	// post: returns true iff vertex labels are equal
	{
		return o.equals(label);
	}
}