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
 * NIO 客户端
 * @author yingdui_wu
 * @date   2018年4月6日 上午9:59:06
 */
public class NioClient {
	
	public static void main(String[] args) {
		
		testNioServer();
		
	}
	
	/**
     * 测试NIO
     * 
     * @author yingdui_wu
     * @date   2018年3月30日 上午10:07:22
     */
    public static void testNioServer() {
    	
    	class ConnectThread implements Runnable {

            int i;
            
            public ConnectThread(int i) {
                this.i = i;
            }
            
            @Override
            public void run() {
                try {
                	String msg = "==== Hello, I'm " + i + " ====";
                	
                	Selector selector = Selector.open();
                	SocketAddress remote = new InetSocketAddress("127.0.0.1", 9999);
                    SocketChannel clientSocketChannel = SocketChannel.open();
                    // 设置为非阻塞模式，这个方法必须在实际连接之前调用(所以open的时候不能提供服务器地址，否则会自动连接)
                    clientSocketChannel.configureBlocking(false);
                    boolean success = clientSocketChannel.connect(remote);
                    System.out.println("success : " + success);
                    if (success) {
                    	// 添加到selector中
                    	clientSocketChannel.register(selector, SelectionKey.OP_READ);
                    	
                    	// 写数据
                        doWrite(clientSocketChannel, msg);
					}
                    else {
                    	clientSocketChannel.register(selector, SelectionKey.OP_CONNECT);
                    }
                    
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    boolean stop = false;
					while (!stop) {
						selector.select();
						Set<SelectionKey> keys = selector.selectedKeys();
						Iterator<SelectionKey> it = keys.iterator();
						SelectionKey key = null;
						while (it.hasNext()) {
							key = it.next();
							it.remove();
							SocketChannel tmpClientSocketChannel = (SocketChannel) key.channel();
							// OP_CONNECT 两种情况，链接成功或失败这个方法都会返回true
							if (key.isConnectable()) {
								// 由于非阻塞模式，connect只管发起连接请求，finishConnect()方法会阻塞到链接结束并返回是否成功
								// 另外还有一个isConnectionPending()返回的是是否处于正在连接状态(还在三次握手中)
								if (tmpClientSocketChannel.finishConnect()) {
									System.out.println("准备发送数据");
									doWrite(tmpClientSocketChannel, msg);
									// 处理完后必须吧OP_CONNECT关注去掉，改为关注OP_READ
									key.interestOps(SelectionKey.OP_READ);
									// 或 tmpClientSocketChannel.register(selector, SelectionKey.OP_READ);
								} else {
									// 链接失败，进程退出
									System.out.println("链接失败，进程退出");
									System.exit(1);
								}
							}
							if (key.isReadable()) {
								// 读取服务端的响应
								ByteBuffer buffer = ByteBuffer.allocate(1024);
								int readBytes = tmpClientSocketChannel.read(buffer);
								String content = "";
								if (readBytes > 0) {
									buffer.flip();
									byte[] bytes = new byte[buffer.remaining()];
									buffer.get(bytes);
									content += new String(bytes);
									stop = true;
								} else if (readBytes < 0) {
									// 对端链路关闭
									key.cancel();
									tmpClientSocketChannel.close();
								}
								System.out.println(content);
								key.interestOps(SelectionKey.OP_READ);
							}
						}
					}
                    
                } catch (IOException e) {
                    System.out.println("第" + i + "个网络连接发生异常...");
                    e.printStackTrace();
                }
            }
            
			private void doWrite(SocketChannel socketChannel, String data) throws IOException {
				byte[] req = data.getBytes();
				ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
				byteBuffer.put(req);
				byteBuffer.flip();
				socketChannel.write(byteBuffer);
				if (!byteBuffer.hasRemaining()) {
					System.out.println("Send 2 server successfully");
				}
			}
        }
        
        for (int i = 0; i < 2; i++) {
            
            new Thread(new ConnectThread(i)).start();
            
        }
    }
}
