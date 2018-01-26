package demo.zookeeper.config;

import demo.common.Config;
import demo.zookeeper.common.ConnectionWatcher;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ResilientConfigUpdater extends ConnectionWatcher {
    public static final String PATH="/config";
    private ChangedActiveKeyValueStore store;
    private Random random=new Random();
    
    public ResilientConfigUpdater(String hosts) throws IOException, InterruptedException {
        store=new ChangedActiveKeyValueStore();
        store.connect(hosts);
    }
    public void run() throws InterruptedException, KeeperException {
        while(true){
            String value=random.nextInt(100)+"";
            store.write(PATH,value);
            System.out.printf("Set %s to %s\n",PATH,value);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static void main(String[] args) throws Exception {
        while(true){
            try {
                ResilientConfigUpdater configUpdater = new ResilientConfigUpdater(Config.IP_WITH_PORT);
                configUpdater.run();
            }catch (KeeperException.SessionExpiredException e) {
                // start a new session
            }catch (KeeperException e) {
                // already retried ,so exit
                e.printStackTrace();
                break;
            }
        }
    }
}