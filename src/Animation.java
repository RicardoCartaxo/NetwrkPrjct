import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Animation extends JFrame {

    public Animation(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.add(new Aquarium());
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
}
