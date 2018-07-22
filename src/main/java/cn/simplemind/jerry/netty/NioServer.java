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
 * @date 2018年3月24日 上午11:25:48
 */
public class NioServer {
	
    public static void serve(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		serverSocket.bind(address); 
		Selector selector = Selector.open(); 
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); 
		final ByteBuffer msg = ByteBuffer.wrap("Hi!I am message from server\r\n".getBytes());
		System.out.println("Nio server start...");
		for (;;) {
			try {
				selector.select();
			} catch (IOException ex) {
				ex.printStackTrace();
				break;
			}
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				try {
					if (key.isAcceptable()) {
						ServerSocketChannel tmpServerSocketChannel = (ServerSocketChannel) key.channel();
						SocketChannel tmpClientChannel = tmpServerSocketChannel.accept();
						tmpClientChannel.configureBlocking(false);
						tmpClientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
						System.out.println("Accepted connection from " + tmpClientChannel);
					}
					if (key.isReadable()) {
						SocketChannel tmpClientChannel = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(32);
						int result = tmpClientChannel.read(buffer);
						buffer.flip();
						while (result > 0) {
							while (buffer.hasRemaining()) {
								System.out.print((char) buffer.get());
							}
							System.out.println();
							result = tmpClientChannel.read(buffer);
						}
					}
					if (key.isWritable()) {
						SocketChannel tmpClientChannel = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer) key.attachment();
						while (buffer.hasRemaining()) {
							if (tmpClientChannel.write(buffer) == 0) {
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
					} catch (IOException cex) {
						// 在关闭时忽略
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		serve(9999);
	}
}
