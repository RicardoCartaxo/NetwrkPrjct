import javax.swing.*;
import java.awt.*;

public class Fish extends MobileItem {

    ImageIcon icon = new ImageIcon("fish.png");
    Image image = icon.getImage();


    public Fish(int width){
        super(width);
    }

    @Override
    public void draw(Graphics G) {
        G.setColor(Color.blue);
        G.drawOval(this.position.x,this.position.y,this.width,this.height);
        G.drawImage(image,position.x,position.y,null);
    }
}
