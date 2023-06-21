
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}

    @Override
    public Vertex<E> newVertex(E valor) {...}

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {...}

    @Override
    public int numVertexs() {...}

    @Override
    public Iterator<Vertex<E>> vertexs() {...}

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {...}

    @Override
    public Iterator<Edge<L,E>> edges() {...}

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {...}

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {...}

    @Override
    public Vertex<E> getVertex(E elem) {...}

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {...}

    @Override
    public void deleteEdge(Edge<L,E> aresta) {...}

    @Override
    public void deleteVertex(Vertex<E> vertix) {...}

    @Override
    public String toString() {...}

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {...}

    protected VertexImpl<E,L> createVertex(E value) {...}
}
