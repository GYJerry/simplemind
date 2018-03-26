package cn.simplemind.jerry.thread.stop;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月2日 下午4:57:52
 */
public class StopTest {
    public static void main(String[] args) {
        InterruptMethod thread = new InterruptMethod();
        thread.setName("testRunning");
        thread.start();
    }
}
