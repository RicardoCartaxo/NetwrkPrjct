import java.awt.*;
import java.nio.Buffer;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AquariumItem {

    //The 3 protected attributes
    protected Point position;
    protected int width;
    protected int height;

    protected Image image;

    public AquariumItem(int width, Image image){
        this.width = 50;
        this.height = 50;
        this.image = image;
        this.position = new Point(ThreadLocalRandom.current().nextInt(0, 350),ThreadLocalRandom.current().nextInt(0, 350));

    }

    // The 4 methods
    public void setPosition(Point p){
        this.position = p;
    }

    public Rectangle rectangle(){
        return new Rectangle(this.position.x, this.position.y, this.width,this.height);
    }

    public void draw(Graphics G){
        G.drawImage(image,position.x,position.y,width,height,null);
    }

    public boolean intersects(Collection<AquariumItem> c){
        for (AquariumItem elem : c) {
            if(elem.rectangle().intersects(this.rectangle())){
                return true;
            }
        }
        return false;
    }

    public abstract int getMinWidth();
    public abstract int getMaxWidth();

    //Extra method to set width according to min and max final int
    protected void setWidth(int x){
        this.width = x;
    }

    public Point getPosition(){
        return this.position;
    }

}
