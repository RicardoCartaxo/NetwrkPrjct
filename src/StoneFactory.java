import java.awt.*;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class StoneFactory extends AquariumItemFactory {

    protected RandomNumber rn = new RandomNumber();

    private Stone st = new Stone(50);
    int maxWidth = st.MAX_WIDTH;
    int minWidth = st.MIN_WIDTH;

    int numberOfStones;

    public StoneFactory(int numberOfStones){
        this.numberOfStones = numberOfStones;
        this.run();
    }
    @Override
    public AquariumItem newItem() {
        return sink(this.aq_it,new Stone((int)(Math.random() * ((maxWidth - minWidth) + 1)) + minWidth));
    }

    public Collection<AquariumItem> getItemCollection(){
        return this.aq_it;
    }

    private AquariumItem sink(Collection<AquariumItem> items, AquariumItem instance){
            if(instance.intersects(items)) {
                instance.setPosition(new Point((int) (Math.random() * 350),(int) (Math.random() * 350)));
                sink(items, instance);
            }
        return instance;}

    @Override
    public void run() {
        for(int i=0; i<numberOfStones;i++){
            try{
                this.semafero.acquire();
                this.aq_it.add(newItem());
            }
            catch(Exception e){
                e.printStackTrace();
            }finally{
                this.semafero.release();
            }
        }

    }
}



