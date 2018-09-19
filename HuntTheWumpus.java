import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.net.URLClassLoader;

import java.util.*;

public class HuntTheWumpus{
	
	private Landscape scape;
	private LandscapeDisplay display;
	private Graph graph;
	private Hunter hunter;
	private Wumpus wumpus;
	
	private enum PlayState { PLAY, STOP; }
	private PlayState state = PlayState.PLAY;
	
	public HuntTheWumpus(int scale){
		//initializes landscape display
		this.scape = new Landscape(14,14);
	
		//initialize graph
		this.graph = new Graph();
		
		int height = this.scape.getHeight()/2;
		int width = this.scape.getWidth()/2;
		
		Vertex[][] vertices = new Vertex[height][width];
		
		//builds array of vertices 
		for( int i=0 ; i < height ; i++){
			for( int j=0 ; j < width ; j++ ){
				//number of rows represents height
				//number of columns -> width
				vertices[i][j] = new Vertex(i,j);
			}
		}
		
		//add edges east/west
		for(int j=0 ; j < height ; j++){
			for(int i=0 ; i < width-1 ; i++){
				this.graph.addEdge(vertices[j][i],Vertex.Direction.East,vertices[j][i+1]);
			}
		}
		
		//add edges north/south
		for( int i=0 ; i < height-1 ; i++){
			for( int j=0 ; j < width ; j ++ ){
				this.graph.addEdge( vertices[i][j], Vertex.Direction.South,vertices[i+1][j]);
			}
		}
		
		Random rand = new Random();
		
		int x_1 = rand.nextInt(7);
		int y_1 = rand.nextInt(7);
		int x_2 = rand.nextInt(7);
		int y_2 = rand.nextInt(7);
		
		//creates random location for hunter and wumpus
		this.hunter = new Hunter( vertices[x_1][y_1] );
		this.wumpus = new Wumpus( vertices[x_2][y_2] );
		
		//insert hunter and wumpus into landscape
		this.scape.setHunter(this.hunter);
		this.scape.setWumpus(this.wumpus);	
		
		
		this.scape.addGraph( this.graph );
		
		//add vertices to graph and calculate shortest distance to wunpus to display red when near
		this.graph.shortestPath( this.wumpus.getHome() );
		
		this.display =  new LandscapeDisplay(this.scape, 64);
			
		Control control = new Control();

		//stop button makes game stop
		JButton stop = new JButton("Stop");
		JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
		stop.addActionListener(control);
		panel.add( stop );
		this.display.add( panel, BorderLayout.NORTH);
		this.display.pack();
		
		stop.addActionListener( control );
		
		this.display.addKeyListener(control);
		
		this.display.setFocusable(true);
		this.display.requestFocus();
		

	}//end hunt the wumpus constructor
	
	//returns hunter 
	public Hunter getHunter(){
		return this.hunter;
	}
	
	//returns wumpus
	public Wumpus getWumpus(){
		return this.wumpus;
	}
	
