package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * BIO 服务端
 * @author yingdui_wu
 * @date   2018年3月24日 上午11:24:46
 */
public class BioServer {
    
    private static Object logPrintLock = new Object(); 
    
    public static void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            System.out.println("Bio server start...");
            System.out.println();
            
            for (int i = 0; ; i++) {
                final Socket clientSocket = socket.accept();
                List<String> logs = new ArrayList<>();
                logs.add("Accepted connection from " + clientSocket);

                /**
                 * 使用单线程方式处理
                 */
                handlerRequest(clientSocket, 0, logs);
                
                /**
                 * 使用多线程方式处理
                 */
                /*int cnt = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handlerRequest(clientSocket, cnt, logs);
                    }
                }).start();*/
            }
        } catch (IOException e) {
            e.printStackTrace();
            socket.close();
        }
    }
    
    private static void handlerRequest(Socket clientSocket, int cnt, List<String> logs) {
        InputStream input;
        OutputStream output;
        try {
            logs.add("--" + cnt + " -- read start time: " + System.currentTimeMillis());
            // 1、从客户端获取数据
            input = clientSocket.getInputStream();
            byte[] b = new byte[100];
            input.read(b);
            logs.add("--" + cnt + " -- " + new String(b));
            logs.add("--" + cnt + " -- read end time: " + System.currentTimeMillis());
            
            // 2、暂停2s，模拟对数据的处理操作
            Thread.sleep(2000);

            // 3、写数据回客户端
            output = clientSocket.getOutputStream();
            output.write(("==== Hello, I'm " + cnt + " from server ====").getBytes("UTF-8"));
            output.flush();
            
            // 4、关闭输入输出流。必须最后才关闭，如果在【1】之后就关闭输入流，将会导致socket也被关闭
            input.close();
            output.close();
            
            // 5、关闭socket连接
            clientSocket.close();
            
            // 加锁保证每次打印的只是一条线程内的日志，不出现乱序
            synchronized (logPrintLock) {
                for (String log : logs) {
                    System.out.println(log);
                }
                System.out.println();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        serve(9999);
    }
}
