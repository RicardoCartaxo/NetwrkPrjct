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

    private static int ID_helper = 0;
    private int ID;

    // Going over around 25 sometimes causes a StackOverflowException (due to there not being any more space on the canvas)
    static final int NB_STONES = 2;
    static final int NB_SEAWEED = 2;
    static final int NB_FISH = 4;

    private StoneFactory stF;
    private SeaweedFactory swF;
    private FishFactory fshF;

    protected Collection<AquariumItem> items;
    protected Timer tm = new Timer(10, this);


    private  boolean right;
    private boolean left;

    private String outMessage = "0";
    private String inMessage = "1";


    public Aquarium() {
        this.setBackground(Color.CYAN);
        items = new ArrayList<>();
        this.fill();
        ID_helper++;
        ID=ID_helper;
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
        go(); //make them movw
        repaint(); //repaint
        run(); //set Message, send Message, receive message
    }

    public void run() {
        for (AquariumItem aq_it : items) {
            if (aq_it instanceof MobileItem) {
                if(aq_it.getPosition().x < 0 || aq_it.getPosition().x >350) {
                    outMessage = "2: " + aq_it.getPosition() + " : " + ID;
                    // this.items.remove(aq_it);
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

            switch(outMessage.charAt(0)){
                case '1':
                    System.out.println("CLIENT Case 1: Do Nothing");
                    //sending message doNothing
                    buffer = outMessage.getBytes();
                    packet = new DatagramPacket(buffer, buffer.length, realServerName, port);
                    client.send(packet);
                    break;
                case '2':
                    System.out.println("CLIENT Case 2: My Fish Has Moved");
                    //sending message fishHasMoved
                    buffer = outMessage.getBytes();
                    packet = new DatagramPacket(buffer, buffer.length, realServerName, port);
                    client.send(packet);
                    break;
                default:
                System.out.println("CLIENT Default");
            }

            if (!inMessage.isEmpty()) {
                //receiving message
                packet = new DatagramPacket(buffer, buffer.length);
                client.receive(packet);

                inMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("[Aquarium Received message] -> " + inMessage);

                switch(inMessage.charAt(0)){
                    case '1':

                        break;
                    case '2':

                        break;
                    case '!':

                        //Control ID contained in Message
                        if((Character.getNumericValue(inMessage.charAt(3))==this.ID)){
                            items.add(new Fish(50));
                        }

                        break;
                }
            }

            //closing socket
            client.close();
            System.out.println("Aquarium Socket closed");

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public int getID(){
        return this.ID;
    }

}
