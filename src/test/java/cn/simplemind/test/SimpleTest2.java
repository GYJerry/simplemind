package cn.simplemind.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author wuyingdui
 * @date   2017年11月30日 下午2:16:26
 */
public class SimpleTest2 {
    
    static class UserBean {
        private List<String> codes;
        private String name;
        public List<String> getCodes() {
            return codes;
        }
        public void setCodes(List<String> codes) {
            this.codes = codes;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return "UserBean [codes=" + codes + ", name=" + name + "]";
        }
    }
    
    /**
     * 测试方法中改变对象中null值数据结果
     * 
     * @author wuyingdui
     * @date   2017年11月30日 下午2:17:54
     */
    public static void testObjectNullValue() {
        UserBean bean = new UserBean();
        bean.setName("Jerry");
        System.out.println(bean);
        setValue(bean.getCodes());
        System.out.println(bean);
    }
    
    private static void setValue(List<String> list) {
        list = Arrays.asList("aaaaa","bbbbb","ccccc");
    }
    
    public static void testVector() {
        Vector<String> vector = new Vector<>();
        vector.get(0);
        vector.remove(0);
    }
    
    enum Season {
        Spring, Summer, Autumn, Winter;
        
        public static Season getComfortableSeason() {
            return Summer;
        }
    }
    
    public static void testEnumOpt() {
        System.out.println(Season.getComfortableSeason());
        System.out.println(Season.Summer.getComfortableSeason());
        System.out.println(Season.Summer.ordinal());
    }
    
    static class Car {};
    static class FordCar extends Car {};
    static class BuickCar extends Car {};
    
    /**
     * 使用枚举的抽象方法实现工厂模式
     * 
     * @author wuyingdui
     * @date   2017年11月30日 下午7:51:47
     */
    enum CarFactory {
        FordCar {
            @Override
            public Car create() {
                return new FordCar();
            }
        },
        BuickCar {
            @Override
            public Car create() {
                return new BuickCar();
            }
        };

        // 抽象生产方法
        public abstract Car create();
    }
    
    /**
     * 使用非静态方法实现工厂模式
     * 
     * @author wuyingdui
     * @date   2017年11月30日 下午7:56:44
     */
    enum CarFactory2 {
        FordCar, BuickCar;

        // 非静态方法生产方式
        public Car create() {
            switch (this) {
                case FordCar:
                    return new FordCar();
                    
                case BuickCar:
                    return new BuickCar();

                default:
                    throw new AssertionError("无此车型");
            }
        }
    }
    
    public static void produceEnumCar() {
        System.out.println(CarFactory.FordCar.create());
        System.out.println(CarFactory.BuickCar.create());
        System.out.println(CarFactory2.FordCar.create());
        System.out.println(CarFactory2.BuickCar.create());
    }
    
    /**
     * 将一个list copy到新的list中
     * 
     * @author wuyingdui
     * @date   2017年12月1日 下午1:55:47
     * @param  dest
     * @param  src
     */
    private static <T> void copyList(List<? super T> dest, List<? extends T> src) {
        for (int i=0; i<src.size(); i++)
                dest.set(i, src.get(i)); // 有问题 dest越界了
    }
    
    public static void testCopyList() {
        List<String> srcList = Arrays.asList("aaa","bbb","ccc");
        List<String> desList = new ArrayList<>(10);
        copyList(desList, srcList);
        System.out.println(desList);
        System.out.println(srcList);
    }
    
    public static void testRemove() {
        List<String> list = new ArrayList<>();
        list.add("aaa");list.add("aaa");list.add("bbb");
        List<String> list2 = Arrays.asList("aaa");
        list.removeAll(list2);
        System.out.println(list);
    }
    
    public static void orTest() {
        System.out.println(false | false);
        System.out.println(false | true);
        System.out.println(true | true);
        System.out.println(true | false);
    }
    
    /**
     * @author wuyingdui
     * @date   2017年11月30日 下午2:23:10
     * @param  args
     */
    public static void main(String[] args) {
        //testObjectNullValue();
        //testEnumOpt();
        //produceEnumCar();
        //testCopyList();
        
        //System.out.println("aaaabbbb".indexOf("ccc"));
        //testRemove();
        orTest();
    }
}
