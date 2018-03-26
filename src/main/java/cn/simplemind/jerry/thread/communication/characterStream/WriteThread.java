package cn.simplemind.jerry.thread.communication.characterStream;

import java.io.IOException;
import java.io.PipedWriter;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月23日 上午10:57:09
 */
public class WriteThread extends Thread {
    private PipedWriter out;
    
    public WriteThread(PipedWriter out) {
        super();
        this.out = out;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("write :");
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
