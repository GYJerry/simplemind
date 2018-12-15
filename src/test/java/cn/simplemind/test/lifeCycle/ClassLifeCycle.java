package cn.simplemind.test.lifeCycle;

/**
 * 
 *
 * @author yingdui_wu
 * @date 2018年10月9日 上午8:26:53
 */
class A {
    static int a;// 类变量
    static byte by;
    static char ch;
    static short sh;
    static float fl;
    static long lg;
    static double db;
    static boolean boo;
    String name;
    int id;
    // 静态代码块
    static {
        a = 10;
        System.out.println("这是父类的静态代码块a:" + a);
    }
    // 构造代码块
    {
        id = 11;
        System.out.println("这是父类的构造代码块id:" + id);
    }

    A() {
        System.out.println("这是父类的无参构造函数");
    }

    A(String name) {
        System.out.println("这是父类的name:" + name);
    }
}

class B extends A {
    String name;
    static int b;
    static {
        b = 12;
        System.out.println("这是子类的静态代码块b:" + b);
    }

    B(String name) {
        //super();
        this.name = name;
        System.out.println("这是子类的name:" + name);
    }

    @Override
    public String toString() {
        return "B [name=" + name + ", id=" + id + "]";
    }
}

public class ClassLifeCycle {
    public static void main(String[] args) {
        B b1 = null;
        
        System.out.println("实例化第一个B对象");
        b1 = new B("GG");
        System.out.println(b1);
        
        System.out.println("实例化第二个B对象");
        B b2 = new B("CC");
        System.out.println(b2);
        
    }
}
