
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // code here
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // code here
        return null;
    }

    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        // code here
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // code here
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // code here
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        // code here
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // code here
        return null;
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // code here
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        // code here
        return null;
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // code here
        return null;
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // code here
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        // code here
        return null;
    }

    @Override
    public int numVertexs() {
        // code here
        return 0;
    }

    @Override
    public String toString() {
        // code here
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        // code here
        return null;
    }
}
