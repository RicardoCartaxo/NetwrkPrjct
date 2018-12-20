import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class  SeaweedFactory extends AquariumItemFactory{

    protected RandomNumber rn = new RandomNumber();

    private Seaweed sw = new Seaweed(50);
    int maxWidth = sw.MAX_WIDTH;
    int minWidth = sw.MIN_WIDTH;
    int numberOfSeaweed;

    public SeaweedFactory(int numberOfSeaweed) {
        this.numberOfSeaweed = numberOfSeaweed;
        this.run();
    }

    @Override
    public AquariumItem newItem() {
        return sink(this.aq_it, new Seaweed((int) (Math.random() * 100)));
    }

    public Collection<AquariumItem> getItemCollection() {
        return this.aq_it;
    }

    private AquariumItem sink(Collection<AquariumItem> items, AquariumItem instance) {
            if (instance.intersects(items)) {
                instance.setPosition(new Point((int) (Math.random() * 300), (int) (Math.random() * 300)));
                sink(items,instance);
            }
        return instance;
    }


    @Override
    public void run() {
        for (int i = 0; i < numberOfSeaweed; i++) {
            this.aq_it.add(this.newItem());
        }
    }
}
