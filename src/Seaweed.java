import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Scanner;

public class Seaweed extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

    ImageIcon icon = new ImageIcon("seaweed.png");
    Image image = icon.getImage();

    public Seaweed(int width){
        super(width);
    }

    @Override
    public void draw(Graphics G) {
        G.setColor(Color.green);
        G.drawOval(position.x,position.y,width,height);
        G.drawImage(image,position.x,position.y,null);
    }

    public int getMinWidth(){
        return this.MIN_WIDTH;
    };
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    };
}
