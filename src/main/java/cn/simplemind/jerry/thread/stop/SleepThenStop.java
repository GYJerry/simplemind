package cn.simplemind.jerry.thread.stop;

/**
 * 在沉睡中被停止
 * @author yingdui_wu
 * @date 2018年1月30日 下午2:05:55
 */
public class SleepThenStop extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止!进入catch!" + this.isInterrupted());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            SleepThenStop thread = new SleepThenStop();
            thread.start();
            Thread.sleep(200);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
    
}
