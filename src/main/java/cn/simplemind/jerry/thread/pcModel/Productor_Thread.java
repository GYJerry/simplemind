package cn.simplemind.jerry.thread.pcModel;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月5日 上午10:05:31
 */
public class Productor_Thread extends Thread {
    
    private MyStack myStack;

    public Productor_Thread(MyStack myStack) {
        super();
        this.myStack = myStack;
    }
    
    @Override
    public void run() {
        super.run();
        while (true) {
            myStack.push();
        }
    }
}
