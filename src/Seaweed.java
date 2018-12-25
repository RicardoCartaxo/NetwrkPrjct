import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Seaweed extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

    private static File file = new File("C:/Users/user/Desktop/seaweed.png");
    private static Image image;


    public Seaweed(int width){
        super(width);
        if(image == null) {
            try {
                image = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("seaweed");
            }
        }
    }

    @Override
    public void draw(Graphics G) {
        G.drawImage(image,position.x,position.y,width,height,null);
    }

    public int getMinWidth(){
        return this.MIN_WIDTH;
    };
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    };
}
