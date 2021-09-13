
/**
 * Set up the game frame 
 *
 * @Xiao Gao
 * @June 10 2021
 */

import javax.swing.*;
import java.awt.*;

public class Frame
{
    public static void main (String[] args){
        
        // creat a frame
        JFrame frame = new JFrame();
        
        // set title
        frame.setTitle ("Snake");
        
        // set dimension and set to center location 
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setBounds ((width - 600) / 2, (height - 700) / 2, 600, 700);
        
        // the window's size is inmutable
        frame.setResizable (false);
        
        // exit the program when the user close the window 
        frame.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
        
        // create the panel 
        GamePanel gp = new GamePanel();
        // enable the window 
        frame.setVisible (true);
        frame.add (gp);
        
       
    }


   
}
