
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {}

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        return ((VertexImpl<E,L>) vertex).adjacencyIterator();
    }

    @Override
    protected VertexImpl<E,L> createVertex(E value) {
        return new VertexImpl<>(value);
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        ((EdgeImpl<L,E>) edge).getSource().removeOutgoingEdge(edge);
        ((EdgeImpl<L,E>) edge).getDest().removeIncomingEdge(edge);
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        VertexImpl<E,L> src = ((VertexImpl<E,L>) vertex);

        Iterator<Edge<L,E>> it = this.edgesWithSource(vertex);
        while (it.hasNext()) {
            this.deleteEdge(it.next());
        }

        it = this.edgedWithDestA(vertex);
        while (it.hasNext()) {
            this.deleteEdge(it.next());
        }

        src.getGraph().removeVertex(src);
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        return ((VertexImpl<E,L>) vertex).incomingIterator();
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        return new EdgesIteratorImpl<>(this);
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        return ((VertexImpl<E,L>) vertex).outgoingIterator();
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        return ((VertexImpl<E,L>) src).getOutgoingEdge(dest);
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        return vertices.get(elem);
    }

    @Override
    protected Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        return new DictionaryImpl<>();
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        return new EdgeImpl<>((VertexImpl<E,L>)src, (VertexImpl<E,L>) dest);
    }

    @Override
    public Vertex<E> newVertex(E value) {
        VertexImpl<E,L> vertex = new VertexImpl<>(value);
        this.vertices.insert(value, vertex);
        vertex.setGraph(this);
        return vertex;
    }

    @Override
    public int numVertexs() {
        return vertices.size();
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        return new VertexsIteratorImpl<>(this);
    }

    @Override
    public String toString() {
        return "DirectedGraphImpl{" + "vertices=" + vertices + '}';
    }
}
