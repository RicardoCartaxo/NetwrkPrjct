import java.awt.*;
import java.util.Collection;

public class  FishFactory extends AquariumItemFactory{

    private Fish fsh = new Fish(1);
    int maxWidth = fsh.MAX_WIDTH;
    int minWidth = fsh.MIN_WIDTH;
    int numberOfFish;

    public FishFactory(int numberOfSeaweed) {
        this.numberOfFish = numberOfSeaweed;
        this.run();
    }

    @Override
    public AquariumItem newItem() {
        return sink(this.aq_it, new Fish((int)(Math.random() * ((maxWidth - minWidth) + 1)) + minWidth));
    }

    public Collection<AquariumItem> getItemCollection() {
        return this.aq_it;
    }

    private AquariumItem sink(Collection<AquariumItem> items, AquariumItem instance) {
        if (instance.intersects(items)) {
            instance.setPosition(new Point((int) (Math.random() * 350), (int) (Math.random() * 350)));
            sink(items,instance);
        }
        return instance;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfFish; i++) {
            try {
                this.semafero.acquire();
                this.aq_it.add(this.newItem());
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                this.semafero.release();
            }
        }

    }
}
