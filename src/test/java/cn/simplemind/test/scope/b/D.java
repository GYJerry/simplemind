package cn.simplemind.test.scope.b;

import cn.simplemind.test.scope.a.A;

/**
 *
 * @author yingdui_wu
 * @date   2018年7月17日 下午7:56:34
 */
public class D {
    
    public static void main(String[] args) {
        A a = new A();
        // a.field;  // protected 的字段在其他包无法访问
        // a.defaultScope;  // default 的字段在其他包无法访问
    }
    
}
