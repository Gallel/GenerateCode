
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {}

    @Override
    public void deleteEdge(Edge<L,E> aresta) {}

    @Override
    public void deleteVertex(Vertex<E> vertex) {}

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {}

    @Override
    public Vertex<E> getVertex(E elem) {}

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {}

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {}

    @Override
    public Vertex<E> newVertex(E valor) {}

    @Override
    public int numVertexs() {}

    @Override
    public String toString() {}

    @Override
    public Iterator<Edge<L,E>> edges() {}

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {}

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {}

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {}

    @Override
    public Iterator<Vertex<E>> vertexs() {}
}
