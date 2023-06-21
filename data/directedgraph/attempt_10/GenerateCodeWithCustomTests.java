
public class DirectedGraphImpl<E, L> extends Object implements DirectedGraph<E, L> {

    public DirectedGraphImpl() {}

    @Override
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E, L> createVertex(E value) {
        // Write code here
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        // Write code here
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        // Write code here
    }

    @Override
    public DirectedEdge<L, E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // Write code here
    }

    @Override
    public void deleteEdge(Edge<L, E> aresta) {
        // Write code here
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // Write code here
    }

    @Override
    public DirectedEdge<L, E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // Write code here
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // Write code here
    }

    @Override
    public Iterator<Edge<L, E>> edges() {
        // Write code here
    }

    @Override
    public Iterator<Edge<L, E>> edgesWithSource(Vertex<E> vertex) {
        // Write code here
    }

    @Override
    public Iterator<Edge<L, E>> edgedWithDestA(Vertex<E> vertex) {
        // Write code here
    }

    @Override
    public int numVertexs() {
        // Write code here
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        // Write code here
    }

    protected Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        // Write code here
    }

    @Override
    public String toString() {
        // Write code here
    }
} 