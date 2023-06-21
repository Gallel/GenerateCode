
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
        //constructor code
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //code to obtain adjacent vertices
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //code to create a new vertex
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        //code to delete an edge
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //code to delete a vertex
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDest(Vertex<E> vertex) {
        //code to obtain edges with destination in a vertex
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        //code to obtain all edges
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //code to obtain edges with source in a vertex
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //code to obtain an edge between two vertices
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        //code to obtain a vertex with a given element
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //code to create a new dictionary of vertices
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //code to create a new edge
    }

    @Override
    public Vertex<E> newVertex(E value) {
        //code to create a new vertex
    }

    @Override
    public int numVertexs() {
        //code to obtain the number of vertices in the graph
    }

    @Override
    public String toString() {
        //code to convert the graph to a string
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        //code to obtain all vertices
    }
}
