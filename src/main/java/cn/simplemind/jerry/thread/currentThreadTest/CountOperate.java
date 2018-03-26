package cn.simplemind.jerry.thread.currentThreadTest;

/**
 * 
 * @author yingdui_wu
 * @date 2018年1月29日 下午7:20:07
 */
public class CountOperate extends Thread {
    public CountOperate() {
        System.out.println("CountOperate");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("run");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
    }

    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t1 = new Thread(c);
        t1.setName("A");
        t1.start();
    }
}
