package cn.simplemind.jerry.demo.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * zookeeper相关操作
 * @author yingdui_wu
 * @date   2018年1月25日 下午5:18:47
 */
public class ZKOperation {
    
    // create static instance for zookeeper class.
    private static ZooKeeper zk;

    // create static instance for ZooKeeperConnection class.
    private static ZooKeeperConnection conn;
    
    // Method to create znode in zookeeper ensemble
    public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    
    // Method to check existence of znode and its status, if znode is available.
    public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }
    
    public static byte[] get(String path, boolean watch, Stat stat) throws KeeperException, InterruptedException {
        return zk.getData(path, watch, stat);
    }
    
    // Method to update the data in a znode. Similar to getData but without watcher.
    public static void update(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.setData(path, data, zk.exists(path, true).getVersion());
    }
    
    public static void main(String[] args) {
        // znode path
        String path = "/programZnode"; // Assign path to znode
        
        // data in byte array
        byte[] data = "add by program".getBytes(); // Declare data

        conn = new ZooKeeperConnection();
        
        try {
            zk = conn.connect("localhost");
            create(path, data); // Create the data to the specified path
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Catch error message
        }
        
        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect("localhost");
            Stat stat = znode_exists(path); // Stat checks the path of the znode
            if (stat != null) {
                System.out.println("Node exists and the node version is " + stat.getVersion());
            }
            else {
                System.out.println("Node does not exists");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage()); // Catches error messages
        }
        
        try {
            zk = conn.connect("localhost");
            String result = new String(get(path, false, null));
            System.out.println(result);
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Catch error message
        }
        
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        try {
            zk = conn.connect("localhost");
            Stat stat = znode_exists(path);

            if (stat != null) {
                byte[] b = zk.getData(path, new Watcher() {

                    public void process(WatchedEvent we) {
                        if (we.getType() == Event.EventType.None) {
                            switch (we.getState()) {
                                case Expired:
                                    connectedSignal.countDown();
                                    break;
                            }
                        }
                        else {
                            try {
                                byte[] bn = zk.getData(path, false, null);
                                String data = new String(bn, "UTF-8");
                                System.out.println(data);
                                connectedSignal.countDown();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                }, null);
                System.out.println(new String(b, "UTF-8"));
                connectedSignal.await();
            }
            else {
                System.out.println("Node does not exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            zk = conn.connect("localhost");
            byte[] updateData = "Success".getBytes(); //Assign data which is to be updated.
            update(path, updateData); // Update znode data to the specified path
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
