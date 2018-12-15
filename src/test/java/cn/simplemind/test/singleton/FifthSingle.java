package cn.simplemind.test.singleton;

import cn.simplemind.test.annotation.ThreadSafe;

/**
 * 懒汉模式 -- 加锁，保证线程安全，加锁代码块。双重同步锁单例模式
 *
 * @author yingdui_wu
 * @date   2018年10月20日 上午11:32:11
 */
@ThreadSafe
public class FifthSingle {
    // 私有构造函数，只有构造函数私有了，才不可以使用new创建对象
    private FifthSingle() {
        
    }
    
    // 单例对象  volatile + 双重检测机制 -> 限制指令重排
    private volatile static FifthSingle instance = null;
    
    // 生成新建对象的过程
    // a、memory = allocate() 分配对象内存空间
    // b、初始化对象
    // c、instance = memory 设置instance指向刚分配的内存
    
    // 静态工厂方法
    public static FifthSingle getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (FifthSingle.class) { // 同步锁
                if (instance == null) {
                    instance = new FifthSingle();
                }
            }
        }
        
        return instance;
    }
}
