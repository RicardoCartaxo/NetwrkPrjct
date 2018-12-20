import java.awt.*;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class StoneFactory extends AquariumItemFactory implements Runnable {

    protected RandomNumber rn = new RandomNumber();

    private Stone st = new Stone(50);
    int maxWidth = st.MAX_WIDTH;
    int minWidth = st.MIN_WIDTH;
    private int numberOfStones;

    public StoneFactory(int numberOfStones){
        this.numberOfStones = numberOfStones;
        this.run();

    }
    @Override
    public AquariumItem newItem() {
        return sink(this.aq_it,new Stone((int) (Math.random() * 100)));
    }

    public Collection<AquariumItem> getItemCollection(){
        return this.aq_it;
    }

    private AquariumItem sink(Collection<AquariumItem> items, AquariumItem instance){
            if(instance.intersects(items)) {
                instance.setPosition(new Point((int) (Math.random() * 300),(int) (Math.random() * 300)));
                sink(items, instance);
            }
        return instance;}

    @Override
    public void run() {
        for(int i=0; i<numberOfStones;i++){
            this.aq_it.add(this.newItem());
        }
    }
}



