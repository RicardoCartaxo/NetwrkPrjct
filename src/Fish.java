import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Fish extends MobileItem {

    private static File file = new File("C:/Users/user/Desktop/fish.png");
    private static Image image;

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

    public Fish(int width){
        super(width);
        if(image == null) {
            try {
                image = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("fish");
            }
        }
    }

    @Override
    public void draw(Graphics G) {
        G.drawImage(image,position.x,position.y,width,height,null);
    }
}
