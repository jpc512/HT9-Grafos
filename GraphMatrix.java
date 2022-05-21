import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

abstract public class GraphMatrix
	implements iGraph
{
	protected int gsize; // allocation size for graph
	protected Edge data[][]; // matrix - array of arrays
	protected Map<String,GraphMatrixVertex> dict; // labels -> vertices
	protected Map<Integer,String> ids; // ids -> labels
	protected ArrayList<Integer> freeList; // available indices in matrix
	protected boolean directed; // graph is directed
	Edge[][] costos, caminos;

	protected GraphMatrix(int size, boolean dir)
	{
		this.gsize = size; // set maximum size
		directed = dir; // fix direction of edges
		// the following constructs a size x size matrix
		data = new Edge[size][size];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				data[i][j] = new Edge(true);
			}
		}
		// label to index translation table
		dict = new Hashtable<String,GraphMatrixVertex>(size);
		ids = new Hashtable<Integer,String>(size);
		// put all indices in the free list
		freeList = new ArrayList<Integer>();
		for (int row = size-1; row >= 0; row--){
			freeList.add(Integer.valueOf(row));
		}
			
	}

	public void add(String label)
	// pre: label is a non-null label for vertex
	// post: a vertex with label is added to graph;
	// if vertex with label is already in graph, no action
	{
		// if there already, do nothing
		if (dict.containsKey(label)) return;
		// verificar que aun existan indices disponibles para el vertice
		// allocate a free row and column
		int row = freeList.remove(0).intValue();
		// add vertex to dictionary
		ids.put(row, label);
		dict.put(label, new GraphMatrixVertex(label, row));
	}

	public String remove(String label)
	// pre: label is non-null vertex label
	// post: vertex with "equals" label is removed, if found
	{
		// find and extract vertex
		GraphMatrixVertex vert;
		vert = dict.remove(label);
		if (vert == null) return null;
		// remove vertex from matrix
		int index = vert.index();
		// clear row and column entries
		for (int row=0; row<gsize; row++) {
			data[row][index] = null;
			data[index][row] = null;
		}
		// add node index to free list
		freeList.add(Integer.valueOf(index));
		return vert.label();
	}

	abstract public void addEdge(String v1, String v2, Float label);
	// pre: vtx1 and vtx2 are labels of existing vertices
	// post: an edge (possibly directed) is inserted between
	// vtx1 and vtx2.
	
	@Override
	public String get(String label) {
		return label;
	}


	@Override
	public Edge getEdge(String label1, String label2) {
		GraphMatrixVertex v1 = dict.get(label1);
		GraphMatrixVertex v2 = dict.get(label2);
		int i1 = dict.get(label1).index;
		int i2 = dict.get(label1).index;
		Edge e = data[i1][i2];
		if (e != null) {
			return e;
		}
		return null;
	}


	@Override
	public boolean contains(String label) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsEdge(String vLabel1, String vLabel2) {
		return getEdge(vLabel1, vLabel2).equals(null);
	}


	@Override
	public boolean visit(String label) {
		GraphMatrixVertex vtx = dict.get(label);
		return vtx.visit();
	}


	@Override
	public boolean visitEdge(Edge e) {
		return e.visit();
	}


	@Override
	public boolean isVisited(String label) {
		return dict.get(label).isVisited();
	}


	@Override
	public boolean isVisitedEdge(Edge e) {
		return e.isVisited();
	}


	@Override
	public void reset() {
		for (Edge[] edges : data) {
			for (Edge edge : edges) {
				edge.reset();
			}
		}
		for (String key : dict.keySet()) {
			dict.get(key).visit();
		}
		
	}


	@Override
	public int size() {
		return dict.size();
	}

	public void floyd(){
		costos = new Edge[size()][size()];
		caminos = new Edge[size()][size()];

		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				Edge e =  data[i][j];
				costos[i][j] = new Edge(e.here(), e.there(), e.label(), true);
				caminos[i][j] = new Edge(false);
			}
		}

		

		
		
		
		
		for (int k=0; k<costos.length; k++) {
			for (int i=0; i<costos.length; i++) {
				for (int j=0; j<costos.length; j++) {
					if (costos[i][j].label() > costos[i][k].label() + costos[k][j].label()) {
						costos[i][j].setLabel(costos[i][k].label() + costos[k][j].label());
						if (i != j) {
							caminos[i][j].setLabel(Float.valueOf(k));;
						}
					}
				}
			}
		}
	}

	private ArrayList<Float> maxArray() {
		ArrayList<Float> maxArr = new ArrayList<Float>();
		for (int i=0; i<costos.length; i++) {
			float max = Float.NEGATIVE_INFINITY;
			for (Edge[] e : costos) {				
					if (max < e[i].label()) {
							max = e[i].label();
						}				
			}
			maxArr.add(max);
		}
		return maxArr;
	}

	public int argmin() {
		ArrayList<Float> maxArr = maxArray();
		float min = Float.POSITIVE_INFINITY;
		int id = 0;
		for (int i=0; i<maxArr.size(); i++) {
			if (maxArr.get(i) < min) {
				min = maxArr.get(i);
				id = i;
			}
		}
		return id;
	}

	public String getCentro() {
		floyd();
		int id = argmin();
		String center = ids.get(id);
		return center;
	}

	public String getCamino(int c1, int c2, String txt){

		if (!caminos[c1][c2].label().equals(0f)) {
			txt = getCamino(c1, (int) caminos[c1][c2].label().floatValue(), txt);
			txt += ids.get((int) caminos[c1][c2].label().floatValue()) + "--";
			txt = getCamino((int) caminos[c1][c2].label().floatValue(), c2, txt);
			return txt;
		}
		return txt;

	}

}