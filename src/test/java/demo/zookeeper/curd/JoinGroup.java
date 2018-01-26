package demo.zookeeper.curd;

import demo.common.Config;
import demo.zookeeper.common.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

public class JoinGroup extends ConnectionWatcher {
    public void join(String groupName,String memberName) throws KeeperException, InterruptedException{
        String path="/"+groupName+"/"+memberName;
        String createdPath=zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created:"+createdPath);
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect(Config.ipWithPort);
        joinGroup.join("zoo", "miao");
        
        //stay alive until process is killed or thread is interrupted
        Thread.sleep(Long.MAX_VALUE);
    }
}