import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Seaweed extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

   // private static File file = new File("C:/Users/user/Desktop/seaweed.png");
    //private static Image image;

   private static ImageIcon icon = new ImageIcon("C:/Users/user/Desktop/seaweed.png");
   private static Image image = icon.getImage();


    public Seaweed(int width){
        super(width,image);
    }


    public int getMinWidth(){
        return this.MIN_WIDTH;
    };
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    };
}
