package cn.simplemind.jerry.algorithm.sort;

/**
 * 冒泡排序
 * （一般不用。）
 * 将序列中所有元素两两比较，将最大的放在最后面。
 * 将剩余序列中所有元素两两比较，将最大的放在最后面。
 * 重复第二步，直到只剩下一个数。
 * 
 * @author wuyingdui
 * @date   2017年5月26日 上午12:57:16
 */
public class BubbleSort {

    public static void sort(int[] a) {
    	int tmp;
    	for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if (a[j] > a[j+1]) {
					tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
				}
			}
		}
    }
}
