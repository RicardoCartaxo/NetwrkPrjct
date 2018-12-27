import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Stone extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

    private static ImageIcon icon = new ImageIcon("C:/Users/user/Desktop/stone.png");
    private static Image image = icon.getImage();

    public Stone(int width){
        super(width,image);
    }

    public int getMinWidth(){ return this.MIN_WIDTH; }
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    }

    @Override
    public void draw(Graphics G) {
        G.drawImage(image,position.x,position.y,width,height,null);
    }
}
