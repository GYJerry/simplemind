package cn.simplemind.test.scope.a;

/**
 *
 * @author yingdui_wu
 * @date   2018年7月17日 下午7:50:37
 */
public class B {
    
    public static void main(String[] args) {
        A a = new A();
        a.field = "test";
        System.out.println(a.field);
        
        a.defaultScope = "default";
        System.out.println(a.defaultScope);
    }

}
