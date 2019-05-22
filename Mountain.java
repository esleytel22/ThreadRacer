import java.util.LinkedList;
import java.util.Queue;

public class Mountain {
    private Queue<RandomThread> queue;
    private int Racer;

    public Mountain(int Racer) {
        this.Racer = Racer;
        queue = new LinkedList<>();
    }

    public synchronized void addToVector(RandomThread thread) {
        queue.add(thread); 
        if (queue.size() == Racer) {
            awakeFirst();
        }
    }

    public void awakeFirst() {
        RandomThread randomThread = queue.poll();
        if (randomThread != null) {
            randomThread.setReady(true);
        }
    }
}