	public LandscapeDisplay getDisplay(){
		return this.display;
	}

	
	private class Control extends KeyAdapter implements ActionListener {
		//allows us to use keys to move hunter and play game
		public void keyTyped(KeyEvent e) {
			System.out.println( "Key Pressed: " + e.getKeyChar() );
			//click space bar, arms or disarms hunter
			if( ("" + e.getKeyChar()).equalsIgnoreCase("o") ) {
				if( getHunter().getState() == 1 ){
					getHunter().setHunterState(0);
					System.out.println("Hunter is armed and ready.");
				}
				else if ( getHunter().getState() == 0 ){
					getHunter().setHunterState(1);
					System.out.println("Hunter is disarmed.");
				}
			}
			//move north or shoot north
			else if ( ("" + e.getKeyChar()).equalsIgnoreCase("w") ){
				//hunter is unarmed
				if( getHunter().getState() == 1){
					System.out.println("Hunter moves North");
					//hunter moves west
					getHunter().move(Vertex.Direction.North);
					getHunter().getLocation().setVisible(true);
					//hunter dies if moves into vertex of wumpus while unarmed
					if( getHunter().getLocation() == getWumpus().getHome() ){
						getHunter().setHunterState(-1);
						getWumpus().getHome().setVisible(true);
						getWumpus().setLifeStatus(1);
						System.out.println("Hunter was killed");
					}
				}
				//hunter is armed
				else if( getHunter().getState() == 0){
					System.out.println("Hunter shoots west");
					Vertex neighbor = getHunter().getLocation().getNeighbor(Vertex.Direction.North);
					while( neighbor != null){
						if( getWumpus().getHome() == neighbor ){
							getWumpus().getHome().setVisible(true);
							getWumpus().setLifeStatus(-1);
							getHunter().setHunterState(2);
							System.out.println("Wumpus dies!");
							break;
						}
						neighbor = neighbor.getNeighbor(Vertex.Direction.North);
					}
					if( neighbor == null ){
						getHunter().setHunterState(-2);
						System.out.println("Hunter shoots and misses");
					}
				}
			}
			else if ( ("" + e.getKeyChar()).equalsIgnoreCase("a") ){
				if( getHunter().getState() == 1){
					System.out.println("Hunter moves West");
					getHunter().move(Vertex.Direction.West);
					getHunter().getLocation().setVisible(true);
					//hunter dies if moves into vertex of wumpus
					if( getHunter().getLocation() == getWumpus().getHome() ){
						getHunter().setHunterState(-1);
						getWumpus().getHome().setVisible(true);
						getWumpus().setLifeStatus(1);
						System.out.println("Hunter was killed");
					}
				}
				//hunter is armed
				else if( getHunter().getState() == 0){
					System.out.println("Hunter shoots");
					Vertex neighbor = getHunter().getLocation().getNeighbor(Vertex.Direction.West);
					while( neighbor != null){
						if( getWumpus().getHome() == neighbor ){
							getWumpus().getHome().setVisible(true);
							getWumpus().setLifeStatus(-1);
							getHunter().setHunterState(2);
							System.out.println("Wumpus dies!");
							break;
						}
						neighbor = neighbor.getNeighbor(Vertex.Direction.West);
					}
					if( neighbor == null ){
						getHunter().setHunterState(-2);
						System.out.println("Hunter shoots and misses");
					}
				}	
			}
			else if ( ("" + e.getKeyChar()).equalsIgnoreCase("s") ){
				if( getHunter().getState() == 1){
					System.out.println("Hunter moves South");
					getHunter().move(Vertex.Direction.South);
					getHunter().getLocation().setVisible(true);
					//hunter dies if moves into vertex of wumpus
					if( getHunter().getLocation() == getWumpus().getHome() ){
						getHunter().setHunterState(-1);
						getWumpus().getHome().setVisible(true);
						getWumpus().setLifeStatus(1);
						System.out.println("Hunter was killed");
					}
				}
				//hunter is armed
				else if( getHunter().getState() == 0){
					System.out.println("Hunter shoots");
					Vertex neighbor = getHunter().getLocation().getNeighbor(Vertex.Direction.South);
					while( neighbor != null){
						if( getWumpus().getHome() == neighbor ){
							getWumpus().getHome().setVisible(true);
							getWumpus().setLifeStatus(-1);
							getHunter().setHunterState(2);
							System.out.println("Wumpus dies!");
							break;
						}
						neighbor = neighbor.getNeighbor(Vertex.Direction.South);
					}
					if( neighbor == null ){
						getHunter().setHunterState(-2);
						System.out.println("Hunter shoots and misses");
					}
				}	
			}
			else if ( ("" + e.getKeyChar()).equalsIgnoreCase("d") ){
				if( getHunter().getState() == 1){
					System.out.println("Hunter moves East");
					getHunter().move(Vertex.Direction.East);
					getHunter().getLocation().setVisible(true);
					//hunter dies if moves into vertex of wumpus
					if( getHunter().getLocation() == getWumpus().getHome() ){
						getHunter().setHunterState(-1);
						getWumpus().getHome().setVisible(true);
						getWumpus().setLifeStatus(1);
						System.out.println("Hunter was killed");
					}
				}
				//hunter is armed
				else if( getHunter().getState() == 0){
					System.out.println("Hunter shoots");
					Vertex neighbor = getHunter().getLocation().getNeighbor(Vertex.Direction.East);
					while( neighbor != null){
						if( getWumpus().getHome() == neighbor ){
							getWumpus().getHome().setVisible(true);
							getWumpus().setLifeStatus(-1);
							getHunter().setHunterState(2);
							System.out.println("Wumpus dies!");
							break;
						}
						neighbor = neighbor.getNeighbor(Vertex.Direction.East);
					}
					if( neighbor == null ){
						getHunter().setHunterState(-2);
						System.out.println("Hunter shoots and misses");
					}
				}
			}
		}//end keyTyped class
       //game stops when stop button is pressed
        public void actionPerformed(ActionEvent event) {
            if( event.getActionCommand().equalsIgnoreCase("Stop") ) {
				System.out.println("Game has been stopped.");
				state = PlayState.STOP;
			}
        }
	}//end control class
	

	
	public static void main( String[] args ) throws InterruptedException{
		System.out.println("Used w to move north, a to move west, s to move south and d to move east. Use o to arm and disarm hunter.");
		HuntTheWumpus game = new HuntTheWumpus(64);
		
		while(game.state == PlayState.PLAY) {
 			
 			game.getDisplay().repaint();
			// game.getDisplay().update();
			Thread.sleep(33);
		}
		game.getDisplay().dispose();
	}
	
	
}//end hunt the wumpus class
	