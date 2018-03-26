package cn.simplemind.jerry.thread.category;

/**
 * 
 * @author yingdui_wu
 * @date   2018年1月29日 下午4:32:38
 */
public class ThreadTestThread extends Thread {
    private Thread t;
    private String threadName;
    
    ThreadTestThread(String name) {
       threadName = name;
       System.out.println("Creating " +  threadName );
    }
    
    public void run() {
       System.out.println("Running " +  threadName );
       try {
          for(int i = 4; i > 0; i--) {
             System.out.println("Thread: " + threadName + ", " + i);
             // 让线程睡眠一会
             Thread.sleep(50);
          }
       }catch (InterruptedException e) {
          System.out.println("Thread " +  threadName + " interrupted.");
       }
       System.out.println("Thread " +  threadName + " exiting.");
    }
    
    public void start () {
       System.out.println("Starting " +  threadName );
       if (t == null) {
          t = new Thread (this, threadName);
          t.start ();
       }
    }
    
    public static void main(String args[]) {
        ThreadTestThread T1 = new ThreadTestThread( "Thread-1");
        T1.start();
        
        ThreadTestThread T2 = new ThreadTestThread( "Thread-2");
        T2.start();
     }  
}
