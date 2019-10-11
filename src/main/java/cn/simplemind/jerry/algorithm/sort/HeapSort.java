package cn.simplemind.jerry.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序 （对简单选择排序的优化。） 将序列构建成大顶堆。 将根节点与最后一个节点交换，然后断开最后一个节点。 重复第一、二步，直到所有节点断开。
 * 
 * @author wuyingdui
 * @date 2017年5月26日 上午12:53:21
 */
public class HeapSort {

	public static void sort(int[] a) {
		int length = a.length;
		// 循环建堆
		for (int i = 0; i < length - 1; i++) {
			// 建堆
			buildMaxHeap(a, length - 1 - i);
			// 交换堆顶和最后一个元素
			swap(a, 0, length - 1 - i);
		}
	}

	private static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	// 对data数组从0到lastIndex建大顶堆
	private static void buildMaxHeap(int[] data, int lastIndex) {
		// 从lastIndex处节点（最后一个节点）的父节点开始
		// (lastIndex - 1) / 2 即为最后一个节点的父节点
		// 需要依次判断节点 (lastIndex - 1) / 2 ～ 0 所有节点对应的子树是否满足大顶堆的要求
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k保存正在判断的节点
			int k = i;
			// 如果当前k节点的子节点存在
			// k * 2 + 1 为k的左子节点
			// k * 2 + 2 为k的右子节点
			while (k * 2 + 1 <= lastIndex) {
				// k节点的左子节点的索引
				int biggerIndex = k * 2 + 1;
				// 如果biggerIndex小于lastIndex，说明biggerIndex+1代表的k节点的右子节点存在
				if (biggerIndex < lastIndex) {
					// 若果右子节点的值较大
					if (data[biggerIndex] < data[biggerIndex + 1]) {
						// biggerIndex总是记录较大子节点的索引
						biggerIndex++;
					}
				}
				// 如果k节点的值小于其较大的子节点的值
				if (data[k] < data[biggerIndex]) {
					// 交换他们
					swap(data, k, biggerIndex);
					// 将biggerIndex赋予k，开始while循环的下一次循环
					// 保证当前k节点的子树中根节点的值大于其左右子节点的值
					k = biggerIndex;
				} else {
					// k节点对应的子树，根节点已经是最大值
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
    	int[] arr = {32, 43, 23, 13, 5};
    	sort(arr);
    	System.out.println(Arrays.toString(arr));
	}
}
