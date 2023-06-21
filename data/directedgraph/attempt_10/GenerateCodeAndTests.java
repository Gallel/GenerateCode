
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //implementation code
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //implementation code
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        //implementation code
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //implementation code
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //implementation code
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        //implementation code
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //implementation code
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //implementation code
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        //implementation code
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //implementation code
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //implementation code
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        //implementation code
    }

    @Override
    public int numVertexs() {
        //implementation code
    }

    @Override
    public String toString() {
        //implementation code
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        //implementation code
    }
}
