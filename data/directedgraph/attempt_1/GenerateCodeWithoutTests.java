
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
        // (Constructor code)
    }
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        // (Code to get the adjacency list of a vertex)
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        // (Code to create a new vertex)
    }
    
    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        // (Code to delete an edge from the graph)
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex) {
        // (Code to delete a vertex and its corresponding edges from the graph)
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        // (Code to get the edges with destination at a certain vertex)
    }
    
    @Override
    public Iterator<Edge<L,E>> edges() {
        // (Code to get all the edges of the graph)
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        // (Code to get the edges with source at a certain vertex)
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        // (Code to get a certain edge between two vertices)
    }
    
    @Override
    public Vertex<E> getVertex(E elem) {
        // (Code to get a certain vertex based on a given element)
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        // (Code to create and return a new dictionary of vertices)
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        // (Code to create and include a new edge in the graph)
    }
    
    @Override
    public Vertex<E> newVertex(E valor) {
        // (Code to create and include a new vertex in the graph)
    }
    
    @Override
    public int numVertexs() {
        // (Code to get the number of vertices in the graph)
    }
    
    @Override
    public String toString() {
        // (Code to convert the graph to a string representation)
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs() {
        // (Code to get all the vertices of the graph)
    }
}
