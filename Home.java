import java.util.LinkedList;
import java.util.List;

public class Home {
    private List<RandomThread> queue;
    private int Racer;

    public Home(int Racer) {
        this.Racer = Racer;
        queue = new LinkedList<>();
    }

    public synchronized void addToVector(RandomThread thread) {
        queue.add(thread);
    }

    public boolean allFinished() {
        return queue.size() == Racer;
    }

    public void printQueue() {
        for (int i = 0; i < queue.size(); i++) {
            RandomThread randomThread = queue.get(i);
            System.out.println("" + (i + 1) + ": " + randomThread.getName() + ". Total time: " + randomThread.totalTime);
            System.out.println("\tForest time:\t" + randomThread.forestTime);
            System.out.println("\tMountain time:\t" + randomThread.mountainTime);
            System.out.println("\tRiver time:\t\t" + randomThread.riverTime);
        }
    }
}
