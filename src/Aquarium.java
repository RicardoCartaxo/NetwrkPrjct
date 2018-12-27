import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class Aquarium extends JPanel implements ActionListener{

    private InetAddress realServerName;
    private int port = 44500;

    // Going over around 25 sometimes causes a StackOverflowException (due to there not being any more space on the canvas)
    static final int NB_STONES = 12;
    static final int NB_SEAWEED = 10;
    static final int NB_FISH = 4;

    private StoneFactory stF;
    private SeaweedFactory swF;
    private FishFactory fshF;

    protected Collection<AquariumItem> items = new ArrayList<>();
    protected Timer tm = new Timer(10, this);


    private  boolean right;
    private boolean left;

    private String outMessage = "0";
    private String inMessage = "1";


    public Aquarium() {
        this.setBackground(Color.CYAN);
        this.fill();
        this.setVisible(true);
        this.left = false;
        this.right = false;
        try{
            realServerName = InetAddress.getLocalHost();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (AquariumItem aq_it : items) {
            aq_it.draw(g);
        }
        tm.start();
    }

    public void fill() {
        stF = new StoneFactory(NB_STONES);
        swF = new SeaweedFactory(NB_SEAWEED);
        stF.addItems(swF.getItemCollection());
        fshF = new FishFactory(NB_FISH);
        stF.addItems(fshF.getItemCollection());
        items.addAll(stF.getItemCollection());
    }

   public void setBoundaries() {
        for (AquariumItem aq_it : items) {
            if (aq_it instanceof MobileItem) {
                if (left) {
                    ((MobileItem) aq_it).setStartX(0);
                } else if (right)
                    ((MobileItem) aq_it).setEndX(350);
            }
        }
    }

    public void go() {
        for (AquariumItem aq_it : items) {
            if (aq_it instanceof MobileItem) {
                ((MobileItem) aq_it).move(((MobileItem) aq_it).target(items));
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        go();
        repaint();
        run();
    }

    public void run() {
        for (AquariumItem aq_it : items) {
            if (aq_it instanceof MobileItem) {
                if(aq_it.getPosition().x < 0 ) {
                    outMessage = "2: Fish exited left @ " + aq_it.getPosition() + "!";
                }
                if(aq_it.getPosition().x >350 ) {
                    outMessage = "2: Fish exited right @ " + aq_it.getPosition() + "!";
                }
                else{
                    outMessage = "1: Do Nothing";
                }
            }
        }
        try {

            //client datagram socket and packet
            DatagramSocket client = new DatagramSocket();
            DatagramPacket packet = null;

            //input and output streams
            byte[] buffer = new byte[256];

            System.out.println(outMessage.charAt(0));

            switch(outMessage.charAt(0)){
                case '0':
                    System.out.println("CLIENT IS ALIVE");
                    break;
                case '1':
                    System.out.println("CLIENT CASE 1");
                    //sending message fishHasMoved
                    buffer = outMessage.getBytes();
                    packet = new DatagramPacket(buffer, buffer.length, realServerName, port);
                    client.send(packet);
                    break;
                case '2':
                    //sending message fishHasMoved
                    buffer = outMessage.getBytes();
                    packet = new DatagramPacket(buffer, buffer.length, realServerName, port);
                    client.send(packet);
                    break;
            }

            if (!inMessage.equals("exit")) {
                //receiving message
                packet = new DatagramPacket(buffer, buffer.length);
                client.receive(packet);

                inMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("[Aquarium Received message] -> " + inMessage);
            }

            //closing socket
            client.close();
            System.out.println("Aquarium Socket closed");

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public int getPort(){
        return this.port;
    }
    public void setPort(int port){
        this.port = port;
    }

}
