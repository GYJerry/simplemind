package cn.simplemind.jerry.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.fastinfoset.QualifiedName;

import cn.simplemind.jerry.util.number.NumberUtil;

/**
 * 基数排序 （用于大量数，很长的数进行排序时。） 将所有的数的个位数取出，按照个位数进行排序，构成一个序列。
 * 将新构成的所有的数的十位数取出，按照十位数进行排序，构成一个序列。
 * 
 * @author wuyingdui
 * @date 2017年5月26日 上午1:05:16
 */
public class RadixSort {

	/**
	 * @author wuyingdui
	 * @date 2017年5月26日 上午1:05:16
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = NumberUtil.generateRandomIntArray(100, 10, 1000);
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] a) {
		// 首先确定排序的趟数
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		int time = 0;
		// 判断位数
		while (max > 0) {
			max /= 10;
			time++;
		}

		// 建立10个队列
		List<List> queue = new ArrayList<List>();
		for (int i = 0; i < 10; i++) {
			List<Integer> subQueue = new ArrayList<Integer>();
			queue.add(subQueue);
		}

		// 进行time次分配和收集
		for (int i = 0; i < time; i++) {
			//分配数组元素
			for (int j = 0; j < a.length; j++) {
				//得到数字的第i+1位数
				int x = a[j] % (int)Math.pow(10, i+1) / (int)Math.pow(10, i);
				queue.get(x).add(a[j]);
			}
			
			// 元素计数器
			int count = 0;
			for (int k = 0; k < 10; k++) {
				while (queue.get(k).size() > 0) {
					a[count++] = (Integer) queue.get(k).get(0);
					queue.get(k).remove(0);
				}
			}
		}
	}
}
