package cn.simplemind.jerry.thread.communication.byteStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月23日 上午11:04:59
 */
public class Run {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            // 使用代码inputStream.connect(outputStream)或outputStream.connect(inputStream)
            // 的作用使两个Stream之间产生通信链接，这样才可以将数据进行输出与输入
            outputStream.connect(inputStream); // 或inputStream.connect(outputStream);
            ReadThread threadRead = new ReadThread(readData, inputStream);
            threadRead.start();
            Thread.sleep(2000);
            WriteThread threadWrite = new WriteThread(writeData, outputStream);
            threadWrite.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
