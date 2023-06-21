
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E, L> {

    public DirectedGraphImpl() {}

    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // code to get adjacent vertices
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // code to create a new vertex
    }

    public void deleteEdge(Edge<L,E> aresta) {
        // code to delete an edge
    }

    public void deleteVertex(Vertex<E> vertex) {
        // code to delete a vertex
    }

    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // code to get edges with destination vertex
    }

    public Iterator<Edge<L,E>> edges() {
        // code to get all edges
    }

    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // code to get edges with source vertex
    }

    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // code to get edge between two vertices
    }

    public Vertex<E> getVertex(E elem) {
        // code to get vertex with given element
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // code to create new dictionary for vertices
    }

    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // code to create a new edge
    }

    public Vertex<E> newVertex(E valor) {
        // code to create a new vertex
    }

    public int numVertexs() {
        // code to get number of vertices
    }

    public String toString() {
        // code to convert graph to string
    }

    public Iterator<Vertex<E>> vertexs() {
        // code to get all vertices
    }
}
