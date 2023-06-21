
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    // default constructor
    public DirectedGraphImpl() {}
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // code here
    }
    
    // protected method to create a new vertex
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // code here
    }
    
    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        // code here
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // code here
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // code here
    }
    
    @Override
    public Iterator<Edge<L,E>> edges() {
        // code here
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // code here
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // code here
    }
    
    // returns the vertex corresponding to a certain element
    @Override
    public Vertex<E> getVertex(E elem) {
        // code here
    }
    
    // protected method to create a new dictionary of vertices
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // code here
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // code here
    }
    
    @Override
    public Vertex<E> newVertex(E valor) {
        // code here
    }
    
    @Override
    public int numVertexs() {
        // code here
    }
    
    @Override
    public String toString() {
        // code here
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs() {
        // code here
    }
}
