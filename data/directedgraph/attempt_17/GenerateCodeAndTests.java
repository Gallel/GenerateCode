
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    public DirectedGraphImpl() {
        // constructor body
    }
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // method body
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // method body
    }
    
    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        // method body
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // method body
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // method body
    }
    
    @Override
    public Iterator<Edge<L,E>> edges() {
        // method body
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // method body
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // method body
    }
    
    @Override
    public Vertex<E> getVertex(E elem) {
        // method body
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // method body
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // method body
    }
    
    @Override
    public Vertex<E> newVertex(E valor) {
        // method body
    }
    
    @Override
    public int numVertexs() {
        // method body
    }
    
    @Override
    public String toString() {
        // method body
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs() {
        // method body
    }
}
