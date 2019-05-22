public class JudgeThread implements Runnable {
    private String name = "Judge";
    private static long time = System.currentTimeMillis();
    private River river;
    private Home home;
    private Thread thread;

    public JudgeThread() {
        setName("Judge");
        thread = new Thread(this);
    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + getName() + ": " + m);
    }

    public String getName() {
        return name;
    }

    public Thread getThread() {
        return thread;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRiver(River river) {
        this.river = river;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while (!river.allReadyToCrossTheRiver()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        msg("Awake all !!!");
        river.awakeAll();

        msg("Waiting until all finished...");
        while (!home.allFinished()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        printStatistics();
    }

    private void printStatistics() {
        System.out.println("\nStatistics:");
        home.printQueue();
    }
}
