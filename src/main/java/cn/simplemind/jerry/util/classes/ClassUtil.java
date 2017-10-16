package cn.simplemind.jerry.util.classes;

/**
 * 类处理工具类
 * @author wuyingdui
 * @date   2017年5月16日 下午5:31:47
 */
public class ClassUtil {
    /**
     * 限制该类的静态方法只能通过类名调用，不能进行实例化
     * @throws Exception
     */
    private ClassUtil() throws Exception {
        throw new Exception("please do not initialiize me!");
    }
}
