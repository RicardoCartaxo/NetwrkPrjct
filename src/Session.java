import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Session extends JPanel implements Runnable{

    private static Animation anim1 = new Animation("Anim1");
    private static Animation anim2 = new Animation("Anim2");
    private int rcv_port = 44500;
    int counter = 0;
    Thread t1;
    Thread t2;

    private String outMessage="1";
    private String inMessage="0";

    public Session(){
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
                    System.out.println("Session waiting for message on port " + rcv_port + "...");
                    if(!(counter == 0)){{
                        server.receive(packet);
                        inMessage = new String(packet.getData(), 0, packet.getLength());
                    }}

                    if(!inMessage.isEmpty()){
                        InetAddress address = packet.getAddress();
                        int rep_port = packet.getPort();
                    switch (inMessage.charAt(0)){
                    case '1':
                        System.out.println("CASE 1 SERVER IS RUNNING");
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
                        System.out.println("CASE 2 FISH HAS MOVED");
                        System.out.println("[Session Received message] -> "+inMessage);
                        //sending message
                        address = packet.getAddress();
                        rep_port = packet.getPort();
                        outMessage=new String(inMessage.toUpperCase());
                        System.out.println("[Session Replying to Message] -> "+outMessage);
                        buffer = outMessage.getBytes();
                        packet = new DatagramPacket(buffer, buffer.length, address, rep_port);
                        server.send(packet);
                    default:
                        System.out.println("CASE 0 SERVER IS RUNNING");
                        counter++;
                        break;
                }}

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

    public void add(int position, Animation animation){

    }
}
