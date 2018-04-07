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
 * NIO
 * @author yingdui_wu
 * @date 2018年3月24日 上午11:25:48
 */
public class NioServer {
	public static void serve(int port) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		ServerSocket serverSocket = serverChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		serverSocket.bind(address); // 1
		Selector selector = Selector.open(); // 2
		serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 3
		final ByteBuffer msg = ByteBuffer.wrap("Hi!I am message from server\r\n".getBytes());
		System.out.println("Nio server start...");
		for (;;) {
			try {
				selector.select(); // 4
			} catch (IOException ex) {
				ex.printStackTrace();
				// handle exception
				break;
			}
			Set<SelectionKey> readyKeys = selector.selectedKeys(); // 5
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				try {
					if (key.isAcceptable()) { // 6
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel clientChannel = server.accept();
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate()); // 7
						System.out.println("Accepted connection from " + clientChannel);
					}
					if (key.isReadable()) {
						SocketChannel clientChannel = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(32);
						int result = clientChannel.read(buffer);
						buffer.flip();
						while (result > 0) {
							while (buffer.hasRemaining()) {
								System.out.print((char) buffer.get());
							}
							System.out.println();
							result = clientChannel.read(buffer);
						}
					}
					if (key.isWritable()) { // 8
						SocketChannel clientChannel = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer) key.attachment();
						while (buffer.hasRemaining()) {
							if (clientChannel.write(buffer) == 0) { // 9
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