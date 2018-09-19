 /**
 * File: LandscapeDisplay.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Image;


/**
 * Displays a Landscape graphically using Swing.  The Landscape
 * can be displayed at any scale factor.
 * @author bseastwo
 */
public class LandscapeDisplay extends JFrame
{
    protected Landscape scape;
    private LandscapePanel canvas;
    private int gridScale; // width (and height) of each square in the grid
    public JFrame f = new JFrame();
   public JButton b = new JButton();

    /**
     * Initializes a display window for a Landscape.
     * @param scape the Landscape to display
     * @param scale controls the relative size of the display
     */
    public LandscapeDisplay(Landscape scape, int scale)
    {
        // setup the window
        super("Hunt the Wumpus");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.scape = scape;
        this.gridScale = scale;
        
        

        // create a panel in which to display the Landscape
        this.canvas = new LandscapePanel( (int) this.scape.getWidth() * this.gridScale,
                                        (int) this.scape.getHeight() * this.gridScale);

        // add the panel to the window, layout, and display
        this.add(this.canvas, BorderLayout.CENTER);
        //inserts game image: hunt the wumpus logo
        this.pack();
        this.setVisible(true);
      	f.setSize(400, 400);
      	f.setLocation((200), (200));

	  ImageIcon pic = new ImageIcon("MyPic.png");
	  Image icon = pic.getImage();
	  Image real = icon.getScaledInstance(400, 200, java.awt.Image.SCALE_SMOOTH);
	  //For the 50's, put in the actual dimensions you want the picture to be
	  pic = new ImageIcon(real);

	  b.setIcon(pic);

	  f.add(b);
	  f.setVisible(true);
	  f.setEnabled(false);
    }

    /**
     * Saves an image of the display contents to a file.  The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename  the name of the file to save
     */
    public void saveImage(String filename)
    {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), 
                                                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try
                {
                        ImageIO.write(image, ext, new File(filename));
                }
        catch (IOException ioe)
                {
                        System.out.println(ioe.getMessage());
                }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel
    {
        /**
         * Creates the panel.
         * @param width     the width of the panel in pixels
         * @param height        the height of the panel in pixels
         */
        public LandscapePanel(int width, int height)
        {
                super();
                this.setPreferredSize(new Dimension(width, height));
                this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g     the Graphics object used for drawing
         */
        public void paintComponent(Graphics g){

            super.paintComponent(g);
            scape.draw( g );    
        } // end paintComponent
        
    } // end LandscapePanel

    public void update( ) {
        Graphics g = canvas.getGraphics();
        this.requestFocus();
        canvas.paintComponent( g );
    }


    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(100,100);
//         Checkout cH = new Checkout(50,350);
//         Checkout cH2 = new Checkout(70,350);
//    		scape.addQueue( cH );
//    		scape.addQueue( cH2 );
//    		
//         LandscapeDisplay display = new LandscapeDisplay(scape, 4);
// 
//         display.repaint();
	}
}