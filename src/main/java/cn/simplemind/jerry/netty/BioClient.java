package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author yingdui_wu
 * @date   2018年3月29日 下午7:31:44
 */
public class BioClient {
    public static void main(String[] args) {
        
        testBioServer();
    	
    }
    
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
                	clientSocket = new Socket("127.0.0.1", 9999);
                    System.out.println("socket"  + i + " timeout " + clientSocket.getSoTimeout());
                    //clientSocket.setSoTimeout(2000);
                    
                    OutputStream out = clientSocket.getOutputStream();
                    
                    /**
                     * 请求方式一
                     */
                    /*try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    out.write(("==== Hello, I'm " + i + " ====").getBytes("UTF-8"));
                    out.flush();*/
                    
                    /**
                     * 请求方式二
                     */
                    out.write(("==== Hello, I'm " + i + " ====").getBytes("UTF-8"));
                    out.flush();
                    for (int j = 0; j < 10000; j++) {
                        try {
                            out.write(j);
                            out.flush();
                        } catch (Exception e) {
                            System.out.println("j = " + j);
                            System.out.println(System.currentTimeMillis());
                            System.out.println(clientSocket.isClosed());
                            System.out.println(clientSocket.isConnected());
                            throw e;
                        }
                    }
                    
                    /**
                     * 请求方式三
                     */
                    /*long generateStrStart = System.currentTimeMillis();
                    String inputStr = "==== Hello, I'm " + i + " ====";
                    for (int j = 0; j < 5000; j++) {
                        inputStr += j;
                    }
                    System.out.println("generate string cost: " + (System.currentTimeMillis() - generateStrStart));
                    out.write(inputStr.getBytes("UTF-8"));
                    out.flush();*/
                    
                    clientSocket.close();
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
        
        for (int i = 0; i < 5; i++) {
            
            new Thread(new ConnectThread(i)).start();
            
        }
    }
}
