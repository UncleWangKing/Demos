package demo.concurrent;

/**
 * 展示可见性导致的死锁
 */
public class VolatileDeadLock {

    public static boolean flag = false;
    //public static volatile boolean flag = false;

    static class AgeThread implements Runnable{
        public void run() {
            System.out.println("begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag=true;
            System.out.println("over");
        }
    }

    static class GradeThread implements Runnable{
        public void run() {

            while (!flag){
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            System.out.println("sssssss");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AgeThread ageThread = new AgeThread();
        GradeThread gradeThread = new GradeThread();
        Thread t1 = new Thread(ageThread);
        Thread t2 = new Thread(gradeThread);
        t2.start();t1.start();
        t1.join();t2.join();
        System.out.println("结束");
    }
}
