package cn.simplemind.test.singleton;

import cn.simplemind.test.annotation.ThreadUnsafe;

/**
 * 懒汉模式 -- 线程不安全的，单线程请况下无问题
 *
 * @author yingdui_wu
 * @date   2018年10月20日 上午11:32:11
 */
@ThreadUnsafe
public class FirstSingle {
    // 私有构造函数，只有构造函数私有了，才不可以使用new创建对象
    private FirstSingle() {
        
    }
    
    // 单例对象
    private static FirstSingle instance = null;
    
    // 静态工厂方法
    public static FirstSingle getInstance() {
        if (instance == null) {
            instance = new FirstSingle();
        }
        
        return instance;
    }
}
