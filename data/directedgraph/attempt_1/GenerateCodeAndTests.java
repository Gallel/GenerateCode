
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {
    
    public DirectedGraphImpl() {
        //constructor code here
    }
    
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //code to create vertex here
    }
    
    public void deleteEdge(Edge<L,E> aresta) {
        //code to delete edge here
    }
    
    public void deleteVertex(Vertex<E> vertex) {
        //code to delete vertex here
    }
    
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //code to get edge here
    }
    
    public Vertex<E> getVertex(E elem) {
        //code to get vertex here
    }
    
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //code to create new dictionary of vertices here
    }
    
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //code to create and add new edge here
    }
    
    public Vertex<E> newVertex(E valor) {
        //code to create and add new vertex here
    }
    
    public int numVertexs() {
        //code to get number of vertices here
    }
    
    public String toString() {
        //code to convert graph to string here
    }
    
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //code to get iterator to adjacent vertices here
    }
    
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //code to get iterator to edges with destination vertex here
    }
    
    public Iterator<Edge<L,E>> edges() {
        //code to get iterator to all edges here
    }
    
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //code to get iterator to edges with source vertex here
    }
    
    public Iterator<Vertex<E>> vertexs() {
        //code to get iterator to all vertices here
    }
}
