public class Time extends Thread {

    protected Aquarium aquarium;

    public Time(Aquarium aquarium){
        this.aquarium = aquarium;
    }

    @Override
    public void run() {
        aquarium.go();
    }
}
