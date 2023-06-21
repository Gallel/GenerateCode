
public class DirectedGraphImpl<E, L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl(){}

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //code for obtaining iterator to adjacent vertices
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //code for creating new vertex
    }

    @Override
    public void deleteEdge(Edge<L,E> edge) {
        //code for deleting an edge from the graph
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //code for deleting a vertex and all its associated edges
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //code for obtaining iterator to edges with destination at vertex
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        //code for obtaining iterator to all edges in the graph
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //code for obtaining iterator to edges with source at vertex
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //code for obtaining edge between two vertices
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        //code for obtaining vertex corresponding to an element
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //code for creating a new dictionary of vertices
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //code for creating and adding a new edge to the graph
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        //code for creating and adding a new vertex to the graph
    }

    @Override
    public int numVertexs() {
        //code for obtaining the number of vertices in the graph
    }

    @Override
    public String toString() {
        //code for returning a string representation of the graph
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        //code for obtaining iterator to all vertices in the graph
    }
}
