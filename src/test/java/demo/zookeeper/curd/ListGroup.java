package demo.zookeeper.curd;

import java.io.IOException;
import java.util.List;

import demo.common.Config;
import demo.zookeeper.common.ConnectionWatcher;
import org.apache.zookeeper.KeeperException;

public class ListGroup extends ConnectionWatcher {
    public void list(String groupNmae) throws KeeperException, InterruptedException{
        String path ="/"+groupNmae;
        try {
            List<String> children = zk.getChildren(path, false);
            if(children.isEmpty()){
                System.out.printf("No memebers in group %s\n",groupNmae);
                System.exit(1);
            }
            for(String child:children){
                System.out.println(child);
            }
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist \n", groupNmae);
            System.exit(1);
        } 
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect(Config.IP_WITH_PORT);
        listGroup.list("zoo");
        listGroup.close();
    }
}