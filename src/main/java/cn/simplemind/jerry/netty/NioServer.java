package cn.simplemind.jerry.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 服务端
 * @author yingdui_wu
 * @date   2018年3月24日 上午11:25:48
 */
public class NioServer {
	
	public static void main(String[] args) throws IOException {
		serve(9999);
	}
	
    public static void serve(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 配置为非阻塞
		serverSocketChannel.configureBlocking(false); 
		
		// 服务端socket
		ServerSocket socket = serverSocketChannel.socket();
		// 将ServerSocket绑定到指定的 ip+端口 上
		InetSocketAddress address = new InetSocketAddress(port);
		socket.bind(address); 
		
		// 打开一个多路复用器
		Selector selector = Selector.open();
		// 将服务器套接字的channel注册到selector上，指定关注该channel的请求接收
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); 
		final ByteBuffer msg = ByteBuffer.wrap("Hi!I am message from server\r\n".getBytes());
		ServerSocketChannel acceptServerSocketChannel = null;
		SocketChannel clientSocketChannel = null;
		// 分配读缓冲区
		ByteBuffer readBuff = ByteBuffer.allocate(1024);
		// 分配写缓冲区
        ByteBuffer writeBuff = ByteBuffer.allocate(128);
		
		System.out.println("Nio server start...");
		for (;;) {
			try {
				// 获取通道已准备好进行I/O操作的key
				selector.select();
			} catch (IOException ex) {
				ex.printStackTrace(System.out);
				break;
			}
			// 获取select出来的key
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				// 对应key取出来后，从集合中删除
				iterator.remove();
				try {
					if (key.isAcceptable()) {
						// 接收到客户端请求
						acceptServerSocketChannel = (ServerSocketChannel) key.channel();
						clientSocketChannel = acceptServerSocketChannel.accept();
						clientSocketChannel.configureBlocking(false);
						// 将客户端套接字的channel注册到selector上，指定关注该channel的写、读操作
						clientSocketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
						System.out.println("Accepted connection from " + clientSocketChannel);
					}
					if (key.isReadable()) {
						// 客户端channel进入可读状态
						clientSocketChannel = (SocketChannel) key.channel();
						readBuff.clear();
						// 将客户端channel中的数据读入到缓冲区中
						int result = clientSocketChannel.read(readBuff);
						readBuff.flip();
						while (result > 0) {
							while (readBuff.hasRemaining()) {
								System.out.print((char) readBuff.get());
							}
							System.out.println();
							result = clientSocketChannel.read(readBuff);
						}
					}
					if (key.isWritable()) {
						// 客户端channel进入可写状态
						clientSocketChannel = (SocketChannel) key.channel();
						writeBuff = (ByteBuffer) key.attachment();
						while (writeBuff.hasRemaining()) {
							if (clientSocketChannel.write(writeBuff) == 0) {
								break;
							}
						}
						System.out.println("send 2 client successfully");
						// 写完就把状态关注去掉，否则会一直触发写事件(改变自身关注事件)
                        key.interestOps(SelectionKey.OP_READ);
					}

				} catch (IOException ex) {
					key.cancel();
					try {
						key.channel().close();
					} catch (IOException exc) {
						exc.printStackTrace(System.out);
					}
				}
			}
		}
	}
}
