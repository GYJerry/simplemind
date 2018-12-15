package cn.simplemind.test.loadClass;

/**
 * 
 *
 * @author yingdui_wu
 * @date   2018年10月23日 上午10:59:37
 */
public class LoadClass {
    
public static void main(String[] args) throws ClassNotFoundException {
    //Class clazz = A.class;
    //Class clazz = Class.forName("cn.simplemind.test.loadClass.A");
    Class clazz = Thread.currentThread().getContextClassLoader().loadClass("cn.simplemind.test.loadClass.A");
    
    System.out.println("----------------------------------");
    
    A a = new A();
}
}
