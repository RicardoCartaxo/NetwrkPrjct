import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Aquarium extends JPanel {

    final int NB_STONES = 13;
    final int NB_SEAWEED = 15;

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
        stF.run();
        swF = new SeaweedFactory(NB_SEAWEED);
    }

}
