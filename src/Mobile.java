import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public interface Mobile {

    static ArrayList<Integer> exclX= new ArrayList<>();
    static ArrayList<Integer> exclY = new ArrayList<>();

    //Used for moving, setting the Point
    boolean move(Point destination);

    //Used to search for/decide destination
    Point target(Collection<AquariumItem> neighbours);
}
