package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

import cn.simplemind.jerry.util.number.NumberUtil;

/**
 * 快速排序 （要求时间最快时。） 选择第一个数为p，小于p的数放在左边，大于p的数放在右边。 递归的将p左边和右边的数都按照第一步进行，直到不能递归。
 * 
 * @author wuyingdui
 * @date 2017年5月26日 上午1:00:29
 */
public class QuickSort {

	public static void main(String[] args){
		int[] arr = {32,43,23,13,5};
		System.out.println(Arrays.toString(arr));
		sort(arr);
	}
	
	public static void sort(int[] a) {
		int start = 0;
		int end = a.length - 1;
		quickSort(a, start, end);
	}

	private static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int base = arr[start]; // 选定的基准值（第一个数值作为基准值）
			int temp; // 记录临时中间值
			int i = start, j = end;
			do {
				while (arr[i] < base && i < end)
					i++;
				while (arr[j] > base && j > start)
					j--;
				if (i <= j) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
				//System.out.println(Arrays.toString(arr));
			} while (i <= j);
			if (start < j)
				quickSort(arr, start, j);
			if (end > i)
				quickSort(arr, i, end);
		}
	}
}
