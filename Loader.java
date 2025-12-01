// Name: Anjali Tomar
// Roll Number: 2401010080
package util;
public class Loader implements Runnable {
    private int iterations;
    private long pause;

    public Loader() {
        this.iterations = 5;
        this.pause = 300;
    }

    public Loader(int iterations, long pause) {
        this.iterations = iterations;
        this.pause = pause;
    }

    @Override
    public void run() {
        try {
            System.out.print("Loading");
            for (int i = 0; i < iterations; i++) {
                Thread.sleep(pause);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("\nLoading interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
