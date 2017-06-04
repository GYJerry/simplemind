package cn.simplemind.jerry.algorithm.sort;

import java.util.Date;
import java.util.Iterator;

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
        int d = a.length;
        while (d > 0) {
			d = d/2;
			for (int x = 0; x < d; x++) {
				// 进行直接插入排序
				for (int i = x + d; i < a.length; i += d) {
					int insertNum = a[i]; // 要插入的值
					int j = i - d;
					while (j >= 0 && insertNum < a[j]) {
						a[j+d] = a[j];
						j -= d;
					}
					a[j+d] = insertNum;
				}
			}
		}
    }
}
