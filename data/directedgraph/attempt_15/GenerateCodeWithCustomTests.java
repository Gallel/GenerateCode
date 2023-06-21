
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}

    @Override
    public Vertex<E> newVertex(E valor) {
        return null;
    }

    @Override
    public DirectedEdge<L, E> newEdge(Vertex<E> src, Vertex<E> dest) {
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        return null;
    }

    @Override
    public int numVertexs() {
        return 0;
    }

    @Override
    public DirectedEdge<L, E> getEdge(Vertex<E> src, Vertex<E> dest) {
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        return null;
    }

    @Override
    public Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        return null;
    }

    @Override
    public Iterator<Edge<L, E>> edgesWithSource(Vertex<E> vertex) {
        return null;
    }

    @Override
    public Iterator<Edge<L, E>> edgesWithDest(Vertex<E> vertex) {
        return null;
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        return null;
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {}

    @Override
    public void deleteEdge(Edge<L, E> edge) {}

    @Override
    public Iterator<Edge<L, E>> edges() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        return null;
    }
}
