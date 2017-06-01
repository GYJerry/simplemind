package cn.simplemind.jerry.util.number;

import java.util.Random;

/**
 * 数字相关工具类
 * @author wuyingdui
 * @date   2017年5月27日 上午12:33:35
 */
public class NumberUtil {
    
    /**
     * 生成随机数数组
     * 
     * @author wuyingdui
     * @date   2017年5月27日 上午12:37:56
     * @param  length  数组长度
     * @param  seed    使用Random类相关的种子，指定非0值，每次将返回相同的数组
     * @param  maxNum  最大值（正数），指定非0值，则生成0到该值之间的随机数
     * @return
     */
    public static int[] generateRandomIntArray(int length, int seed, int maxNum) {
        int[] arr = new int[length];
        
        Random random = null;
        if (seed == 0) {
            random = new Random();
        } else {
            random = new Random(seed);
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (maxNum <= 0) {
                arr[i] = random.nextInt();
            } else {
                arr[i] = random.nextInt(maxNum);
            }
            System.out.println(arr[i]);
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        generateRandomIntArray(20, 0, 0);
    }
    
}
