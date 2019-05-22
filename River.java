import java.util.Deque;
import java.util.LinkedList;

public class River {
    private Deque<RandomThread> queue;
    private int Racer;

    public River(int nRacer) {
        this.Racer = nRacer;
        queue = new LinkedList<>();
    }

    public synchronized void addToVector(RandomThread randomThread) {
        RandomThread previousRacer = queue.peekLast();
        if (previousRacer != null) {
            previousRacer.setNextRacer(randomThread);
        }
        queue.add(randomThread);
    }

    public boolean allReadyToCrossTheRiver() {
        return queue.size() == Racer;
    }

    public void awakeAll() {
        for (RandomThread randomThread : queue) {
            randomThread.getThread().interrupt();
        }
    }
}
