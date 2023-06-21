
public class DirectedGraphImpl<E, L> extends Object implements DirectedGraph<E, L> {
    
    public DirectedGraphImpl() {
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E, L> createVertex(E value) {
        return null;
    }
    
    public void deleteEdge(Edge<L, E> aresta) {
    }
    
    public void deleteVertex(Vertex<E> vertex) {
    }
    
    public DirectedEdge<L, E> newEdge(Vertex<E> src, Vertex<E> dest) {
        return null;
    }
    
    public Vertex<E> newVertex(E valor) {
        return null;
    }
    
    public DirectedEdge<L, E> getEdge(Vertex<E> src, Vertex<E> dest) {
        return null;
    }
    
    public Iterator<Edge<L, E>> edges() {
        return null;
    }
    
    public Iterator<Edge<L, E>> edgesWithSource(Vertex<E> vertex) {
        return null;
    }
    
    public Iterator<Edge<L, E>> edgedWithDestA(Vertex<E> vertex) {
        return null;
    }
    
    public int numVertexs() {
        return 0;
    }
    
    public Vertex<E> getVertex(E elem) {
        return null;
    }
    
    public Iterator<Vertex<E>> vertexs() {
        return null;
    }
    
    protected Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        return null;
    }
    
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        return null;
    }
    
    public String toString() {
        return null;
    }
}
