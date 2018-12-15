package cn.simplemind.test.singleton;

import cn.simplemind.test.annotation.ThreadSafe;

/**
 * 饿汉模式 -- 线程安全。由于类初始化时即生成对象，如果涉及的工作比较多，并且在整个过程中没有使用到，那么将造成资源的浪费
 *
 * @author yingdui_wu
 * @date   2018年10月20日 上午11:32:11
 */
@ThreadSafe
public class SecondSingle {
    // 私有构造函数，只有构造函数私有了，才不可以使用new创建对象
    private SecondSingle() {
        
    }
    
    // 单例对象
    private static SecondSingle instance = new SecondSingle();
    
    // 静态工厂方法
    public static SecondSingle getInstance() {
        return instance;
    }
}
