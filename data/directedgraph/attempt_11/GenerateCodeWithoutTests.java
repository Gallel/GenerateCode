
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
        //constructor
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //implementation
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //implementation
        return null;
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        //implementation
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //implementation
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDest(Vertex<E> vertex) {
        //implementation
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        //implementation
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //implementation
        return null;
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //implementation
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        //implementation
        return null;
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //implementation
        return null;
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //implementation
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        //implementation
        return null;
    }

    @Override
    public int numVertexs() {
        //implementation
        return 0;
    }

    @Override
    public String toString() {
        //implementation
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        //implementation
        return null;
    }
}
