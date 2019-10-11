package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序 （一般不用。） 将序列中所有元素两两比较，将最大的放在最后面。 将剩余序列中所有元素两两比较，将最大的放在最后面。
 * 重复第二步，直到只剩下一个数。
 * 
 * @author wuyingdui
 * @date 2017年5月26日 上午12:57:16
 */
public class BubbleSort {

	public static void sort(int[] a) {
		int tmp;
		for (int i = 0; i < a.length; i++) { // 从头遍历整个数组
			for (int j = 0; j < a.length - 1 - i; j++) { // 每次只比较0～(length-1-i)位置的数据
				if (a[j] > a[j + 1]) {
					// 如果当前值大于后一个值，则将当前值与后一个值交换位置
					tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 32, 43, 23, 13, 5 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
