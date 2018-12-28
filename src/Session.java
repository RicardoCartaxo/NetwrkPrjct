import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Session extends JPanel implements Runnable{

    private Animation[] anim_arr = new Animation[2];
    private int rcv_port = 44500;
    int counter = 0;
    Thread t1;
    Thread t2;

    private String outMessage="1";
    private String inMessage="0";

    public Session(){
        for(int i = 0; i < anim_arr.length;i++){
            anim_arr[i] = new Animation("Anim " +(i+1)+"");
        }
    }

    @Override
    public void run() {
        while(true)
        {
            try
            {
                    //server datagram socket and packet
                    DatagramSocket server=new DatagramSocket(rcv_port);
                    DatagramPacket packet = null;

                    //input and output streams
                    byte[] buffer=new byte[256];



                    //receiving message
                    packet=new DatagramPacket(buffer, buffer.length);
                    System.out.println("\nSession waiting for message on port " + rcv_port + "...");

                    if(!(counter == 0)){{
                        server.receive(packet);
                        inMessage = new String(packet.getData(), 0, packet.getLength());
                    }}

                    if(!inMessage.isEmpty()){
                        InetAddress address;
                        int rep_port;
                        int ID;
                        Position pos;
                        switch (inMessage.charAt(0)){
                            case '1':
                                System.out.println("SERVER case1: Do Nothing");
                                System.out.println("[Session Received message] -> "+inMessage);
                                //sending message
                                address = packet.getAddress();
                                rep_port = packet.getPort();
                                outMessage=new String(inMessage.toUpperCase());
                                System.out.println("[Session Replying to Message] -> "+outMessage);
                                buffer = outMessage.getBytes();
                                packet = new DatagramPacket(buffer, buffer.length, address, rep_port);
                                server.send(packet);
                                break;
                            case '2':
                                //TELL OTHER ANIMATION TO SPAWN FISH
                                System.out.println("SERVER case2: FishHasMoved");
                                System.out.println("[Session Received FishHasMoved message] -> "+inMessage);

                                //Getting needed info from packet and inMessage
                                address = packet.getAddress();
                                rep_port = packet.getPort();
                                ID = (Character.getNumericValue(inMessage.charAt(inMessage.length()-1)));
                                pos = null;
                                //Changing ID to send to OTHER aquarium
                                for(int i=0; i < anim_arr.length;i++){
                                    if (anim_arr[i].getAquarium().getID() != ID){
                                        ID = anim_arr[i].getAquarium().getID();
                                    }
                                }

                                // Message to send, if char(0)='!' then Aquarium needs to check ID
                                // If ID is the Aquariums, he needs to spawn a new fish
                                outMessage="!: "+ID+"";
                                System.out.println("[Session Notifying ALL] -> "+outMessage);
                                buffer = outMessage.getBytes();
                                packet = new DatagramPacket(buffer, buffer.length, address, rep_port);
                                server.send(packet);
                                break;

                            default:
                                System.out.println("CASE 0: Server Running");
                                counter++;
                                break;
                        }
                    }

                //closing socket
                server.close();
                System.out.println("Session Connection closed");

            } catch(SocketTimeoutException s)
            {
                System.out.println("Session Socket timed out!");
                break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }

    }
}
