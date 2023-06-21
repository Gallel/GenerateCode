
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    public DirectedGraphImpl() {}
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //code to be implemented
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //code to be implemented
    }
    
    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        //code to be implemented
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //code to be implemented
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //code to be implemented
    }
    
    @Override
    public Iterator<Edge<L,E>> edges() {
        //code to be implemented
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //code to be implemented
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //code to be implemented
    }
    
    @Override
    public Vertex<E> getVertex(E elem) {
        //code to be implemented
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //code to be implemented
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //code to be implemented
    }
    
    @Override
    public Vertex<E> newVertex(E valor) {
        //code to be implemented
    }
    
    @Override
    public int numVertexs() {
        //code to be implemented
    }
    
    @Override
    public String toString() {
        //code to be implemented
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs() {
        //code to be implemented
    }
}
