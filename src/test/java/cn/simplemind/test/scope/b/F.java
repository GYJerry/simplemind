package cn.simplemind.test.scope.b;

import cn.simplemind.test.scope.a.A;

/**
 * 
 * @author yingdui_wu
 * @date   2018年7月17日 下午8:02:09
 */
public class F extends A {

    public static void main(String[] args) {
        F f = new F();
        f.field = "test";
        System.out.println(f.field);
        
        // f.defaultScope;  // default 的字段在其他包子类中也无法访问
    }
    
}
