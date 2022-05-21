class GraphMatrixVertex extends Vertex
{
    protected int index;

    public GraphMatrixVertex(String label, int idx)
    {
        super(label);
        index = idx;
    }

    public int index()
    {
        return index;
    }

    
}
