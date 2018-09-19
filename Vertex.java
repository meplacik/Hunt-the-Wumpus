import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Graphics;
import java.awt.Color;

public class Vertex extends Agent implements Comparable<Vertex>{
	
	//map of edges
	//each entry in map has direction as key and vertex as value
	private HashMap<Direction,Vertex> map;
	//cost of vertex
	private int cost;
	private boolean marked;
	private boolean visible;
	
	public Vertex(int row,int col){
		super(col,row);
		this.cost = 100000000;
		this.marked = false;
		this.map = new HashMap<Direction,Vertex>();
		this.visible = false;
	}
	
	public int getCol(){
		return this.getX();
	}
	
	public int getRow(){
		return this.getY();
	}
	
	public enum Direction{ North,South,East,West,None;}
	
	//public static method
	//returns compas opposite of a direction
	public Direction opposite( Direction d ){
		if ( d == Direction.North ){
			return Direction.South;
		}
		else if ( d == Direction.East ){
			return Direction.West;
		}
		else if ( d == Direction.West ){
			return Direction.East;
		}
		else {
			return Direction.North;
		} 
	}
	
	
	
	public void setVisible(boolean x){
		this.visible = x;
	}
	
	public boolean getVisibility(){
		return this.visible;
	}
	
	public int getCost(){
		return this.cost;
	}
	
	//sets cost of vertex
	public void setCost( int x ){
		this.cost = x;
	}
	
	//returns whether or not vertex is marked
	public boolean getMark(){
		return this.marked;
	}
	
	//returns string value of marked state
	public String stringMarkedState(){
		if( this.marked == false ){
			return "Vertex is unmarked";
		}
		else{
			return "Vertex is marked";
		}
	}
	
	public void setMark( boolean x){
		this.marked = x;
	}
	
	//connects two vertices
	public void connect( Vertex other, Direction dir ){
		this.map.put( dir, other );
	}
	
	//returns neighbor of vertex in given direction
	public Vertex getNeighbor( Direction dir ){
		return this.map.get(dir);
		
	}
	
	//returns array list of all neighbors
	public ArrayList<Vertex> getNeighbors(){
		ArrayList<Vertex> neighbors;
		neighbors = new ArrayList<Vertex>( this.map.values() );
		return neighbors;	
	}
	
	//show possible exits from room
	//indicates whether the wumpus is 2 rooms away or closer
	//connected rooms do not ned to be linked visually
	public void draw( Graphics g , int x0 , int y0 , int scale ){
		//if room is not visible
		if( !this.visible ) {
			return;
		}
		int xpos = x0 + this.getCol()*scale;
		int ypos = y0 + this.getRow()*scale;
		int border = 2;
		int half = scale/2;
		int eighth = scale/8;
		int sixteenth = scale /16;
		
		//draw rectabgle for walls of cave
		if (this.cost <= 2){
			//wumpus is near
			g.setColor(Color.red);
		}
		else{
			//wumpus is not near
			g.setColor(Color.black);
		}
		g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
		
		//draw doorways as boxes
		g.setColor(Color.black);
		if (this.map.containsKey(Direction.North)){
			g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
		}
		if (this.map.containsKey(Direction.South)){
			g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
				  eighth, eighth + sixteenth);
		}
		if (this.map.containsKey(Direction.West)){
			g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
		}
		if (this.map.containsKey(Direction.East)){
			g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
				  eighth + sixteenth, eighth);
		}
	}
	
	public String toString(){
		return "Cost: " + this.getCost() + ", Marked state: " + 
			this.stringMarkedState() + ", Neighbors: " + this.getNeighbors().size() + 
			", Position: (" + super.getX() + ", " + super.getY() + ")\n" ;
	}
	
	public static void main( String[] args ){
		Vertex vert = new Vertex(2,3);
		System.out.print(vert);
	}
	
	public int compareTo(Vertex v){
		return 0;
	}

}