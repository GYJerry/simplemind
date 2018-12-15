package cn.simplemind.test.singleton;

import cn.simplemind.test.annotation.Recommend;
import cn.simplemind.test.annotation.ThreadSafe;

/**
 * 懒汉模式 -- 通过使用枚举来实现单例
 *
 * @author yingdui_wu
 * @date   2018年10月20日 上午11:32:11
 */
@ThreadSafe
@Recommend
public class SixthSingle {
    // 私有构造函数，只有构造函数私有了，才不可以使用new创建对象
    private SixthSingle() {
        
    }
    
    // 静态工厂方法
    public static SixthSingle getInstance() {
        return Singlton.INSTANCE.getInstance();
    }
    
    private enum Singlton {
        INSTANCE;
        
        private SixthSingle singleton;
        
        // JVM保证该方法绝对只调用一次（针对每个枚举值只调用一次）
        Singlton() {
            singleton = new SixthSingle();
        }
        
        public SixthSingle getInstance() {
            return singleton;
        }
    }
    
    private enum Test {
        ONE,TWO;
        
        Test() {
            System.out.println(this.hashCode());
        }
    }
    
    public static void main(String[] args) {
        Test one = Test.ONE;
        Test two = Test.TWO;
    }
}
