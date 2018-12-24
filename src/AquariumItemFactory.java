import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public abstract class AquariumItemFactory< T extends AquariumItem> implements Runnable{

    protected static Collection<AquariumItem> aq_it = Collections.synchronizedList(new ArrayList<AquariumItem>());
    protected static Semaphore semafero = new Semaphore(1);

    public abstract T newItem();
    public abstract Collection<AquariumItem> getItemCollection();

}
