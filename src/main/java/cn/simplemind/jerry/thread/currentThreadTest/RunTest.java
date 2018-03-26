package cn.simplemind.jerry.thread.currentThreadTest;

/**
 * 
 * @author yingdui_wu
 * @date 2018年1月29日 下午7:21:05
 */
public class RunTest {
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t1 = new Thread(c);
        t1.setName("A");
        t1.start();
    }
}
