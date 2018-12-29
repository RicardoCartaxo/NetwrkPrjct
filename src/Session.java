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
    private int rcv_port1 = 44500;
    private int rcv_port2 = 44501;
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
                    DatagramSocket server1=new DatagramSocket(rcv_port1);
                    DatagramPacket packet1 = null;
                    DatagramSocket server2=new DatagramSocket(rcv_port2);
                    DatagramPacket packet2 = null;

                    //input and output streams
                    byte[] buffer=new byte[512];



                    //receiving message
                    packet1=new DatagramPacket(buffer, buffer.length);
                    System.out.println("\nSession waiting for message on port " + rcv_port1 + "...");
                    packet2=new DatagramPacket(buffer, buffer.length);
                    System.out.println("\nSession waiting for message on port " + rcv_port2 + "...");

                    if(!(counter == 0)){{
                        server1.receive(packet1);
                        inMessage = new String(packet1.getData(), 0, packet1.getLength());
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
                                //Changing ID to send to OTHER aquarium
                                if(ID==1){
                                    ID=2;
                                }
                                if(ID==2){
                                    ID=1;
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
