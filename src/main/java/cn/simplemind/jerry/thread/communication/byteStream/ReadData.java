package cn.simplemind.jerry.thread.communication.byteStream;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * 
 * @author yingdui_wu
 * @date   2018年2月23日 上午10:53:27
 */
public class ReadData {
    public void readMethod(PipedInputStream input) {
        try {
            System.out.println("read  :");
            byte[] byteArray = new byte[20];
            int readLength = input.read(byteArray); // 每次只读出20个字节
            while (readLength != -1) {
                String newData = new String(byteArray, 0, readLength);
                System.out.println(newData);
                readLength = input.read(byteArray);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
