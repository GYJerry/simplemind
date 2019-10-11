package cn.simplemind.jerry.algorithm;

import java.util.Arrays;

/**
 * 
 *
 * @author yingdui_wu
 * @date 2019年09月21日 15:56
 */
public class MaxHeap {
	public static void main(String args[]) {

		int data[] = { 32, 43, 23, 13, 5 }; // 原始数组内容
		System.out.println("原始数组：" + Arrays.toString(data));

		buildMaxHeap(data); // 建立大顶堆
		System.out.println("大顶堆内容：" + Arrays.toString(data));
	}

	public static void buildMaxHeap(int[] data) {
		int lastIndex = data.length - 1;
		// 从lastIndex处节点（最后一个节点）的父节点开始
		// (lastIndex - 1) / 2 即为最后一个节点的父节点
		// 需要依次判断节点 (lastIndex - 1) / 2 ～ 0 所有节点对应的子树是否满足大顶堆的要求
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			adjustDownToUp(data, i);
		}
	}
	
	

	private static void adjustDownToUp(int[] data, int k) {
		int lastIndex = data.length -1;
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
				int tmp = data[k];
				data[k] = data[biggerIndex];
				data[biggerIndex] = tmp;
				// 将biggerIndex赋予k，开始while循环的下一次循环
				// 保证当前k节点的子树中根节点的值大于其左右子节点的值
				k = biggerIndex;
			}
			else {
				// k节点对应的子树，根节点已经是最大值
				break;
			}
		}
	}
}
