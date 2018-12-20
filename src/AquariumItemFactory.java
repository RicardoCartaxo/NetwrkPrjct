import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AquariumItemFactory< T extends AquariumItem> implements Runnable{

    protected static Collection<AquariumItem> aq_it = new ArrayList<AquariumItem>();

    public abstract T newItem();
    public abstract Collection<AquariumItem> getItemCollection();

}
