
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    public DirectedGraphImpl() {}
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {}
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {}
    
    public Vertex<E> newVertex(E valor) {}
    
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {}
    
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {}
    
    public Iterator<Edge<L,E>> edges() {}
    
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {}
    
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {}
    
    public int numVertexs() {}
    
    public Vertex<E> getVertex(E elem) {}
    
    public void deleteVertex(Vertex<E> vertex) {}
    
    public void deleteEdge(Edge<L,E> edge) {}
    
    public Iterator<Vertex<E>> vertexs() {}
    
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {}
    
    public String toString() {}
}
