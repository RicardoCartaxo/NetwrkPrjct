import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Aquarium extends JPanel {

    // Going over around 25 sometimes causes a StackOverflowException (due to there not being any more space on the canvas)
    final int NB_STONES = 8;
    final int NB_SEAWEED = 6;
    final int NB_FISH = 5;

    private StoneFactory stF;
    private SeaweedFactory swF;
    private FishFactory fshF;

    protected Collection<AquariumItem> items = new ArrayList<>();

    protected Time time = new Time(this);


    public Aquarium() {
        this.setBackground(Color.CYAN);
        this.fill();
        this.setVisible(true);
        //time.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (AquariumItem aq_it: items){
           aq_it.draw(g);
       }
    }
    public void fill(){
        stF = new StoneFactory(NB_STONES);
        swF = new SeaweedFactory(NB_SEAWEED);
        fshF = new FishFactory(NB_FISH);
        items.addAll(stF.getItemCollection());

    }

    public void go(){

    }

}
