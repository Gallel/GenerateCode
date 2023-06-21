
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
    }
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //implementation
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //implementation
    }
    
    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        //implementation
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //implementation
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //implementation
    }
    
    @Override
    public Iterator<Edge<L,E>> edges() {
        //implementation
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //implementation
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //implementation
    }
    
    @Override
    public Vertex<E> getVertex(E elem) {
        //implementation
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //implementation
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //implementation
    }
    
    @Override
    public Vertex<E> newVertex(E valor) {
        //implementation
    }
    
    @Override
    public int numVertexs() {
        //implementation
    }
    
    @Override
    public String toString() {
        //implementation
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs() {
        //implementation
    }
}
