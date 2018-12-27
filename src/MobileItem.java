import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

public abstract class MobileItem extends AquariumItem implements Mobile {

    private int speed;

    private boolean reachedDestination;
    private Point destination;
    int run;
    int startX, endX, startY, endY;



    private int counter = 0;

    public MobileItem(int width, Image image) {
        super(width,image);
        speed = 3;
        reachedDestination = false;
        run = 0;
        this.startX=-50;
        this.startY=-50;
        this.endX=400;
        this.endY=400;
    }

    @Override
    public boolean move(Point destination) {
        if(run==0) {
            this.destination = destination;
            run++;
        }
        if(reachedDestination){
            this.destination = destination;
            reachedDestination = false;
        }
        if(!reachedDestination) {
            if (this.position.x < this.destination.x) {
                if ((this.destination.x - this.position.x) >= speed)
                    this.position.x = this.position.x + speed;
                else {
                    this.position.x = this.position.x + (this.destination.x - this.position.x);
                }
            }
            if (this.position.x > this.destination.x) {
                if ((this.position.x - this.destination.x) >= speed) {
                    this.position.x = this.position.x - speed;
                } else {
                    this.position.x = this.position.x - (this.position.x - this.destination.x);
                }
            }
            if (this.position.y < this.destination.y) {
                if (this.destination.y - this.position.y >= speed) {
                    this.position.y = this.position.y + speed;
                } else {
                    this.position.y = this.position.y + (this.destination.y - this.position.y);
                }
            }
            if (this.position.y > this.destination.y) {
                if ((this.position.y - this.destination.y) >= speed) {
                    this.position.y = this.position.y - speed;
                } else {
                    this.position.y = this.position.y - (this.position.y - this.destination.y);
                }
            }
            if(this.position.x == this.destination.x && this.position.y == this.destination.y){
                reachedDestination = true;
            }
        }

        return false;
    }

    private void getStaticObj(Collection<AquariumItem> col){
        for (AquariumItem elem : col) {
            if(!(elem instanceof MobileItem)&&!(this.exclX.contains(elem.position.x))){
                this.exclX.add(elem.position.x);
            }
            if(!(elem instanceof MobileItem)&&!(this.exclY.contains(elem.position.y))){
                this.exclY.add(elem.position.y);
            }
        }
        counter++;

    }

    @Override
    public Point target(Collection<AquariumItem> neighbours) {
        if(counter==0) {
            getStaticObj(neighbours);
        }
        return new Point( (generateRandom(startX,endX,exclX)), (generateRandom(startY,endY,exclY)));
    }

    public boolean setStartX(int startX){
        this.startX=startX;
        return true;
    }
    public boolean setEndX(int endX){
        this.endX=endX;
        return true;
    }
    public boolean setStartY(int startY){
        this.startY=startY;
        return true;
    }
    public boolean setEndY(int endY){
        this.endY=endY;
        return true;
    }


    private int generateRandom(int start, int end, ArrayList<Integer> excludeRows) {
        Random rand = new Random();
        int range = end - start + 1;

        int random = rand.nextInt(range) + 1;
        while(excludeRows.contains(random)) {
            random = rand.nextInt(range) + 1;
        }
        return random;
    }

    @Override
    public int getMinWidth() {
        return 0;
    }

    @Override
    public int getMaxWidth() {
        return 0;
    }
}
