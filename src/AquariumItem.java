import java.awt.*;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AquariumItem {

    //The 3 protected attributes
    protected Point position;
    protected int width;
    protected int height;

    public AquariumItem(int width){
        this.width = width;
        this.height = (width/2);
        this.position = new Point(ThreadLocalRandom.current().nextInt(0, 350),ThreadLocalRandom.current().nextInt(0, 350));

    }

    // The 4 methods
    public void setPosition(Point p){
        this.position = p;
    }

    public Rectangle rectangle(){
        return new Rectangle(this.position.x, this.position.y, this.width,this.height);
    }

    public abstract void draw(Graphics G);

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

}
