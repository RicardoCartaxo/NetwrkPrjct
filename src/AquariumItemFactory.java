import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public abstract class AquariumItemFactory< T extends AquariumItem> implements Runnable{

    protected Collection<AquariumItem> aq_it = Collections.synchronizedList(new ArrayList<>());
    protected static Semaphore semafero = new Semaphore(1);

    public abstract T newItem();
    public abstract Collection<AquariumItem> getItemCollection();

    public void addItems(Collection<AquariumItem> items){
        this.aq_it.addAll(items);
    }

}
