 /**
 * File: Landscape.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */

import java.awt.Graphics;
import java.util.Collections;
import java.util.Random;
import java.awt.Font;
import java.lang.Math;
import java.util.LinkedList;


public class Landscape {

	//fields
	private int width;
	private int height;
	//hunter and wumpus
	private Hunter hunter;
	private Wumpus wumpus;
	//vertices
	private Graph graph;
	
	//constructor that sets the width and height fields
	//initializes lists of checkout objects and customer objects
	public Landscape( int w , int h ){
		this.width = w;
		this.height = h;
		// this.foreground = new LinkedList<Agent>();
		this.wumpus =null;
		this.hunter = null; 
		this.graph = null;
		
	}
	
	//returns the height
	public int getHeight(){
		return this.height;
	}
	
	//returns the width
	public int getWidth(){
		return this.width;
	}
	
	public void setHunter(Hunter v){
		this.hunter = v;
	}
	
	public void setWumpus( Wumpus v){
		this.wumpus = v;
	}
	
// 	public void addWumpus( Wumpus w){
// 		this.foreground.add(w);
// 	}
	
	public void addGraph(Graph g){
		this.graph = g;
	}

	//draws foreground and background agents
	public void draw ( Graphics g){
// 		for ( Vertex b: this.background ){
// 			b.draw(g,10,10,100);
// 		}
		for( int i=0 ; i<this.graph.vertexCount() ; i++){
			this.graph.vertexList().get(i).draw(g,10,10,100);
		}
		//draws hunter and wumpus into landscape
		this.hunter.draw(g,10,10,100);
		this.wumpus.draw(g,10,10,100);

	}

	//main method to test class methods
	public static void main( String[] args ){
		Landscape scape = new Landscape(100,100);

	}

}