package cn.simplemind.jerry.thread.communication.byteStream;

import java.io.PipedOutputStream;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月23日 上午10:57:09
 */
public class WriteThread extends Thread {
    private WriteData write;
    private PipedOutputStream out;
    
    public WriteThread(WriteData write, PipedOutputStream out) {
        super();
        this.write = write;
        this.out = out;
    }
    
    @Override
    public void run() {
        write.writeMethod(out);
    }
}
