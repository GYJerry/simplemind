package cn.simplemind.test.singleton;

import cn.simplemind.test.annotation.ThreadUnsafe;

/**
 * 懒汉模式 -- 加锁，保证线程安全，加锁代码块。双重同步锁单例模式
 *
 * @author yingdui_wu
 * @date   2018年10月20日 上午11:32:11
 */
@ThreadUnsafe
public class ForthSingle {
    // 私有构造函数，只有构造函数私有了，才不可以使用new创建对象
    private ForthSingle() {
        
    }
    
    // 单例对象
    private static ForthSingle instance = null;
    
    // 生成新建对象的过程
    // a、memory = allocate() 分配对象内存空间
    // b、初始化对象
    // c、instance = memory 设置instance指向刚分配的内存
    
    // 如果JVM和cpu优化，发生了指令重排序，就会出现执行顺序由 a->b->c 变成了 a->c->b
    
    // 静态工厂方法
    public static ForthSingle getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (ForthSingle.class) { // 同步锁
                if (instance == null) {
                    instance = new ForthSingle();
                }
            }
        } 
        // 当线程1以a->c->b的顺序执行到c，这个时候，如果线程2执行到这个if判断时，
        // 发现instance非空，就会直接return，线程2就会拿到未初始化完成的对象，是线程不安全的。
        
        return instance;
    }
}
