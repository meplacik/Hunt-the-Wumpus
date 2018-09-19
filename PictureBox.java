import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Image;


public class PictureBox
{

   public JFrame f = new JFrame();
   public JButton b = new JButton();

   public PictureBox()
   {

      f.setUndecorated(true);
      f.setSize(50, 50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocation((200), (200));


      ImageIcon pic = new ImageIcon("MyPic.png");
      Image icon = pic.getImage();
      Image real = icon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      //For the 50's, put in the actual dimensions you want the picture to be
      pic = new ImageIcon(real);

      b.setIcon(pic);

      f.add(b);
      f.setVisible(true);
      f.setEnabled(false);

   }

   public static void main(String[] args)
   {

      PictureBox pb = new PictureBox();

   }

}