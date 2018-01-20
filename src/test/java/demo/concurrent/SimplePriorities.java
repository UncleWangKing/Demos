package demo.concurrent;


public class SimplePriorities extends Thread {
    private int countDown = 5;
    private volatile double d = 0; // No optimization
    public SimplePriorities(int priority) {
        setPriority(priority);
        start();
    }
    public String toString() {
        return super.toString() + ": " + countDown;
    }
    public void run() {
        while(true) {
            // An expensive, interruptable operation:
            for(int i = 1; i < 100000; i++)
                d = d + (Math.PI + Math.E) / (double)i;
            System.out.println(this);
            if(--countDown == 0) return;
        }
    }
    public static void main(String[] args) {
        new SimplePriorities(Thread.MIN_PRIORITY);
        for(int i = 0; i < 5; i++)
            new SimplePriorities(Thread.MAX_PRIORITY);
    }
}