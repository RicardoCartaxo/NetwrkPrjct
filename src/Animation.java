import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Animation extends JFrame{

    private Aquarium aquarium;
    Thread t1;

    public Animation(String title) throws HeadlessException {
        super(title);
        aquarium = new Aquarium();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.add(aquarium);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int input = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?");
                if(input==0){
                    System.exit(0);
                }
            }
        });
        this.setVisible(true);
    }

    public Aquarium getAquarium(){
        return this.aquarium;
    }
}
