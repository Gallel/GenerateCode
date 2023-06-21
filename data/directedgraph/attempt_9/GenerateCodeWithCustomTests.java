
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L>{
  
  public DirectedGraphImpl() {
  }
  
  @Override
  protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {
    return new edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L>(value);
  }
  
  @Override
  public void deleteEdge(Edge<L,E> edge) {
  }
  
  @Override
  public void deleteVertex(Vertex<E> vertex) {
  }
  
  @Override
  public DirectedEdge<L,E> newEdge(Vertex<E> source, Vertex<E> dest) {
    return new edu.uoc.ds.adt.nonlinear.graphs.DirectedEdgeImpl<L,E>(source, dest);
  }
  
  @Override
  public Vertex<E> newVertex(E value) {
    return new edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L>(value);
  }
  
  @Override
  public int numVertexs() {
    return 0;
  }
  
  @Override
  public String toString() {
    return null;
  }
  
  @Override
  public Iterator<Vertex<E>> vertexs() {
    return null;
  }

  @Override
  public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
    return null;
  }

  @Override
  public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {
    return null;
  }

  @Override
  public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {
    return null;
  }

  @Override
  public Iterator<Edge<L,E>> edges() {
    return null;
  }

  @Override
  public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {
    return null;
  }

  @Override
  public Vertex<E> getVertex(E elem) {
    return null;
  }

  @Override
  protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {
    return null;
  }
}
