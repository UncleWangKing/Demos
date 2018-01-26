package demo.zookeeper.lock;

public class ZkTest {
    public static void main(String[] args) {
        ConcurrentTest.ConcurrentTask[] tasks = new ConcurrentTest.ConcurrentTask[3];
        for(int i=0;i<tasks.length;i++){
            ConcurrentTest.ConcurrentTask task3 = new ConcurrentTest.ConcurrentTask(){
                public void run() {
                    DistributedLock lock = null;
                    try {
                        lock = new DistributedLock("172.168.70.113:2183","test2");
                        lock.lock();
                        System.out.println("Thread " + Thread.currentThread().getId() + " running");
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        lock.unlock();
                    }
                     
                }
            };
            tasks[i] = task3;
        }
        new ConcurrentTest(tasks);
    }
}