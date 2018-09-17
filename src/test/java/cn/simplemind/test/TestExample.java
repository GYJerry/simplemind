package cn.simplemind.test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestExample {
	public static void main(String[] args) {
		AtomicInteger count = new AtomicInteger(0);
		count.incrementAndGet();
	}
}
