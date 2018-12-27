public class Start {

    public static void main(String[] args) {

        Session session = new Session();
        Thread t = new Thread(session);
        t.start();

    }

}