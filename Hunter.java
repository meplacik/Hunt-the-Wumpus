import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.net.URLClassLoader;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Image;


public class Hunter extends Agent{
	
	//location of hunter represented as vertex
	private Vertex location;
	//hunter state of being
	private int state;
	
	//constructor
	public Hunter(Vertex vertex){
		super(0,0);
		this.location = vertex;
		//hunter is always visible
		this.location.setVisible(true); 
		//initial state is hunter is not armed
		this.state = 1;
	}
	
	//allows hunter to move to different vertices as long as neighbor is not null
	public void move(Vertex.Direction dir){
		if(this.location.getNeighbor(dir) != null){
			this.location = this.location.getNeighbor(dir);
		}
	}
	
	//sets state of hunter
	//-1 is killed, -2 is shot and missed, 0 is armed, 1 is unarmed, 2 is winning
	public void setHunterState( int x ){
		this.state = x;
	}
	
	//returns vertex of hunter
	public Vertex getLocation(){
		return this.location;
	}
	
	//-1 is killed, -2 is shoot and miss, 0 is armed, 1 is unarmed, 2 is winning
	public int getState(){
		return this.state;
	}	
	
	//draws hunter based on state
	public void draw( Graphics g , int x0 , int y0 , int scale ){
		int xpos = x0 + this.location.getX()*scale;
		int ypos = y0 + this.location.getY()*scale;
		int border = 2;
		int half = scale/2;
		int eighth = scale/8;
		int sixteenth = scale /16;

		//hunter shot and missed
		if( this.getState() == -2 ) {
			g.setColor(Color.black);
			g.drawString("Hunter shot and missed, that's awkward...",25,25);
		}
		
		//hunter killed by wumpus
		if( this.getState() == -1 ) {
			g.setColor(Color.black);
			g.drawString("Hunter was killed by wumpus",25,25);
		}
		
		//hunter is neutral
		if (this.getState() == 1){
			//hunter is not armed nor eaten
			g.setColor(Color.black);
			g.fillOval(xpos+sixteenth*3, ypos+sixteenth*3, scale - 20*border, scale - 20 * border);
			g.drawString("Hunter is unarmed",25,25);
		}
		//hunter is armed
		if(this.getState() == 0){
			//hunter is armed
			g.setColor(Color.red);
			g.fillOval(xpos+sixteenth*3, ypos+sixteenth*3, scale - 20*border, scale - 20 * border);
			g.setColor(Color.black);
			g.drawString("Hunter is armed...",25,25);
		}
		//hunter won
		if(this.getState() == 2){
			//hunter is armed
			g.setColor(Color.green);
			g.fillOval(xpos+sixteenth*3, ypos+sixteenth*3, scale - 20*border, scale - 20 * border);
			g.setColor(Color.black);
			g.drawString("Hunter Wins!",25,25);
		}
		
	}
	
}