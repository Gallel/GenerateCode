
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
        //constructor body
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //method body
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //method body
        return null;
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        //method body
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //method body
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //method body
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        //method body
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //method body
        return null;
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //method body
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        //method body
        return null;
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //method body
        return null;
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //method body
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        //method body
        return null;
    }

    @Override
    public int numVertexs() {
        //method body
        return 0;
    }

    @Override
    public String toString() {
        //method body
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        //method body
        return null;
    }
}
