package cn.simplemind.test.singleton;

import cn.simplemind.test.annotation.NotRecommend;
import cn.simplemind.test.annotation.ThreadSafe;

/**
 * 懒汉模式 -- 加锁，保证线程安全。但是synchronized，锁住整个方法，每次调用该方法时都会加锁，性能开销大。
 *
 * @author yingdui_wu
 * @date   2018年10月20日 上午11:32:11
 */
@ThreadSafe
@NotRecommend
public class ThirdSingle {
    // 私有构造函数，只有构造函数私有了，才不可以使用new创建对象
    private ThirdSingle() {
        
    }
    
    // 单例对象
    private static ThirdSingle instance = null;
    
    // 静态工厂方法
    public static synchronized ThirdSingle getInstance() {
        if (instance == null) {
            instance = new ThirdSingle();
        }
        
        return instance;
    }
}
