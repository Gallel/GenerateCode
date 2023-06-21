
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
        (constructor code here)
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        (method code here)
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        (method code here)
    }

    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        (method code here)
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        (method code here)
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        (method code here)
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        (method code here)
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        (method code here)
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        (method code here)
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        (method code here)
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        (method code here)
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        (method code here)
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        (method code here)
    }

    @Override
    public int numVertexs() {
        (method code here)
    }

    @Override
    public String toString() {
        (method code here)
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        (method code here)
    }
}
