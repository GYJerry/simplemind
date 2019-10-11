package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序 （速度仅次于快排，内存少的时候使用，可以进行并行计算的时候使用。） 选择相邻两个数组成一个有序序列。 选择相邻的两个有序序列组成一个有序序列。
 * 重复第二步，直到全部组成一个有序序列。
 * 
 * @author wuyingdui
 * @date 2017年5月26日 上午1:02:05
 */
public class MergeSort {

	/**
	 * @author wuyingdui
	 * @date 2017年5月26日 上午1:02:05
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 32, 43, 23, 13, 5 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] a) {
		int t = 1; // 每个子表元素个数
		while (t < a.length) {
			int s = t; // 本次循环每个子表元素个数
			t = 2 * s; // 本次循环每两个子表元素的总个数，也是下次循环每个子表元素的个数
			int i = 0;
			while (i + t - 1 < a.length) {
				// 归并长度为s的两个相邻子表
				merge(a, i, i + s - 1, i + t - 1);
				i += t; // 接下来要归并的两组元素中第一组元素的起始位置
			}
			// 最后余下两个子表，后者长度小于s
			if (i + s - 1 < a.length - 1) {
				merge(a, i, i + s - 1, a.length - 1);
			}
		}
	}

	/**
	 * @param data 待排序数组
	 * @param p    第一个子表的起始位置
	 * @param q    第一个子表的结束位置，也是第二个子表的起始位置-1
	 * @param r    第二个子表的结束位置
	 */
	private static void merge(int[] data, int p, int q, int r) {
		int[] b = new int[data.length]; // 新的数组，用于存放中间排序数据
		int s = p;		// 第一个子表的起始位置
		int t = q + 1;	// 第二个子表的起始位置
		int k = p;
		while (s <= q && t <= r) {
			if (data[s] <= data[t]) {
				// 如果第一个子表的数值比较小，将第一个子表的值放到新数组
				b[k] = data[s];
				// 第一个子表移向下一位置
				s++;
			}
			else {
				// 否则将第二个子表的值放到新数组
				b[k] = data[t];
				// 第二个子表移向下一位置
				t++;
			}
			// 新数组移向下一位置
			k++;
		}
		// 由于参与归并的子表元素个数是从1开始的，所以后续归并的每个子表中，元素都是从小到大排序的
		// 可以将任意子表未遍历完的数据直接放到新数组中
		if (s == q + 1) {
			// 此时第一个子表已经遍历完，将第二个子表剩余的数据放到新数组
			while (t <= r) {
				b[k++] = data[t++];
			}
		}
		else {
			// 此时第二个子表已经遍历完，将第一个子表剩余的数据放到新数组
			while (s <= q) {
				b[k++] = data[s++];
			}
		}

		for (int i = p; i <= r; i++) {
			// 将合并后的数据放回原数组
			data[i] = b[i];
		}
	}
}
