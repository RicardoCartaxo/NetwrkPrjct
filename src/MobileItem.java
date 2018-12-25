import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

public abstract class MobileItem extends AquariumItem implements Mobile {

    private float speed;
    private static RandomNumber rn = new RandomNumber();


    public MobileItem(int width) {
        super(width);
        speed = Math.round((1 / width) * 10);
    }

    @Override
    public boolean move(Point destination) {
        while(this.position.x < destination.x){
            if((destination.x - this.position.x) >= 3)
                this.position.x = this.position.x + 3;
            else{
                this.position.x = this.position.x + (destination.x - this.position.x);
            }
        }
        while(this.position.x > destination.x){
            if((this.position.x - destination.x) >= 3){
                this.position.x = this.position.x - 3;
            }else{
                this.position.x = this.position.x - (this.position.x - destination.x);
            }
        }
        while(this.position.y < destination.y){
            if(destination.y - this.position.y >= 3){
                this.position.y = this.position.y + 3;
            }else{
                this.position.y = this.position.y + (destination.y - this.position.y);
            }
        }
        while(this.position.y > destination.y){
            if((this.position.y - destination.y) >= 3) {
                this.position.y = this.position.y - 3;
            }else{
                this.position.y = this.position.y - (this.position.y - destination.y);
            }
        }
        return false;
    }

    @Override
    public Point target(Collection<AquariumItem> neighbours) {
        for (AquariumItem elem : neighbours) {
            if(!(this.exclX.contains(elem.position.x))){
                this.exclX.add(elem.position.x);
            }
            if(!(this.exclY.contains(elem.position.y))){
                this.exclY.add(elem.position.y);
            }

        }
        return new Point( (generateRandom(0,350,exclX)), (generateRandom(0,350,exclY)));
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
