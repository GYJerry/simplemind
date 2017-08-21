package cn.simplemind.jerry.algorithm.sort;


/**
 * 直接插入排序
 * （经常碰到这样一类排序问题：把新的数据插入到已经排好的数据列中。）
 * 1、将第一个数和第二个数排序，然后构成一个有序序列
 * 2、将第三个数插入进去，构成一个新的有序序列。
 * 3、对第四个数、第五个数……直到最后一个数，重复第二步
 * 
 * @author wuyingdui
 * @date   2017年5月26日 上午12:26:29
 */
public class InsertSort {
    
    public static void sort(int[] a) {
        int insertNum;
        for (int i = 1; i < a.length; i++) {
			insertNum = a[i];
			int j = i-1;
			while (j >= 0 && a[j] > insertNum) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = insertNum;
		}
    }
}
