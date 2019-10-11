package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO 服务端
 * @author yingdui_wu
 * @date   2018年3月24日 上午11:24:46
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        serve(9999);
    }
    
    public static void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            System.out.println("Bio server start...\n");
            ExecutorService threadPool = null;
            
            for (int i = 0; ; i++) {
                final Socket clientSocket = socket.accept();
                StringBuilder logBuilder = new StringBuilder();
                logBuilder.append("Accepted connection from " + clientSocket + "\n");

                /**
                 * 使用多线程方式处理
                 */
                if (threadPool == null) {
                	// 初始化线程池，仅开2条线程并发执行客户端请求
                	threadPool = Executors.newFixedThreadPool(2);
				}
                int cnt = i;
                threadPool.execute(() -> {
                	handlerRequest(clientSocket, cnt, logBuilder);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            socket.close();
        }
    }
    
    /**
     * 模拟服务器处理请求
     * 接收客户端请求 --> 处理数据 --> 向客户端返回处理结果
     */
    private static void handlerRequest(Socket clientSocket, int cnt, StringBuilder logBuilder) {
        InputStream input;
        OutputStream output;
        try {
        	String threadName = Thread.currentThread().getName();
        	logBuilder.append(threadName + "\n");
            logBuilder.append("-- " + cnt + " -- read start time: " + System.currentTimeMillis() + "\n");
            // 1、从客户端获取数据
            input = clientSocket.getInputStream();
            byte[] b = new byte[100];
            input.read(b);
            logBuilder.append("-- " + cnt + " -- " + new String(b) + "\n");
            logBuilder.append("-- " + cnt + " -- read end time: " + System.currentTimeMillis() + "\n");
            
            // 2、暂停2s，模拟对数据的处理操作
            Thread.sleep(2000);

            // 3、写数据回客户端
            output = clientSocket.getOutputStream();
            output.write(("==== Hello, I'm " + threadName + "/" + cnt + " from server ====").getBytes("UTF-8"));
            output.flush();
            
            // 4、关闭输入输出流。必须最后才关闭，如果在【1】之后就关闭输入流，将会导致socket也被关闭
            input.close();
            output.close();
            
            // 5、关闭socket连接
            clientSocket.close();
            
            // 一次性打印一条线程内的日志，不出现乱序
            System.out.println(logBuilder.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
