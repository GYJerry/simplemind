package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

/**
 * 简单选择排序
 * （常用于取序列中最大最小的几个数时。）
 * （如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序。）
 * 遍历整个序列，将最小的数放在最前面。
 * 遍历剩下的序列，将最小的数放在最前面。
 * 重复第二步，直到只剩下一个数。
 * 
 * @author wuyingdui
 * @date   2017年5月26日 上午12:46:35
 */
public class SelectSort {

    public static void sort(int[] a) {
        int length = a.length;
    	for (int i = 0; i < length - 1; i++) { // 从头遍历整个数组，最后剩下一个数时不需要比较
			int compareNum = a[i]; // 当前最小的数值
			int position = i; // 当前最小数值的位置
			for (int j = i + 1; j < length; j++) { // 从起始比较的位置向后遍历
				if (a[j] < compareNum) {
					// 如果当前数值不是最小，则将最小数值替换为遍历到的数值
					compareNum = a[j];
					position = j;
				}
			}
			// 最小值与当前位置值交换位置
			a[position] = a[i];
			a[i] = compareNum;
		}
    }
    
    public static void main(String[] args) {
    	int[] arr = {32, 43, 23, 13, 5};
    	sort(arr);
    	System.out.println(Arrays.toString(arr));
	}
}
