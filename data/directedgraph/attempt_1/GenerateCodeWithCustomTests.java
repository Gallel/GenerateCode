
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L>{

    public DirectedGraphImpl(){}

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E, L> createVertex(E value) {
        return null;
    }

    @Override
    public void deleteEdge(Edge<L, E> aresta) {
        
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        
    }

    @Override
    public Iterator<Edge<L, E>> edgedWithDestA(Vertex<E> vertex) {
        return null;
    }

    @Override
    public Iterator<Edge<L, E>> edges() {
        return null;
    }

    @Override
    public Iterator<Edge<L, E>> edgesWithSource(Vertex<E> vertex) {
        return null;
    }

    @Override
    public DirectedEdge<L, E> getEdge(Vertex<E> src, Vertex<E> dest) {
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        return null;
    }

    protected Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        return null;
    }

    @Override
    public DirectedEdge<L, E> newEdge(Vertex<E> src, Vertex<E> dest) {
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        return null;
    }

    @Override
    public int numVertexs() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        return null;
    }

}
