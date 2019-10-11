package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO 客户端
 * 
 * @author yingdui_wu
 * @date   2018年3月29日 下午7:31:44
 */
public class BioClient {
	
	public static void main(String[] args) {
		request();
	}
    
    private static int cnt = 4;
    
    private static CyclicBarrier barrier = new CyclicBarrier(cnt);
    
    private static CountDownLatch countDownLatch = new CountDownLatch(cnt);
    
    /**
     * 测试BIO
     * 
     * @author yingdui_wu
     * @date   2018年3月30日 上午10:06:31
     */
    public static void request() {
    	
    	ExecutorService threadPool = Executors.newCachedThreadPool();
    	
    	System.out.println("==== Request begin... ==== \n");
        long startTime = System.currentTimeMillis();
    	
    	// 启动n条线程同时向BIO服务端发起请求
        for (int j = 0; j < cnt; j++) {
			int i = j;
			threadPool.execute(() -> {
				connectAndSend(i);
			});
		}
        
        try {
            // 等待所有线程执行完成
            countDownLatch.await();
            // 关闭线程池
            threadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
        
        // 所有请求执行总耗时统计
        System.out.println("\n==== Request total cost " + (System.currentTimeMillis() - startTime) + " ms");
    }
    
    /**
     * 模拟客户端发送请求
     * 向服务器发送请求 --> 等会服务器处理 --> 接收服务器返回数据
     */
    private static void connectAndSend(int i) {
    	Socket clientSocket = null;
        try {
        	// 保证所有线程都同时发出请求
            barrier.await();
            
            StringBuilder logBuilder = new StringBuilder();
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
                logBuilder.append(new String(b) + "\n");
                result = input.read(b);
            }
            // 由于服务端向客户端写数据之前Sleep了2s，所以客户端从服务端获取数据需要至少等待2s的时间
            logBuilder.append("==== Request " + i + " cost " + (System.currentTimeMillis() - readTimeStart) + " ms\n");
            
            // 3、关闭输入输出流。必须最后才关闭，如果在【1】之后就关闭输入流，将会导致socket也被关闭
            out.close();
            input.close();
            
            // 4、操作完成关闭socket连接
            clientSocket.close();
            
            // 一次性打印一条线程内的日志，不出现乱序
            System.out.println(logBuilder.toString());
            
            // 每个线程执行完成均减1
            countDownLatch.countDown();
            
        } catch (Exception e) {
            System.out.println("第" + i + "个网络连接发生异常...");
            e.printStackTrace(System.out);
            if (clientSocket != null) {
            	try {
					clientSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace(System.out);
				}
			}
        }
	}
}
