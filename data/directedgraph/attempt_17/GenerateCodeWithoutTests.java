
public class DirectedGraphImpl<E,L> extends Object implements DirectedGraph<E,L> {

  public DirectedGraphImpl() {}

  @Override
  Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {}

  protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value) {}

  @Override
  void deleteEdge(Edge<L,E> aresta) {}

  @Override
  void deleteVertex(Vertex<E> vertex) {}

  @Override
  Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex) {}

  @Override
  Iterator<Edge<L,E>> edges() {}

  @Override
  Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex) {}

  @Override
  DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest) {}

  @Override
  Vertex<E> getVertex(E elem) {}

  protected Dictionary<E,Vertex<E>> newDictionaryVertexs() {}

  @Override
  DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest) {}

  @Override
  Vertex<E> newVertex(E valor) {}

  @Override
  int numVertexs() {}

  @Override
  String toString() {}

  @Override
  Iterator<Vertex<E>> vertexs() {}

}
