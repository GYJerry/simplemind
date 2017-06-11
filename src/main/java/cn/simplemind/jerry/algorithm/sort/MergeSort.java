package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * （速度仅次于快排，内存少的时候使用，可以进行并行计算的时候使用。）
 * 选择相邻两个数组成一个有序序列。
 * 选择相邻的两个有序序列组成一个有序序列。
 * 重复第二步，直到全部组成一个有序序列。
 * 
 * @author wuyingdui
 * @date   2017年5月26日 上午1:02:05
 */
public class MergeSort {

    /**
     * @author wuyingdui
     * @date   2017年5月26日 上午1:02:05
     * @param  args
     */
    public static void main(String[] args) {
    	int[] arr = {32,43,23,13,5};
		sort(arr);
		System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] a) {
        int t = 1; //每组元素个数
        while (t < a.length) {
        	int s = t;// 本次循环每组元素个数 
        	t = 2*s;
        	int i = 0;
        	while (i+t-1 < a.length) {
        		// 归并s长度的两个相邻子表
        		merge(a, i, i+s-1, i+t-1);
				i += t;
			}
        	// 最后余下两个子表，后者长度小于s
        	if (i+s-1 < a.length-1) {
        		merge(a, i, i+s-1, a.length-1);
			}
		}
    }
    
    private static void merge(int[] data, int p, int q, int r) {
        int[] b = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
			if (data[s] <= data[t]) {
				b[k] = data[s];
				s++;
			} else {
				b[k] = data[t];
				t++;
			}
			k++;
		}
        if (s == q+1) {
			b[k++] = data[t++];
		} else {
			b[k++] = data[s++];
		}
        
        for (int i = p; i <= r; i++) {
			data[i] = b[i];
		}
    }
}
