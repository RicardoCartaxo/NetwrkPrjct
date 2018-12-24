import java.awt.*;
import java.util.Scanner;

public class Seaweed extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 50;

    public Seaweed(int width){
        super(width);
    }
    @Override
    public void draw(Graphics G) {
        G.setColor(Color.green);
        G.drawOval(this.position.x,this.position.y,this.width,this.height);
    }

    public int getMinWidth(){
        return this.MIN_WIDTH;
    };
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    };
}
