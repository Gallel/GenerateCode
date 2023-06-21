
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl(){
        // constructor code
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // code for getting adjacent vertices
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // code for creating a new vertex
        return null;
    }

    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        // code for deleting an edge
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // code for deleting a vertex
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // code for getting edges with destination in vertex
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        // code for getting all edges
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // code for getting edges with source in vertex
        return null;
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // code for getting a specific edge
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        // code for getting a specific vertex
        return null;
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // code for creating a new dictionary of vertices
        return null;
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // code for creating a new edge
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        // code for creating and adding a new vertex
        return null;
    }

    @Override
    public int numVertexs() {
        // code for getting the number of vertices
        return 0;
    }

    @Override
    public String toString() {
        // code for converting graph to string
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        // code for getting all vertices
        return null;
    }
}
