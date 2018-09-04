package cn.simplemind.test.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *
 * @author yingdui_wu
 * @date   2018年8月13日 下午4:58:04
 */
public class CodeExample {
    
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        
        Semaphore semaphore = new Semaphore(5);
        
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        
        CountDownLatch countDownLatch = new CountDownLatch(10);
        
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    }
    
}
