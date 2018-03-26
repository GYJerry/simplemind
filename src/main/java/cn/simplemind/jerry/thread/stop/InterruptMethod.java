package cn.simplemind.jerry.thread.stop;

/**
 * 
 * @author yingdui_wu
 * @date 2018年1月30日 上午10:07:35
 */
public class InterruptMethod extends Thread {
    @Override
    public void run() {
        super.run();
        /*
         * try { sleep(1000000); } catch (InterruptedException e) {
         * e.printStackTrace(); }
         */
        for (int i = 0; i < 500000; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("i=" + (i + 1));
        }
    }

    private static void test1() {
        try {
            InterruptMethod thread = new InterruptMethod();
            thread.start();
            Thread.sleep(2000); // 主线程停止2s
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }

    private static void test2() {
        try {
            InterruptMethod thread = new InterruptMethod();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            // Thread.currentThread().interrupt();
            System.out.println("是否停止1？=" + Thread.interrupted());
            System.out.println("是否停止2？=" + Thread.interrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }

    private static void testMainInterrupt() {
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？=" + Thread.interrupted()); // 测试当前线程是否已经中断。线程的中断状态由该方法清除
        System.out.println("是否停止2？=" + Thread.interrupted());
        System.out.println("end!");
    }
    
    private static void test3() {
        try {
            InterruptMethod thread = new InterruptMethod();
            thread.start();
            Thread.sleep(1000);
            System.out.println("是否停止是否停止是否停止是否停止是否停止是否停止是否停止1？=" + thread.isInterrupted());
            thread.interrupt();
            System.out.println("是否停止是否停止是否停止是否停止是否停止是否停止是否停止2？=" + thread.isInterrupted());
            System.out.println("是否停止是否停止是否停止是否停止是否停止是否停止是否停止3？=" + thread.isInterrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
    
    private static void test4() {
        InterruptMethod thread = new InterruptMethod();
        //thread.setName("testRunning");
        thread.start();
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        // testMainInterrupt();
        // test3();
        test4();
    }
}