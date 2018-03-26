package cn.simplemind.jerry.thread;

/**
 * 
 * @author yingdui_wu
 * @date   2018年1月29日 下午5:42:29
 */
public class ThreadSafe extends Thread {
    private int count = 5;

    @Override
    public void run() {
        super.run();
        count--;
        // 此示例不要用for语句，因为使用同步后其他线程就得不到运行的机会了，
        // 一直由一个线程进行减法运算        
        System.out.println("由 " + this.currentThread().getName() + " 计算，count=" + count);
    }
    
    public static void main(String[] args) {
        ThreadSafe mythread = new ThreadSafe();
        Thread a = new Thread(mythread, "A");
        Thread b = new Thread(mythread, "B");
        Thread c = new Thread(mythread, "C");
        Thread d = new Thread(mythread, "D");
        Thread e = new Thread(mythread, "E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
