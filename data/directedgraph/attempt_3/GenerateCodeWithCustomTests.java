
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {}
    
    public void deleteEdge(Edge<L,E> aresta) {}
    
    public void deleteVertex(Vertex<E> vertex) {}
    
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {}
    
    public Vertex<E> newVertex(E valor) {}
    
    public int numVertexs() {}
    
    public Vertex<E> getVertex(E elem) {}
    
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {}
    
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {}
    
    public Iterator<Edge<L,E>> edges() {}
    
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {}
    
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {}
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {}

    public String toString() {}

    public Iterator<Vertex<E>> vertexs() {}
}
