
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L>{

    public DirectedGraphImpl(){}

    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value){}

    void deleteEdge(Edge<L,E> aresta){}

    void deleteVertex(Vertex<E> vertex){}

    Iterator<Edge<L,E>> edgesWithDestA(Vertex<E> vertex){}

    Iterator<Edge<L,E>> edges(){}

    Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex){}

    DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest){}

    Vertex<E> getVertex(E elem){}

    protected Dictionary<E,Vertex<E>> newDictionaryVertexs(){}

    DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest){}

    Vertex<E> newVertex(E valor){}

    int numVertexs(){}

    String toString(){}

    Iterator<Vertex<E>> vertexs(){}
}
