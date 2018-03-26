package cn.simplemind.jerry.thread.pcModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yingdui_wu
 * @date 2018年2月5日 上午9:35:41
 */
public class MyStack {
    private List list = new ArrayList();

    synchronized public void push() {
        try {
            /*if (list.size() == 1) {
                this.wait();
            }*/
            while (list.size() == 1) {
                this.wait();
            }
            list.add(Math.random());
            /*this.notify();*/
            this.notifyAll();
            System.out.println("push：" + Thread.currentThread().getName() + "线程中，list.size() = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void pop() {
        try {
            /*if (list.size() == 0) {
                System.out.println("pop操作中的：" + Thread.currentThread().getName() + " 线程呈wait状态");
                this.wait();
            }*/
            while (list.size() == 0) {
                System.out.println("pop操作中的：" + Thread.currentThread().getName() + " 线程呈wait状态");
                this.wait();
            }
            String returnValue = "" + list.get(0);
            list.remove(0);
            /*this.notify();*/
            this.notifyAll();
            System.out.println("pop操作中的：" + Thread.currentThread().getName() + " 线程激活，pop=" + returnValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
