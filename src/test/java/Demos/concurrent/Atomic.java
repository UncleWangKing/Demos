package demos.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

//锁 CAS 热点分离的CAS(类似ConcurrentHashMap的多把锁) 三者效率比较
//需1.8以上JDK方能打开看到完整3个
public class Atomic {
    private static final int MAX_THREADS = 5;                    //线程数
    private static final int TARGET_COUNT = 10000000;                //目标总数
    static CountDownLatch cdlsync = new CountDownLatch(MAX_THREADS);
    static CountDownLatch cdlatomic = new CountDownLatch(MAX_THREADS);
    static CountDownLatch cdladdr = new CountDownLatch(MAX_THREADS);
    private AtomicLong acount = new AtomicLong(0L);            //无锁的原子操作
    private LongAdder lacount = new LongAdder();
    private long count = 0;

    public static void main(String args[]) throws InterruptedException {
        Atomic a = new Atomic();
        a.testSync();
        a.testAtomic();
        a.testAtomicLong();
    }

    protected synchronized long inc() {                            //有锁的加法
        return ++count;
    }

    protected synchronized long getCount() {                        //有锁的操作
        return count;
    }

    public void clearCount() {
        count = 0;
    }

    public void testSync() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(this, starttime);
        for (int i = 0; i < MAX_THREADS; i++) {
            exe.submit(sync);                                //提交线程开始计算
        }
        cdlsync.await();
        exe.shutdown();
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        AtomicThread atomic = new AtomicThread(starttime);
        for (int i = 0; i < MAX_THREADS; i++) {
            exe.submit(atomic);                                //提交线程开始计算
        }
        cdlatomic.await();
        exe.shutdown();
    }

    public void testAtomicLong() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        LongAddrThread atomic = new LongAddrThread(starttime);
        for (int i = 0; i < MAX_THREADS; i++) {
            exe.submit(atomic);                                //提交线程开始计算
        }
        cdladdr.await();
        exe.shutdown();
    }

    public class SyncThread implements Runnable {
        protected String name;
        protected long starttime;
        Atomic out;                                        // TestAtomic为当前类名

        public SyncThread(Atomic o, long starttime) {
            out = o;
            this.starttime = starttime;
        }

        @Override
        public void run() {
            for (int i = 0; i < TARGET_COUNT; i++) {
                out.inc();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endtime - starttime) + "ms" + " value=" + out.getCount());
            cdlsync.countDown();
        }
    }

    public class AtomicThread implements Runnable {
        protected String name;
        protected long starttime;

        public AtomicThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            for (int i = 0; i < TARGET_COUNT; i++) {
                acount.incrementAndGet();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:" + (endtime - starttime) + "ms" + " value=" + acount.get());
            cdlatomic.countDown();
        }
    }

    public class LongAddrThread implements Runnable {
        protected String name;
        protected long starttime;

        public LongAddrThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            for (int i = 0; i < TARGET_COUNT; i++) {
                lacount.increment();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("LongAdder spend:" + (endtime - starttime) + "ms" + " value=" + lacount.sum());
            cdladdr.countDown();
        }
    }
}
