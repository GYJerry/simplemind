package cn.simplemind.test;

import java.util.Date;

import cn.simplemind.jerry.algorithm.sort.BubbleSort;
import cn.simplemind.jerry.algorithm.sort.HeapSort;
import cn.simplemind.jerry.algorithm.sort.InsertSort;
import cn.simplemind.jerry.algorithm.sort.MergeSort;
import cn.simplemind.jerry.algorithm.sort.QuickSort;
import cn.simplemind.jerry.algorithm.sort.RadixSort;
import cn.simplemind.jerry.algorithm.sort.SelectSort;
import cn.simplemind.jerry.algorithm.sort.SheelSort;
import cn.simplemind.jerry.util.number.NumberUtil;

public class SortTest {

	public static void main(String[] args) {
		sortTest();
	}
	
	private static void sortTest() {
    	int[] arr;
    	Date startTime;
    	Date endTime;
    	int numCnt = 20000;
    	int seed = 10;
    	int maxNum = 1000000000;
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	InsertSort.sort(arr);
        endTime = new Date();      
        System.out.println("InsertSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
        SheelSort.sort(arr);
        endTime = new Date();      
        System.out.println("SheelSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	SelectSort.sort(arr);
        endTime = new Date();      
        System.out.println("SelectSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	HeapSort.sort(arr);
        endTime = new Date();      
        System.out.println("HeapSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	BubbleSort.sort(arr);
        endTime = new Date();      
        System.out.println("BubbleSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	QuickSort.sort(arr);
        endTime = new Date();      
        System.out.println("QuickSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	MergeSort.sort(arr);
        endTime = new Date();      
        System.out.println("MergeSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
        
        arr = NumberUtil.generateRandomIntArray(numCnt, seed, maxNum);
    	startTime = new Date();
    	RadixSort.sort(arr);
        endTime = new Date();      
        System.out.println("RadixSort time cost : " + (endTime.getTime() - startTime.getTime()) + "ms");
    }
	
	private static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i != 0 && i % 10 == 0) {
				System.out.println(arr[i] + "  ");
			} else {
				System.out.print(arr[i] + "  ");
			}        	
		}
		System.out.println();
	}

}
