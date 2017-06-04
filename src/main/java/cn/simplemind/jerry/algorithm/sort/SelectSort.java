package cn.simplemind.jerry.algorithm.sort;

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
    	for (int i = 0; i < length; i++) {
			int tmpNum = a[i];
			int position = i;
			for (int j = i + 1; j < length; j++) {
				if (a[j] < tmpNum) {
					tmpNum = a[j];
					position = j;
				}
			}
			// 最小值与当前位置值交换位置
			a[position] = a[i];
			a[i] = tmpNum;
		}
    }
}
