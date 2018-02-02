package demo.zookeeper.leader;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class LeaderElection {
  
    private ZooKeeper zk;
    private int sessionTimeout;  
  
    private static byte[] DEFAULT_DATA = {0x12,0x34};  
    private static Object mutex = new Object();

    private static String ROOT = "/leader";  
  
    private byte[] localhost = getLocalIpAdressBytes();  
  
    private String znode;  
  
    private static CountDownLatch firstElectionSignal = new CountDownLatch(1);
  
    //----------------------------------------------------  
    // leader的IP地址  
    //----------------------------------------------------  
    private static String leader;  
  
    public LeaderElection() throws IOException, InterruptedException, KeeperException {
        this.zk = ZooKeeperClient.getInstance();  
        this.sessionTimeout = zk.getSessionTimeout();  
        ensureExists(ROOT);  
        ensureLocalNodeExists();  
  
        System.out.println("-------------------------------------");  
        System.out.println("local IP: " + getLocalIpAddress());  
        System.out.println("local created node: " + znode);  
        System.out.println("-------------------------------------");  
  
  
    }  
  
    /** 
     * 检查本机是否已创建节点，不存在则创建 
     * @throws KeeperException 
     * @throws InterruptedException 
     */  
    public void ensureLocalNodeExists() throws KeeperException, InterruptedException {  
        List<String> list = zk.getChildren(ROOT,new NodeDeleteWatcher());
        for (String node : list) {  
            Stat stat = new Stat();  
            String path = ROOT + "/" + node;  
            byte[] data = zk.getData(path,false,stat);  
  
            if (Arrays.equals(data,localhost)) {
                znode = path;  
                return;  
            }  
        }  
        znode = zk.create(ROOT + "/", localhost, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
  
    }  
  
    public void ensureExists(String path) {  
        try {  
            Stat stat = zk.exists(path, false);
            if (stat == null) {  
                zk.create(path, DEFAULT_DATA, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);  
            }  
        } catch (KeeperException e) {
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void start() throws KeeperException, InterruptedException, UnsupportedEncodingException {
  
        do{  
            synchronized (mutex) {
  
                System.out.println("begin leader election...");  
  
                List<String> nodes = zk.getChildren(ROOT, null);  
                SortedSet<String> sortedNode = new TreeSet<String>();
                for (String node : nodes) {  
                    sortedNode.add(ROOT + "/" + node);  
                }  
                //----------------------------------------------------  
                // 取出序列号最小的消息  
                //----------------------------------------------------  
                String first = sortedNode.first();  
                leader = first;  
                //----------------------------------------------------  
                // 监控序列最小节点（非本机创建节点）  
                //----------------------------------------------------  
                NodeDeleteWatcher watcher = znode.equals(first) ? null : new NodeDeleteWatcher();  
                byte[] data = zk.getData(first, watcher, null);  
                leader = new String(data, "UTF-8");  
  
                System.out.println("leader election end, the leader is : " + leader);  
  
                if (firstElectionSignal.getCount() != 0) {  
                    firstElectionSignal.countDown();  
                }  
  
                if (znode.equals(first)) {  
                    return;  
                }  
  
                mutex.wait();  
            }  
        } while (true);  
  
  
    }  
  
    class NodeDeleteWatcher implements Watcher {
        @Override  
        public void process(WatchedEvent event) {  
            if (event.getType() == Event.EventType.NodeDeleted) {  
                synchronized (mutex) {  
                    mutex.notify();  
                }  
            }  
        }  
    }  
  
    /** 
     * 获取本地内网IP地址 
     * @return 
     * @throws SocketException 
     */  
    public static String getLocalIpAddress() throws SocketException {
        // 获得本机的所有网络接口  
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
  
        while (nifs.hasMoreElements()) {  
            NetworkInterface nif = nifs.nextElement();  
  
            // 获得与该网络接口绑定的 IP 地址，一般只有一个  
            Enumeration<InetAddress> addresses = nif.getInetAddresses();
            while (addresses.hasMoreElements()) {  
                InetAddress addr = addresses.nextElement();  
  
                String ip = addr.getHostAddress();  
                // 只关心 IPv4 地址  
                if (addr instanceof Inet4Address && !"127.0.0.1".equals(ip)) {
                    return ip;  
                }  
            }  
        }  
        return null;  
    }  
  
    public static byte[] getLocalIpAdressBytes() throws SocketException {  
        String ip = getLocalIpAddress();  
        return ip == null ? null : ip.getBytes();  
    }  
  
    public static String getLeader() throws InterruptedException {  
        //----------------------------------------------------  
        // 第一次leader选举完成后才释放阀门  
        //----------------------------------------------------  
        firstElectionSignal.await();  
        return leader;  
    }  
  
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {  
  
        //-------------------------------------------------------  
        // 启动一条线程用处理leader选举  
        //-------------------------------------------------------  
        new Thread(new Runnable() {  
            @Override  
            public void run() {  
                try {  
                    LeaderElection leaderElection = new LeaderElection();  
                    leaderElection.start();  
                } catch (KeeperException e) {  
                    e.printStackTrace();  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }).start();  
  
        String leader = LeaderElection.getLeader();  
        Thread.sleep(Integer.MAX_VALUE);
    }  
}  