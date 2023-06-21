
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    public DirectedGraphImpl() {
        // constructor code
    }
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // code to obtain iterator to adjacent vertices
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // code to create and return a new vertex
    }
    
    @Override
    public void deleteEdge(Edge<L,E> edge) {
        // code to delete the given edge, if present in the graph
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // code to delete the given vertex and all its incident edges, if present in the graph
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // code to obtain iterator to edges with destination at given vertex
    }
    
    @Override
    public Iterator<Edge<L,E>> edges() {
        // code to obtain iterator to all edges in the graph
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // code to obtain iterator to edges with source at given vertex
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // code to obtain and return the edge (if present) between the given vertices
    }
    
    @Override
    public Vertex<E> getVertex(E elem) {
        // code to obtain and return the vertex corresponding to the given element
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // code to create and return a new dictionary for storing vertices
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // code to create and add a new edge between the given vertices, and return it
    }
    
    @Override
    public Vertex<E> newVertex(E value) {
        // code to create and add a new vertex with the given value, and return it
    }
    
    @Override
    public int numVertexs() {
        // code to return the number of vertices in the graph
    }
    
    @Override
    public String toString() {
        // code to return a string representation of the graph
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs() {
        // code to obtain iterator to all vertices in the graph
    }
    
}
