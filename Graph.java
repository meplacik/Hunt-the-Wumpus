import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Iterator;
import java.awt.Color;


//maintains list of vertices
public class Graph {
	
	//field holding linked list of vertices
	private LinkedList<Vertex> vertices;
	
	//constructor
	public Graph(){
		this.vertices = new LinkedList<Vertex>();
	}
	
	//gets number of vertices
	public int vertexCount(){
		return this.vertices.size();
	}
	
	//returns linked list representation of vertices
	public LinkedList<Vertex> vertexList(){
		return this.vertices;
	}
	
	//adds an edge between vertices using opposite method of vertex
	public void addEdge( Vertex v1, Vertex.Direction dir, Vertex v2){
		if( !this.vertices.contains(v1)){
			this.vertices.add(v1);
		}
		if( !this.vertices.contains(v2)){
			this.vertices.add(v2);
		}
		v1.connect( v2, dir );
		v2.connect( v1, v1.opposite(dir) );
	}
	
	//uses algorithm to compute shorts path to a given vertex
	public void shortestPath( Vertex v0 ){
		//heap of vertices ordered by lowest cost
		PQHeap<Vertex> heap = new PQHeap<Vertex>( new VertexComparator() );
		//loops through number of vertices in graph
		for( int i = 0 ; i< this.vertices.size() ; i++){
			heap.add(this.vertices.get(i));
		}
		//sets initial cost to 0
		v0.setCost(0);                                                     
		//adds vertex to heap
		heap.add(v0);
		//while the heap is not empty
		while ( heap.size() != 0 ){
			//removes first vertex (vertex w lowest cost)
			Vertex v = heap.remove();
			//sets as visited
			v.setMark(true);
			//for ech vertex that neighbors the one w lowest cost
			for ( Vertex w: v.getNeighbors()){
				//if the neighbor is not marked and the cost is less
				if( w.getMark() == false && v.getCost()+1 < w.getCost() ){
					//set new cost
					w.setCost(v.getCost()+1);
					//add to heap
					heap.add(w);
				}
			}
		}
	}
	
	public static void main( String[] args ){
		
		Graph graph = new Graph();
		Vertex v0 = new Vertex(0,0);
		Vertex v1 = new Vertex(0,1);
		Vertex v2 = new Vertex(1,0);
		Vertex v3 = new Vertex(1,1);
	
		graph.addEdge( v0,Vertex.Direction.East,v1);
		graph.addEdge( v1,Vertex.Direction.South,v3);
		graph.addEdge( v0,Vertex.Direction.South,v2);
		graph.addEdge( v2,Vertex.Direction.East,v3);
		
		graph.shortestPath(v0);
		
		for(int i = 0 ; i < graph.vertexCount() ;i++){
			System.out.println( graph.vertexList().get(i).toString() );
		}
		
	}
	
	
}
//orders vertices by cost
class VertexComparator implements Comparator<Vertex> {
	public int compare( Vertex v1, Vertex v2 ) {
		// returns negative number if i2 comes after i1 lexicographically
		Integer val1 = v1.getCost();
		Integer val2 = v2.getCost();
		return -( val1.compareTo( val2 ) );
	}
}