
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    public DirectedGraphImpl() {}
    
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // code implementation here
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // code implementation here
    }
    
    public void deleteEdge(Edge<L,E> edge) {
        // code implementation here
    }
    
    public void deleteVertex(Vertex<E> vertex) {
        // code implementation here
    }
    
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // code implementation here
    }
    
    public Iterator<Edge<L,E>> edges() {
        // code implementation here
    }
    
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // code implementation here
    }
    
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // code implementation here
    }
    
    public Vertex<E> getVertex(E elem) {
         // code implementation here
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // code implementation here
    }
    
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // code implementation here
    }
    
    public Vertex<E> newVertex(E valor) {
        // code implementation here
    }
    
    public int numVertexs() {
        // code implementation here
    }
    
    public String toString() {
        // code implementation here
    }
    
    public Iterator<Vertex<E>> vertexs() {
        // code implementation here
    }
}
