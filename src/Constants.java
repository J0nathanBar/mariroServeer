
public final class Constants {
    // public static final int time = 60;

    public static final byte pauseGame = -1;
    public static final byte resumeGame = -2;
    public static final byte noChange = 0;

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
