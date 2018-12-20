import java.awt.*;
import java.util.Scanner;

public class Stone extends AquariumItem {

    public final int MIN_WIDTH = 30;
    public final int MAX_WIDTH = 100;

    public Stone(int width){
        super(width);
    }
    @Override
    public void draw(Graphics G) {
        G.setColor(Color.gray);
        G.drawOval(this.position.x,this.position.y,this.width,this.height);
    }

    public int getMinWidth(){ return this.MIN_WIDTH; }
    public int getMaxWidth(){
        return this.MAX_WIDTH;
    }
}
