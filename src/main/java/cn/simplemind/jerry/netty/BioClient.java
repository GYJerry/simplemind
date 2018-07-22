package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * BIO 客户端
 * @author yingdui_wu
 * @date   2018年3月29日 下午7:31:44
 */
public class BioClient {
    public static void main(String[] args) {
        
        testBioServer();
    	
    }
    
    private static int cnt = 5;
    
    private static CyclicBarrier barrier = new CyclicBarrier(cnt);
    
    private static CountDownLatch countDownLatch = new CountDownLatch(cnt);
    
    private static Object logPrintLock = new Object(); 
    
    /**
     * 测试BIO
     * 
     * @author yingdui_wu
     * @date   2018年3月30日 上午10:06:31
     */
    public static void testBioServer() {
        
        class ConnectThread implements Runnable {

            int i;
            
            public ConnectThread(int i) {
                this.i = i;
            }
            
            @Override
            public void run() {
            	Socket clientSocket = null;
                try {
                	// 保证所有线程都同时发出请求
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    
                    List<String> logs = new ArrayList<>();
                    clientSocket = new Socket("127.0.0.1", 9999);
                    
                    // 1、写数据到服务端
                    OutputStream out = clientSocket.getOutputStream();
                    out.write(("==== Hello, I'm " + i + " ====").getBytes("UTF-8"));
                    out.flush();
                    
                    // 2、从服务端获取数据
                    long readTimeStart = System.currentTimeMillis();
                    InputStream input = clientSocket.getInputStream();
                    byte[] b = new byte[100];
                    int result = input.read(b);
                    while (result > 0) {
                        logs.add(new String(b));
                        result = input.read(b);
                    }
                    // 由于服务端向客户端写数据之前Sleep了2s，所以客户端从服务端获取数据需要至少等待2s的时间
                    logs.add("==== Request " + i + " cost " + (System.currentTimeMillis() - readTimeStart) + " ms");
                    
                    // 3、关闭输入输出流。必须最后才关闭，如果在【1】之后就关闭输入流，将会导致socket也被关闭
                    out.close();
                    input.close();
                    
                    // 4、操作完成关闭socket连接
                    clientSocket.close();
                    
                    // 每个线程执行完成均减1
                    countDownLatch.countDown();
                    
                    // 加锁保证每次打印的只是一条线程内的日志，不出现乱序
                    synchronized (logPrintLock) {
                        for (String log : logs) {
                            System.out.println(log);
                        }
                        System.out.println();
                    }
                    
                } catch (IOException e) {
                    System.out.println("第" + i + "个网络连接发生异常...");
                    e.printStackTrace();
                    if (clientSocket != null) {
                    	try {
							clientSocket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
                }
            }
        }
        
        System.out.println("==== Request begin... ====");
        System.out.println();
        
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++) {
            new Thread(new ConnectThread(i)).start();
        }
        
        try {
            // 等待所有线程执行完成
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 所有请求执行总耗时统计
        System.out.println();
        System.out.println("==== Request total cost " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
