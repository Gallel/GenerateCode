
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // write code here
    }

    @Override
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // write code here
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        // write code here
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // write code here
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // write code here
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        // write code here
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // write code here
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // write code here
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        // write code here
    }

    @Override
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // write code here
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // write code here
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        // write code here
    }

    @Override
    public int numVertexs() {
        // write code here
    }

    @Override
    public String toString() {
        // write code here
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        // write code here
    }
}
