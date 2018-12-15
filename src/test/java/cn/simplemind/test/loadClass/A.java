package cn.simplemind.test.loadClass;

/**
 * 
 *
 * @author yingdui_wu
 * @date   2018年10月23日 上午10:59:45
 */
public class A {
    public static int a = 10;
    
    static {
        int a = 20;
        System.out.println("静态代码块");
    }
    
    A() {
        System.out.println("构造函数");
    }
}
