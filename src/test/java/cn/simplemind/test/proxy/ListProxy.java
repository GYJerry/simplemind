package cn.simplemind.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 动态代理
 * 例子使用动态代理为ArrayList编写一个代理，在添加和删除元素时，在控制台打印添加或删除的元素以及ArrayList的大小
 * 
 * @author yingdui_wu
 * @date   2018年4月15日 下午9:55:30
 */
public class ListProxy<T> implements InvocationHandler {
	private List<T> target;
	 
    public ListProxy(List<T> target) {
        this.target = target;
    }
 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object retVal = null;
        System.out.println("[" + method.getName() + ": " + args[0] + "]");
        retVal = method.invoke(target, args);
        System.out.println("[size=" + target.size() + "]");
        return retVal;
    }
}
