package cn.simplemind.jerry.thread.pcModel;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月5日 上午10:08:07
 */
public class Consumer_Thread extends Thread {
    
    private MyStack myStack;

    public Consumer_Thread(MyStack myStack) {
        super();
        this.myStack = myStack;
    }
    
    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myStack.pop();
        }
    }
}
