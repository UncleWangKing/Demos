package demo.zookeeper.leader;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ZooKeeperQueue {
  
    private ZooKeeper zk;
    private int sessionTimeout;  
  
    private static byte[] ROOT_QUEUE_DATA = {0x12,0x34};  
    private static String QUEUE_ROOT = "/QUEUE";  
    private String queueName;  
    private String queuePath;  
    private Object mutex = new Object();  
  
    public ZooKeeperQueue(String queueName) throws IOException, KeeperException, InterruptedException {
        this.queueName = queueName;  
        this.queuePath = QUEUE_ROOT + "/" + queueName;  
        this.zk = ZooKeeperClient.getInstance();  
        this.sessionTimeout = zk.getSessionTimeout();  
  
        //----------------------------------------------------  
        // 确保队列根目录/QUEUE和当前队列的目录的存在  
        //----------------------------------------------------  
        ensureExists(QUEUE_ROOT);  
        ensureExists(queuePath);  
    }  
  
    public byte[] consume() throws InterruptedException, KeeperException, UnsupportedEncodingException {
        List<String> nodes = null;
        byte[] returnVal = null;  
        Stat stat = null;
        do {  
            synchronized (mutex) {  
  
                nodes = zk.getChildren(queuePath, new ProduceWatcher());  
  
                //----------------------------------------------------  
                // 如果没有消息节点，等待生产者的通知  
                //----------------------------------------------------  
                if (nodes == null || nodes.size() == 0) {  
                    mutex.wait();
                } else {  
  
                    SortedSet<String> sortedNode = new TreeSet<String>();
                    for (String node : nodes) {  
                        sortedNode.add(queuePath + "/" + node);  
                    }  
  
                    //----------------------------------------------------  
                    // 消费队列里序列号最小的消息  
                    //----------------------------------------------------  
                    String first = sortedNode.first();  
                    returnVal = zk.getData(first, false, stat);  
                    zk.delete(first, -1);  
  
                    System.out.print(Thread.currentThread().getName() + " ");  
                    System.out.print("consume a message from queue：" + first);  
                    System.out.println(", message data is: " + new String(returnVal,"UTF-8"));  
  
                    return returnVal;  
                }  
            }  
        } while (true);  
    }  
  
    class ProduceWatcher implements Watcher {
        @Override  
        public void process(WatchedEvent event) {
            //----------------------------------------------------  
            // 生产一条消息成功后通知一个等待线程  
            //----------------------------------------------------  
            synchronized (mutex) {  
                mutex.notify();  
            }  
        }  
    }  
  
    public void produce(byte[] data) throws KeeperException, InterruptedException, UnsupportedEncodingException {  
  
        //----------------------------------------------------  
        // 确保当前队列目录存在  
        // example: /QUEUE/queueName  
        //----------------------------------------------------  
        ensureExists(queuePath);  
  
        String node = zk.create(queuePath + "/", data,  
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL);  
  
  
        System.out.print(Thread.currentThread().getName() + " ");  
        System.out.print("produce a message to queue：" + node);  
        System.out.println(" , message data is: " + new String(data,"UTF-8"));  
    }  
  
  
    public void ensureExists(String path) {  
        try {  
            Stat stat = zk.exists(path, false);  
            if (stat == null) {  
                zk.create(path, ROOT_QUEUE_DATA, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);  
            }  
        } catch (KeeperException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
  
  
  
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {  
  
        String queueName = "test";  
        final ZooKeeperQueue queue = new ZooKeeperQueue(queueName);  
        //创建是个消费者
        for (int i = 0; i < 10; i++) {  
            new Thread(new Runnable() {  
                @Override  
                public void run() {  
                    try {  
                        queue.consume();  
                        System.out.println("--------------------------------------------------------");  
                        System.out.println();  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    } catch (KeeperException e) {  
                        e.printStackTrace();  
                    } catch (UnsupportedEncodingException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }  
//        一个生产者生产10个"产品"
        new Thread(new Runnable() {
            @Override  
            public void run() {  
                for (int i = 0; i < 10; i++) {  
                    try {
//                      生产间隔
                        Thread.sleep(new Random().nextInt(2000));
                        queue.produce(("massive" + i).getBytes());  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    } catch (KeeperException e) {  
                        e.printStackTrace();  
                    } catch (UnsupportedEncodingException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        },"Produce-thread").start();  
    }
}