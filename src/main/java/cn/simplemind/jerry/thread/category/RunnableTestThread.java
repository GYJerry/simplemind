package cn.simplemind.jerry.thread.category;

/**
 * 
 * @author yingdui_wu
 * @date 2018年1月29日 下午3:38:28
 */
public class RunnableTestThread implements Runnable {

    private Thread t;
    private String threadName;

    RunnableTestThread(String name) {
       threadName = name;
       System.out.println("Creating " +  threadName );
    }
    
    public static void main(String args[]) {
        RunnableTestThread R1 = new RunnableTestThread( "Thread-1");
        R1.start();
        
        RunnableTestThread R2 = new RunnableTestThread( "Thread-2");
        R2.start();
     }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

}
