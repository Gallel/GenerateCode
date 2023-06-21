
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}

    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // implementation
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // implementation
    }

    public void deleteEdge(Edge<L,E> aresta) {
        // implementation
    }

    public void deleteVertex(Vertex<E> vertex) {
        // implementation
    }

    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // implementation
    }

    public Iterator<Edge<L,E>> edges() {
        // implementation
    }

    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // implementation
    }

    public DirectedEdge<L,E> getEdge(Vertex<E> src,
        Vertex<E> dest) {
        // implementation
    }

    public Vertex<E> getVertex(E elem) {
        // implementation
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // implementation
    }

    public DirectedEdge<L,E> newEdge(Vertex<E> src,
        Vertex<E> dest) {
        // implementation
    }

    public Vertex<E> newVertex(E valor) {
        // implementation
    }

    public int numVertexs() {
        // implementation
    }

    public String toString() {
        // implementation
    }

    public Iterator<Vertex<E>> vertexs() {
        // implementation
    }

}
