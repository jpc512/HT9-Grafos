import java.util.Iterator;

public class GraphMatrixDirected extends GraphMatrix
{
	public GraphMatrixDirected(int size)
	// pre: size > 0
	// post: constructs an empty graph that may be expanded to
	// at most size vertices. Graph is directed if dir true
	// and undirected otherwise
	{
		super(size,true);
	}


	public void addEdge(String vLabel1, String vLabel2, Float label)
	// pre: vLabel1 and vLabel2 are labels of existing vertices
	// post: an edge is inserted between vLabel1 and vLabel2;
	// if edge is new, it is labeled with label (can be null)
	{
		GraphMatrixVertex  vtx1,vtx2;
		// get vertices
		if (!dict.containsKey(vLabel1)) {add(vLabel1);}
		if (!dict.containsKey(vLabel2)) {add(vLabel2);}
		vtx1 = dict.get(vLabel1);
		vtx2 = dict.get(vLabel2);
		// update matrix with new edge
		Edge e = new Edge(vtx1.label(),vtx2.label(),label,true);
		data[vtx1.index()][vtx2.index()] = e;
	}

	

	@Override
	public Float removeEdge(String vLabel1, String vLabel2) {
		GraphMatrixVertex vtx1,vtx2;
		// get vertices
		vtx1 = dict.get(vLabel1);
		vtx2 = dict.get(vLabel2);
		// update matrix with new edge
		Edge e = data[vtx1.index()][vtx2.index()];	
		data[vtx1.index()][vtx2.index()] = null;
		return e.label();
	}


}