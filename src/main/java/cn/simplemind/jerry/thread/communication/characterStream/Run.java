package cn.simplemind.jerry.thread.communication.characterStream;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月24日 上午10:38:28
 */
public class Run {
    public static void main(String[] args) {
        try {
            PipedReader input = new PipedReader();
            PipedWriter output = new PipedWriter();
            // input.connect(output);
            output.connect(input);
            ReadThread threadRead = new ReadThread(input);
            threadRead.start();
            Thread.sleep(2000);
            WriteThread threadWrite = new WriteThread(output);
            threadWrite.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
