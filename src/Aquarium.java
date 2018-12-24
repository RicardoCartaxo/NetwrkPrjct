import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Aquarium extends JPanel {

    // Going over around 25 sometimes causes a StackOverflowException (due to there not being any more space on the canvas)
    final int NB_STONES = 14;
    final int NB_SEAWEED = 12;

    private StoneFactory stF;
    private SeaweedFactory swF;

    protected Collection<AquariumItem> items = new ArrayList<AquariumItem>();

    public Aquarium() {
        this.setVisible(true);
        this.setBackground(Color.CYAN);
        this.fill();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (AquariumItem aq_it: items){
            aq_it.draw(g);
        }

    }
    public void fill(){
        stF = new StoneFactory(NB_STONES);
        swF = new SeaweedFactory(NB_SEAWEED);
        items.addAll(stF.getItemCollection());
    }

}
