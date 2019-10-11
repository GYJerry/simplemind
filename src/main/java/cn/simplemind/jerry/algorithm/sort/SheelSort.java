package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;
import java.util.Date;

import cn.simplemind.jerry.util.number.NumberUtil;

/**
 * 希尔排序
 * （对于直接插入排序问题，数据量巨大时。）
 * 将数的个数设为n，取奇数k=n/2，将下标差值为k的书分为一组，构成有序序列。
 * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
 * 重复第二步，直到k=1执行简单插入排序。
 * 
 * @author wuyingdui
 * @date   2017年5月26日 上午12:39:25
 */
public class SheelSort {
    
    public static void sort(int[] a) {
        int k = a.length / 2;
        while (k > 0) {
			for (int x = 0; x < k; x++) {
				// 进行直接插入排序
				for (int i = x + k; i < a.length; i += k) {
					int insertNum = a[i]; // 要插入的值
					int j = i - k;
					while (j >= 0 && insertNum < a[j]) {
						a[j+k] = a[j];
						j -= k;
					}
					a[j+k] = insertNum;
				}
			}
			k = k/2;
		}
    }
    
    public static void main(String[] args) {
    	int[] arr = {32, 43, 23, 13, 5};
    	sort(arr);
    	System.out.println(Arrays.toString(arr));
	}
}
