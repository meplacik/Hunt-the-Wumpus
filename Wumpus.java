import java.awt.Graphics;
import java.awt.Color;

public class Wumpus extends Agent{

	private Vertex home;
	//true if wumpus is alive false if wumpus is dead
	private int lifeStatus;
	
	public Wumpus(Vertex vertex){
		super(0,0);
		this.home = vertex;
		//hunter is always visible
		this.home.setVisible(false); 
		this.lifeStatus = 0;
	}
	

	public Vertex getHome(){
		return this.home;
	}
	
	public int getLifeStatus(){
		return this.lifeStatus;
	}
	
	//-1 is dead, 0 is living, 1 is winning
	public void setLifeStatus(int x){
		this.lifeStatus = x;
	}
	
	public void setVisible(boolean x){
		this.home.setVisible(x);
	}
	
	public boolean getVisibility(){
		return this.home.getVisibility();
	}
	
	public void draw( Graphics g , int x0 , int y0 , int scale ){
		//if room is not visible

		int xpos = x0 + this.home.getCol()*scale;
		int ypos = y0 + this.home.getRow()*scale;
		int border = 2;
		int half = scale/2;
		int eigth = scale/8;
		int sixteenth = scale /16;
		
		//wumpus is red if dead
		if (this.getVisibility() == true && this.getLifeStatus() == -1){
			//wumpus is near
			g.setColor(Color.red);
			g.fillRect(xpos + sixteenth*2, ypos + sixteenth*2, scale - 10*border, scale - 10 * border);
			g.setColor(Color.black);
			g.drawString("Wumpus dies!",25,50);
		}
		
		//wumpus is green if they ate hunter		
		if( this.getVisibility() == true && this.getLifeStatus() == 1) {
			g.setColor(Color.green);
			g.fillRect(xpos + sixteenth*2, ypos + sixteenth*2, scale - 10*border, scale - 10 * border);
			g.setColor(Color.black);
			g.drawString("Wumpus wins!",25,50);
		}
		
		if( this.getVisibility() == false ) {
			return;
		}

		
	}
	
	
	

}