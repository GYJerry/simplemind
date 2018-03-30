package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO
 * @author yingdui_wu
 * @date   2018年3月24日 上午11:24:46
 */
public class PlainBioServer {
    public static void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);     //1
        try {
            System.out.println("Bio server start...");
            for (;;) {
                final Socket clientSocket = socket.accept();    //2
                System.out.println();
                System.out.println("Accepted connection from " + clientSocket);

                /**
                 * 使用单线程方式处理
                 */
                InputStream input;
                try {
                    System.out.println("read start time: " + System.currentTimeMillis());
                    input = clientSocket.getInputStream();
                    byte[] b = new byte[100];
                    int result = input.read(b);
                    int k = 1;
                    while(result > 0) {
                        if (k == 1) {
                            // 仅打印一次字符串
                            System.out.println(new String(b));
                        }
                        result = input.read(b);
                        k++;
                    }
                    
                    input.close();
                    System.out.println("read end time: " + System.currentTimeMillis());
                    
                    clientSocket.close();                //5
                    
                    long processStart = System.currentTimeMillis();
                    for (int h = 0; h < 2100000000; h++) {
                        // 做一个无效的循环，模拟对数据的处理操作
                    }
                    System.out.println("data process cost: " + (System.currentTimeMillis()-processStart));

                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        clientSocket.close();
                    } catch (IOException ex) {
                        // ignore on close
                    }
                }
                
                /**
                 * 使用多线程方式处理
                 */
                /*new Thread(new Runnable() {                        //3
                    @Override
                    public void run() {
                        
                        InputStream input;
                        try {
                            System.out.println("read start time: " + System.currentTimeMillis());
                            input = clientSocket.getInputStream();
                            byte[] b = new byte[100];
                            int result = input.read(b);
                            int k = 1;
                            while(result > 0) {
                                if (k == 1) {
                                    // 仅打印一次字符串
                                    System.out.println(new String(b));
                                }
                                result = input.read(b);
                                k++;
                            }
                            
                            input.close();
                            System.out.println("read end time: " + System.currentTimeMillis());
                            clientSocket.close();                //5
                            
                            long processStart = System.currentTimeMillis();
                            for (int h = 0; h < 2100000000; h++) {
                                // 做一个无效的循环，模拟对数据的处理操作
                            }
                            System.out.println("data process cost: " + (System.currentTimeMillis()-processStart));
        
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            } catch (IOException ex) {
                                // ignore on close
                            }
                        }
                    }
                }).start(); */                                       //6
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        serve(9999);
    }
}
