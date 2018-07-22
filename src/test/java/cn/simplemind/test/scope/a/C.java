package cn.simplemind.test.scope.a;

/**
 *
 * @author yingdui_wu
 * @date   2018年7月17日 下午7:53:17
 */
public class C extends A {
    
    public static void main(String[] args) {
        C c = new C();
        c.field = "test";
        System.out.println(c.field);
        
        c.defaultScope = "default";
        System.out.println(c.defaultScope);
    }
    
}
