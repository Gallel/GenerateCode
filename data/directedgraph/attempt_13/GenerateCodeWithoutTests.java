
public class DirectedGraphImpl<E, L> extends Object implements DirectedGraph<E, L> {

    public DirectedGraphImpl() {
        // Constructor implementation here
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // Implementation of the adjacencyList method here
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E, L> createVertex(E value) {
        // Implementation of the createVertex method here
        return null;
    }

    @Override
    public void deleteEdge(Edge<L, E> aresta) {
        // Implementation of the deleteEdge method here
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // Implementation of the deleteVertex method here
    }

    @Override
    public Iterator<Edge<L, E>> edgedWithDestA(Vertex<E> vertex) {
        // Implementation of the edgedWithDestA method here
        return null;
    }

    @Override
    public Iterator<Edge<L, E>> edges() {
        // Implementation of the edges method here
        return null;
    }

    @Override
    public Iterator<Edge<L, E>> edgesWithSource(Vertex<E> vertex) {
        // Implementation of the edgesWithSource method here
        return null;
    }

    @Override
    public DirectedEdge<L, E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // Implementation of the getEdge method here
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        // Implementation of the getVertex method here
        return null;
    }

    protected Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        // Implementation of the newDictionaryVertexs method here
        return null;
    }

    @Override
    public DirectedEdge<L, E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // Implementation of the newEdge method here
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        // Implementation of the newVertex method here
        return null;
    }

    @Override
    public int numVertexs() {
        // Implementation of the numVertexs method here
        return 0;
    }

    @Override
    public String toString() {
        // Implementation of the toString method here
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        // Implementation of the vertexs method here
        return null;
    }
}
