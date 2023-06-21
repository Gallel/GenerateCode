
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

    public DirectedGraphImpl() {
        //constructor code here
    }

    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        //code to get adjacent vertices here
        return null;
    }

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
        //code to create a new vertex here
        return null;
    }

    @Override
    public void deleteEdge(Edge<L,E> aresta) {
        //code to delete an edge here
    }

    @Override
    public void deleteVertex(Vertex<E> vertex) {
        //code to delete a vertex and related edges here
    }

    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
        //code to get edges with destination at given vertex here
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edges() {
        //code to get all edges here
        return null;
    }

    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
        //code to get edges with source at given vertex here
        return null;
    }

    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
        //code to get edge between two vertices here
        return null;
    }

    @Override
    public Vertex<E> getVertex(E elem) {
        //code to get vertex with given value here
        return null;
    }

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
        //code to create a new dictionary of vertices here
        return null;
    }

    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {
        //code to create a new edge between two vertices here
        return null;
    }

    @Override
    public Vertex<E> newVertex(E valor) {
        //code to create a new vertex with given value here
        return null;
    }

    @Override
    public int numVertexs() {
        //code to get number of vertices in the graph here
        return 0;
    }

    @Override
    public String toString() {
        //code to return string representation of the graph here
        return null;
    }

    @Override
    public Iterator<Vertex<E>> vertexs() {
        //code to get all vertices in the graph here
        return null;
    }
}
