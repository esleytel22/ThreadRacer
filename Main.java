public class Main {
    private static final int DEFAULT_N_RACER = 10;
    public static long time = System.currentTimeMillis();

    private Main(int Racer) {
        race(Racer);
    }

    private void race(int Racer) {
        Forest forest = new Forest();
        Mountain mountain = new Mountain(Racer);
        River river = new River(Racer);
        Home home = new Home(Racer);

        for (int i = 0; i < Racer; i++) {
            RandomThread racer = new RandomThread(i);
            racer.setForest(forest);
            racer.setMountain(mountain);
            racer.setRiver(river);
            racer.setHome(home);
            racer.getThread().start();
        }

        JudgeThread judge = new JudgeThread();
        judge.setRiver(river);
        judge.setHome(home);
        judge.getThread().start();
    }

    public static void main(String[] args) {
        int nRacer = DEFAULT_N_RACER;
        if (args.length > 0) {
            nRacer = Integer.parseInt(args[0]);
        }
        new Main(nRacer);
    }
}
