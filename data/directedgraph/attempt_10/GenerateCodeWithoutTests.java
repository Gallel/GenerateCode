
public class DirectedGraphImpl<E,L>
extends Object
implements DirectedGraph<E,L>{
    
    public DirectedGraphImpl(){ }
    
    @Override
    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex){
        // body of method
    }
    
    @Override
    protected edu.uoc.ds.adt.nonlinear.graphs.VertexImpl<E,L> createVertex(E value){
        // body of method
    }
    
    @Override
    public void deleteEdge(Edge<L,E> edge){
        // body of method
    }
    
    @Override
    public void deleteVertex(Vertex<E> vertex){
        // body of method
    }
    
    @Override
    public Iterator<Edge<L,E>> edgedWithDestA(Vertex<E> vertex){
        // body of method
    }
    
    @Override
    public Iterator<Edge<L,E>> edges(){
        // body of method
    }
    
    @Override
    public Iterator<Edge<L,E>> edgesWithSource(Vertex<E> vertex){
        // body of method
    }
    
    @Override
    public DirectedEdge<L,E> getEdge(Vertex<E> src, Vertex<E> dest){
        // body of method
    }
    
    @Override
    public Vertex<E> getVertex(E elem){
        // body of method
    }
    
    @Override
    protected Dictionary<E,Vertex<E>> newDictionaryVertexs(){
        // body of method
    }
    
    @Override
    public DirectedEdge<L,E> newEdge(Vertex<E> src, Vertex<E> dest){
        // body of method
    }
    
    @Override
    public Vertex<E> newVertex(E valor){
        // body of method
    }
    
    @Override
    public int numVertexs(){
        // body of method
    }
    
    @Override
    public String toString(){
        // body of method
    }
    
    @Override
    public Iterator<Vertex<E>> vertexs(){
        // body of method
    }
}
