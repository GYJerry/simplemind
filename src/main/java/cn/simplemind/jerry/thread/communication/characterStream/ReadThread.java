package cn.simplemind.jerry.thread.communication.characterStream;

import java.io.IOException;
import java.io.PipedReader;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月23日 上午10:56:42
 */
public class ReadThread extends Thread {
    private PipedReader input;
    
    public ReadThread(PipedReader input) {
        super();
        this.input = input;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("read  :");
            char[] charArray = new char[20];
            int readLength = input.read(charArray); // 每次只读出20个字符
            while (readLength != -1) {
                String newData = new String(charArray, 0, readLength);
                System.out.println(newData);
                readLength = input.read(charArray);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
