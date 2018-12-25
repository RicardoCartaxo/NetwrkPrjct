import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Scanner;

public class Stone extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

    public Stone(int width){
        super(width);
    }

    public int getMinWidth(){ return this.MIN_WIDTH; }
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    }

    @Override
    public void draw(Graphics G) {
        G.setColor(Color.black);
        G.drawOval(position.x,position.y,width,height);
    }
}
