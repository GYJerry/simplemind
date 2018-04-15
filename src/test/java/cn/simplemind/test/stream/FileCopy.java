package cn.simplemind.test.stream;

import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
//		try {
//			FileCopyUtil.fileCopy("/Users/jerry/Documents/testFIle/source.rtf", "/Users/jerry/Documents/testFIle/target.rtf");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		try {
			FileCopyUtil.fileCopyNIO("/Users/jerry/Documents/testFIle/source.rtf", "/Users/jerry/Documents/testFIle/target2.rtf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
