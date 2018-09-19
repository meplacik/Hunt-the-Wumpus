 /**
 * File: Agent.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;


public class Agent {
	
	//fields
	private int xPos;
	private int yPos;
	
	//constructor that sets position
	public Agent( int x0 , int y0 ){
		this.xPos = x0;
		this.yPos = y0;
	}
	
	//returns x position
	public int getX(){
		return this.xPos;
	}
	
	//return y position
	public int getY(){
		return this.yPos;
	}
	
	//sets x position
	public void setX( int newX ){
		this.xPos = newX;
	}
	
	//sets y position
	public void setY( int newY ){
		this.yPos = newY;
	}
	
	//returns a String containing the x and y positions
	public String toString(){
		return "(" + this.xPos + ", " + this.yPos + ")";
	}
	
	//does nothing
	public void updateState( ){
	}
	
	//does nothing
	public void draw( Graphics g ){
		
	}
	
	public static void main( String[] args ){
		// Agent aG = new Agent(3.2,4.5);
// 		System.out.println( aG.toString() );
	}

}

