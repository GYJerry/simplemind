package cn.simplemind.test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
    
    public static void stringTest() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming"; // ---> 相当于new StringBuilder("Program").append("ming").toString()？？？
        String s4 = new StringBuilder("Program").append("ming").toString();
        System.out.println("s1 == s2: " + (s1 == s2)); // false
        System.out.println("s1 == s3: " + (s1 == s3)); // true
        System.out.println("s1 == s4: " + (s1 == s4)); // false
        System.out.println("s3 == s4: " + (s3 == s4)); // false
        // 对于任意两个字符串 s 和 t，当且仅当 s.equals(t) 为 true 时，s.intern() == t.intern() 才为 true。
        System.out.println("s1 == s1.intern(): " + (s1 == s1.intern())); // true
        System.out.println("s1.equals(s1.intern()): " + (s1.equals(s1.intern()))); // true
        System.out.println("s1 == s2.intern(): " + (s1 == s2.intern())); // true
        System.out.println("s1.equals(s2.intern()): " + (s1.equals(s2.intern()))); // true
        System.out.println("s1.intern().equals(s2.intern()): " + (s1.intern().equals(s2.intern()))); // true
        System.out.println("s1.intern() == s2.intern(): " + (s1.intern() == s2.intern())); // true
    }
    
    /**
     * 字符串反转
     * 
     * @author yingdui_wu
     * @date   2018年4月9日 下午10:22:50
     * @param originStr
     * @return
     */
    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1) 
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }
    
	public static void testDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		calendar.set(1970, 0, 1, 0, 0, 0);
		Date calDate = calendar.getTime();
		System.out.println(calDate.getTime());
		
		Date testDate2 = new Date(calDate.getTime());
		System.out.println(testDate2);
		
		System.out.println();
		
		Date zeroDate = new Date(0);
		System.out.println(zeroDate.getTime()/1000);
		System.out.println(zeroDate);
	}
    
    /**
     * 日期相关的一些测试
     * 
     * @author yingdui_wu
     * @date   2018年4月12日 下午7:25:20
     */
    public static void dateTest() {
    	Calendar cal = Calendar.getInstance();
        System.out.print(cal.get(Calendar.YEAR) + "  ");
        System.out.print(cal.get(Calendar.MONTH) + "  ");    // 0 - 11
        System.out.print(cal.get(Calendar.DATE) + "  ");
        System.out.print(cal.get(Calendar.HOUR_OF_DAY) + "  ");
        System.out.print(cal.get(Calendar.MINUTE) + "  ");
        System.out.println(cal.get(Calendar.SECOND));
 
        // Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.print(dt.getYear() + "  ");
        System.out.print(dt.getMonthValue() + "  ");     // 1 - 12
        System.out.print(dt.getDayOfMonth() + "  ");
        System.out.print(dt.getHour() + "  ");
        System.out.print(dt.getMinute() + "  ");
        System.out.println(dt.getSecond());
        
        // 获得毫秒数
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(Clock.systemDefaultZone().millis()); // java 8
        System.out.println(new Date().getTime());
        
        // 如何取得某月的最后一天
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH));
	}
    
    public static void catchException() {
    	class Annoyance extends Exception {}
    	class Sneeze extends Annoyance {}
    	 
    	try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {
				System.out.println("Caught Annoyance");
				throw a;
			}
		} catch (Sneeze s) {
			System.out.println("Caught Sneeze");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
    
    public static String finalReturn() {
        String s;
        try {
            s = "abc";
            return s;
        } finally {
            s = "def";
        }
    }
    
    public static String finalReturn2() {
        try {
            return "abc";
        } finally {
            return "def";
        }
    }
    
    static class Annoyance extends Exception {}
    static class Sneeze extends Annoyance {}
    
    public static void throwCatch() {
        try {
            try {
                throw new Sneeze();
            } 
            catch ( Annoyance a ) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } 
        catch ( Sneeze s ) {
            System.out.println("Caught Sneeze");
            return ;
        }
        finally {
            System.out.println("Hello World!");
        }
    }
    
    public static void testFloat() {
        System.out.println(2.00-1.10);
        
        System.out.println(2.00f-1.10f);
        
        BigDecimal b1 = new BigDecimal(Double.toString(2.00));
        BigDecimal b2 = new BigDecimal(Double.toString(1.10));
        double result = b1.subtract(b2).doubleValue();
        System.out.println(result);
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
        //orTest();
        //stringTest();
    	
    	//System.out.println(reverse("wuyingdui"));
    	//testDate();
    	//dateTest();
    	//catchException();
        
        System.out.println(finalReturn());
        //throwCatch();
        //testFloat();
        System.out.println(finalReturn2());
    }
}
