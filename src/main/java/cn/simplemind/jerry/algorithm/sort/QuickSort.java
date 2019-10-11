package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序 （要求时间最快时。） 选择第一个数为p，小于p的数放在左边，大于p的数放在右边。 递归的将p左边和右边的数都按照第一步进行，直到不能递归。
 * 
 * @author wuyingdui
 * @date 2017年5月26日 上午1:00:29
 */
public class QuickSort {

	public static void main(String[] args){
		int[] arr = {32,43,23,13,5};
		sort(arr);
		System.out.println(Arrays.toString(arr));
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
				// 循环结束时，
				// 若start～end之间存在大于等于base的值，则i代表从start位置向后第一个大于等于base的值的位置
				// 否则，i为end的值，即start～end之间的值均小于base
				while (arr[i] < base && i < end)
					i++;
				// 循环结束时，
				// 若start～end之间存在小于等于base的值，则j代表从end位置向前第一个小于等于base的值的位置
				// 否则，j为start的值，即start～end之间的值均大于base
				while (arr[j] > base && j > start)
					j--;
				
				// 将i和j位置上的数据相互替换
				if (i <= j) {
					if (i == j) {
						System.out.println(i);
					}
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++; // i向后移动一个位置
					j--; // j向前移动一个位置
				}
				
			} while (i < j); // 若未达到左边都小于base，右边都大于base，继续循环
			
			if (start < j)
				// 递归左边的数据，进行划分
				quickSort(arr, start, j);
			if (end > i)
				// 递归右边的数据，进行划分
				quickSort(arr, i, end);
		}
	}
}
