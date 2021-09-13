/**
 * All the image resource that I need to build the game 
 *
 * @Xiao Gao
 * @June 10 2021
 */

import java.net.URL;
import javax.swing.*;

public class Data{

    public static URL bodyURL = Data.class.getResource ("src/body.png");
    public static URL foodURL = Data.class.getResource ("src/food.png");
    public static URL upURL = Data.class.getResource ("src/up.png");
    public static URL downURL = Data.class.getResource ("src/down.png");
    public static URL leftURL = Data.class.getResource ("src/left.png");
    public static URL rightURL = Data.class.getResource ("src/right.png");

    public static ImageIcon bodyImg = new ImageIcon (bodyURL);
    public static ImageIcon foodImg = new ImageIcon (foodURL);
    public static ImageIcon upImg = new ImageIcon (upURL);
    public static ImageIcon downImg = new ImageIcon (downURL);
    public static ImageIcon leftImg = new ImageIcon (leftURL);
    public static ImageIcon rightImg = new ImageIcon (rightURL);

}