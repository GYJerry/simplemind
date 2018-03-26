package cn.simplemind.jerry.thread.pcModel;

/**
 * 一生产者对多消费者
 * @author yingdui_wu
 * @date   2018年2月5日 上午9:33:33
 */
public class onePAndManyC {
    public static void main(String[] args) throws InterruptedException {
        
        MyStack myStack = new MyStack();
        
        // 生产者线程
        Productor_Thread pThread = new Productor_Thread(myStack);
        pThread.setName("Productor");
        pThread.start();
        
        Thread.sleep(2000);
        
        // 消费者线程
        Consumer_Thread cThread1 = new Consumer_Thread(myStack);
        Consumer_Thread cThread2 = new Consumer_Thread(myStack);
        Consumer_Thread cThread3 = new Consumer_Thread(myStack);
        Consumer_Thread cThread4 = new Consumer_Thread(myStack);
        Consumer_Thread cThread5 = new Consumer_Thread(myStack);
        
        cThread1.setName("Consumer-1");
        cThread2.setName("Consumer-2");
        cThread3.setName("Consumer-3");
        cThread4.setName("Consumer-4");
        cThread5.setName("Consumer-5");
        
        cThread1.start();
        cThread2.start();
        cThread3.start();
        cThread4.start();
        cThread5.start();
    }
}
