package cn.simplemind.jerry.thread.stop;

/**
 * 线程先停止，再遇到了sleep
 * @author yingdui_wu
 * @date 2018年1月30日 下午2:13:12
 */
public class InterruptThenSleep extends Thread {
    
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 100000; i++) {
                System.out.println("i=" + (i + 1));
            }
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("先停止，再遇到了sleep!进入catch!");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        InterruptThenSleep thread = new InterruptThenSleep();
        thread.start();
        thread.interrupt();
        System.out.println("end!");
    }
    
}
