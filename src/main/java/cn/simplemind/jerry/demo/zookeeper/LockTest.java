package cn.simplemind.jerry.demo.zookeeper;

/**
 * 分布式锁测试
 *
 * @author yingdui_wu
 * @date   2018年8月7日 下午3:44:14
 */
public class LockTest {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        
        Runnable runnable = new Runnable() {
            public void run() {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock("172.20.10.222:2181", "simplemind");
                    lock.lock();
                    secskill();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
