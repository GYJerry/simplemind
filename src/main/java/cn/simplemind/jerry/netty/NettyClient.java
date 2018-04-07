package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author yingdui_wu
 * @date   2018年4月6日 下午2:12:47
 */
public class NettyClient {
	public static void main(String[] args) {
	
		testNettyServer();
		
	}
	
	/**
	 * 测试Netty
	 * 
	 * @author yingdui_wu
	 * @date   2018年4月6日 下午2:14:09
	 */
	public static void testNettyServer() {
		class ConnectThread implements Runnable {

            int i;
            
            public ConnectThread(int i) {
                this.i = i;
            }
            
            @Override
            public void run() {
                try {
                	String msg = "==== Hello, I'm " + i + " ====";
                	
                } catch (Exception e) {
                    System.out.println("第" + i + "个网络连接发生异常...");
                    e.printStackTrace();
                }
            }
        }
        
        for (int i = 0; i < 5; i++) {
            
            new Thread(new ConnectThread(i)).start();
            
        }
	}
}
