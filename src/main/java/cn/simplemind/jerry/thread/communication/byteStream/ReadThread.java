package cn.simplemind.jerry.thread.communication.byteStream;

import java.io.PipedInputStream;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月23日 上午10:56:42
 */
public class ReadThread extends Thread {
    private ReadData read;
    private PipedInputStream input;
    
    public ReadThread(ReadData read, PipedInputStream input) {
        super();
        this.read = read;
        this.input = input;
    }
    
    @Override
    public void run() {
        read.readMethod(input);
    }
}
