import java.util.Random;

public class RandomThread implements Runnable {
    private Thread thread;
    private String name;
    public static long time = System.currentTimeMillis();
    public long forestTime;
    public long mountainTime;
    public long riverTime;
    public long totalTime;
    private Random rnd = new Random();

    private Forest forest;
    private Mountain mountain;
    private River river;
    private Home home;

    private volatile boolean ready;
    private RandomThread nextRacer;

    public RandomThread(int id) {
        setName("RandomThread-" + id);
        thread = new Thread(this);
    }

    public Thread getThread() {
        return thread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setForest(Forest forest) {
        this.forest = forest;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = mountain;
    }

    public void setRiver(River river) {
        this.river = river;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setNextRacer(RandomThread nextRacer) {
        this.nextRacer = nextRacer;
    }

    public RandomThread getNextRacer() {
        return nextRacer;
    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }

    @Override
    public void run() {
        sleepRandomTime();
        forest();
        sleepRandomTime();
        mountain();
        sleepLongTime();
        river();
        getHome();
        totalTime = System.currentTimeMillis() - Main.time;
    }

    private void sleepRandomTime() {
        int time = 2000 + rnd.nextInt(3000);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void forest() {
        long time = System.currentTimeMillis();
        msg("In the Forest...");
        int acceleration = rnd.nextInt(4 + 1);
        thread.setPriority(thread.getPriority() + acceleration);
        String magicWord = forest.generateWord();
        boolean found = forest.contains(magicWord);
        thread.setPriority(Thread.NORM_PRIORITY);
        if (!found) {
            msg("Map not found!");
            Thread.yield();
            Thread.yield();
        }
        forestTime = System.currentTimeMillis() - time;
    }

    private void mountain() {
        mountain.addToVector(this);
        while (!isReady()) {
            busyWait();
        }

        long time = System.currentTimeMillis();
        msg("Go over Passage...");
        sleepRandomTime();
        mountain.awakeFirst();
        mountainTime = System.currentTimeMillis() - time;
    }

    private void busyWait() {
        int i = 0;
        while (i < 1000) {
            Math.cos(i++);
        }
    }

    private void sleepLongTime() {
        river.addToVector(this);

        msg("Sleeping Long Time...");
        try {
            while (!thread.isInterrupted()) {
                Thread.sleep(100_000);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void river() {
        long time = System.currentTimeMillis();
        msg("Crossing the River...");
        sleepRandomTime();
        riverTime = System.currentTimeMillis() - time;
    }

    private void getHome() {
        msg("Get Home...");
        if (getNextRacer() != null && getNextRacer().getThread().isAlive()) {
            try {
                getNextRacer().getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sleepRandomTime();
        home.addToVector(this);
        msg("Finished.");
    }
}
