package cn.simplemind.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import cn.simplemind.jerry.util.number.NumberUtil;
import cn.simplemind.test.enums.FlowStatusEnum;



/**
 * 测试
 * @author wuyingdui
 * @date   2017年6月1日 下午2:57:48
 */
public class SimpleTest {
    
    public static void substringTest() {
    	String str="222,";
        System.out.println(str.lastIndexOf(","));
        if (str.lastIndexOf(",") > 0) {
            str=str.substring(0,str.lastIndexOf(","));
        }
        System.out.println(str);
    }
    
    public static void divisibleTest() {
    	// print 2
    	System.out.println(5/2);
    }
    
    public static void initializeUtilClass() {
        //NumberUtil util = new NumberUtil();
        int[] numbers = NumberUtil.generateRandomIntArray(10, 9, 100);
        System.out.println(Arrays.toString(numbers));
    }
    
    public void test(){
        int i=80;
        String s = String.valueOf(i<90 ? 90 : 100);
        String t = String.valueOf(i<90 ? 90 : 100.0);
        System.out.println(s);
        System.out.println(t);
    }
    
    public static void objectConvert() {
        class Human {
            private int age;
            private String name;
            
            /**
             * @param age
             * @param name
             */
            public Human(int age, String name) {
                super();
                this.age = age;
                this.name = name;
            }
        }
        
        class Student extends Human {
            private int stuNo;
            private String school;
            
            /**
             * @param age
             * @param name
             * @param stuNo
             * @param school
             */
            public Student(int age, String name, int stuNo, String school) {
                super(age, name);
                this.stuNo = stuNo;
                this.school = school;
            }
        }
        
        Human human = new Human(13, "Jerry");
        // father to child
        Student jerry = (Student) human; // 有问题
        
        Student helenStudent = new Student(10, "Helen", 30, "HuaMei");
        // child to father
        Human human2 = helenStudent;
        
        int r = 8;
        Student aStudent = (Student) (r > 9? human:helenStudent); // 有问题
        Human aHuman = r > 9? human:helenStudent; // 没问题
        
        // 子类实例可以转为父类实例，但是父类实例不可以转为子类实例
    }
    
    public static void testOverride() {
        class Base {
            void fun(int price, int... discounts) {
                System.out.println("Base..........fun");
            }
        }
        
        class Child extends Base {
            @Override
            public void fun(int price, int[] discounts) {
                System.out.println("child..........fun");
            }
        }
        
        // 向上转型
        Base base = new Child();
        base.fun(100, 50);
        
        Child child = new Child();
        //child.fun(100, 50);  //这个有问题
    }
    
    public static void autoIncrease() {
        int tmp = 0;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = count++;
        }
        System.out.println(count);
        
        for (int i = 0; i < 10; i++) {
            tmp = count++;
        }
        System.out.println(tmp);
    }
    
    // test static import
    public static void calBallArea(double r) {
        double area = 4*Math.PI * r * r;
        System.out.println(area);
    }
    
    // scriptEngine
    public static void scriptEngine() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bind = engine.createBindings();
        bind.put("factor", 2);
        engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int first = scanner.nextInt();
            int sec = scanner.nextInt();
            System.out.println("输入参数为："+first+","+sec);
            try {
                /** js脚本中的函数
                    function fun(first, sec) {
                        return first + sec*factor;
                    }
                 */
                engine.eval(new FileReader("C:\\Users\\Administrator\\Desktop\\model.js"));
            }catch (Exception e) {
                e.printStackTrace();
            }
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                try {
                    Double result = (Double) invocable.invokeFunction("fun", first, sec);
                    System.out.println("统计结果："+result.intValue());
                } catch (NoSuchMethodException | ScriptException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // instanceof
    public static void tryInstanceof() {
        class GenericClass<T> {
            // 判断是否是Date类型
            public boolean isDateInstance(T t) {
                return t instanceof Date;
            }
        }
        
        // String对象是否是Object的实例
        boolean b1 = "Sting" instanceof Object;
        // String对象是否是String的实例
        boolean b2 = new String() instanceof String;
        // Object对象是否是String的实例
        boolean b3 = new Object() instanceof String;
        // 拆箱类型是否是装箱类型的实例
        //boolean b4 = 'A' instanceof Character;
        // 空对象是否是String的实例
        boolean b5 = null instanceof String;
        // 类型转换后的空对象是否是String的实例
        boolean b6 = (String) null instanceof String;
        // Date对象是否是String的实例
        //boolean b7 = new Date() instanceof String;
        // 在泛型类中判断String对象是否是Date的实例
        boolean b8 = new GenericClass<String>().isDateInstance("");
        
        Object obj = new Date();
        boolean isDate = obj instanceof Date; // true
        boolean isString = obj instanceof String; // false
    }
    
    // integerCache
    public static void integerCache() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextInt()) {
            int ii = input.nextInt();
            
            System.out.println("\n==== " + ii + " 的相等判断======");
            // 两个通过new产生的Integer对象
            Integer i = new Integer(ii);
            Integer j = new Integer(ii);
            
            System.out.println("new产生的对象：" + (i == j));
            // 基本类型转为包装类型后比较
            i=ii;
            j=ii;
            
            System.out.println("基本类型自动装箱的对象：" + (i==j));
            // 通过静态方法生成一个实例
            i = Integer.valueOf(ii); 
            j = Integer.valueOf(ii);
            System.out.println("valueOf产生的对象：" + (i == j));
            
            i = new Integer(ii);; 
            j = ii;
            System.out.println("new和自动装箱产生的对象：" + (i == j));
        }
    }
    
    // test construct code block
    public static void contractBlock() {
        class Father {
            {
                System.out.println("father block~~");
            }
            
            public Father(){
                
            }
        }
        
        class Son extends Father {
            {
                System.out.println("son block~~");
            }
            
            Son(String str) {
                System.out.println("son's construct fun~~ str -- " + str);
            }
        }
        
        Son son = new Son("ggggggggg");
    }
    
    // 使用序列化对象的形式克隆对象
    public static void serialization() {
        class Identity implements Serializable {
            /**
             * serialVersionUID属性说明:
             */
            private static final long serialVersionUID = -5248236553545728871L;
            private String nickName;
            private int id;
            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
            public void setId(int id) {
                this.id = id;
            }
        }
        
        class Someone implements Serializable {
            /**
             * serialVersionUID属性说明:
             */
            private static final long serialVersionUID = 4864668540765202179L;
            
            private Identity identity;
            private int age;
            
            public void setIdentity(Identity identity) {
                this.identity = identity;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }
        
        Someone someone = new Someone();
        Identity identity = new Identity();
        identity.setId(123456);
        identity.setNickName("陈臭圆~~");
        someone.setIdentity(identity);
        someone.setAge(20);
        CloneUtils.clone(someone);

    }
    
    static class CloneUtils {
        // 拷贝一个对象
        @SuppressWarnings("unchecked")
        public static <T extends Serializable> T clone(T obj) {
            // 拷贝产生的对象
            T clonedObj = null;
            try {
                // 读取对象字节数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                oos.close();
                
                // 分配内存空间，写入原始对象，生成新对象
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                // 返回新对象，并做类型转换
                clonedObj = (T) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return clonedObj;
        }
    }
    
    public static void testEqualNull() {
        String a = "test";
        String b = null;
        boolean result = a.equals(b);
        result = b.equals(a);
    }
    
    public static void stringBuilderTest() {
        StringBuilder stringBuilder = new StringBuilder();
        String b = null;
        stringBuilder.append(b);
        System.out.println(stringBuilder.toString());
    }
    
    public static void nullObjToString() {
        Object obj = null;
        String objStr = (String) obj;
        System.out.println(objStr);
        
        String objStr2 = obj + "";
        System.out.println(objStr2);
        
        String objStr3 = String.valueOf(obj);
        System.out.println(objStr3);
    }
    
    public static void enumTest() {
        FlowStatusEnum[] values = FlowStatusEnum.values();
        for (FlowStatusEnum flowStatusEnum : values) {
            System.out.print(flowStatusEnum.getStatus());
            System.out.println(flowStatusEnum.getName());
        }
    }
    
    /**
     * 列表分页
     * 
     * @author wuyingdui
     * @date   2017年9月26日 下午5:43:59
     */
    public static void listPageTest() {
        int start = 0;
        int limit = 4;
        
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        while (start < nums.size()) {
            int toIndex = start+limit;
            if (toIndex > nums.size()) {
                // 最大只能是列表的最大长度
                toIndex = nums.size();
            }
            List<Integer> subNums = nums.subList(start, toIndex);
            System.out.println(subNums);
            
            start += limit;
        }
    }
    
    /**
     * 分页获取列表数据
     * 
     * @author wuyingdui
     * @date   2017年10月17日 下午2:24:51
     * @param  list
     * @param  start
     * @param  limit
     * @return
     */
    public static <E> List<E> subList(List<E> list, int start, int limit) {
        List<E> result = new ArrayList<E>();
        if (list == null) {
            return result;
        }

        start = Math.max(0, start); // 大于等于0
        limit = Math.max(0, limit); // 大于等于0

        int fromIndex = start;
        fromIndex = Math.min(list.size(), fromIndex);
        int toIndex = (limit > 0) ? (fromIndex + limit) : list.size();
        toIndex = Math.min(list.size(), toIndex);
        List<E> subList = list.subList(fromIndex, toIndex);

        if (subList != null && !subList.isEmpty()) {
            result.addAll(subList);
        }

        return result;
    }
    
    /**
     * java双重for循环跳出测试
     * 
     * @author wuyingdui
     * @date   2017年10月26日 上午10:35:19
     */
    public static void jumpFromDoubleFor() {
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                for (int j = 0; j < 5; j++) {
                    if (j == 2) {
                        System.out.println("jump form " + i + "." + j);
                        break;
                    }
                }
            }

            System.out.println("here is " + i);
        }
    }
    
    public static void testGetClass() {
        Integer i = new Integer(1000);
        System.out.println(i.getClass());
        
        SortTest test = new SortTest();
        test.hashCode();
        System.out.println(test.getClass());
    }
    
    public static void getHashCode() {
        int hashCode = new HashCodeBuilder().append("aaaaa").toHashCode();
        System.out.println(hashCode);
        
        SortTest test = new SortTest();
        System.out.println(test.hashCode());
        System.out.println(test);
        
        Integer num = 100;
        System.out.println(num.hashCode());
    }
    
    public static void testStringAddr() {
        String string = "a";
        System.out.println(string.hashCode());
        string = string + "b";
        System.out.println(string.hashCode());
        SortTest test = new SortTest();
        System.out.println(test.toString());
        
        String string2 = "a";
        System.out.println(string2.hashCode());
    }
    
    public static void testRegex() {
        //接收键盘输入
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
           String str = input.nextLine();
           //正则表达式对象
           Pattern pattern = Pattern.compile("\\b\\w+\\b");
           //生成匹配器
           Matcher matcher = pattern.matcher(str);
           //记录单词数量
           int wordsCount = 0;
           //遍历查找匹配，统计单词数量
           while (matcher.find()) {
               wordsCount++;
           }
           System.out.println(str + " 单词数：" + wordsCount);
        }
    }
    
    public static void encoding() {
        String str = "汉字";
        //读取字节
        byte[] b = new byte[0];
        try {
            b = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //重新生成一个新的字符串
        System.out.println(new String(b));
    }
    
    public static void testArrays() {
        String[] strs = new String[3];
        strs[0] = "aaaa"; strs[1] = "bbbb"; strs[2] = "cccc";
        System.out.println(strs.hashCode());
        List<String> list = Arrays.asList(strs);
        System.out.println(list.hashCode());
        
        // list.add("cccc"); // 会报错，不能进行新增
        
        List<String> list2 = new ArrayList<>();
    }
    
    /**
     * 遍历数组的效率
     * 
     * @author wuyingdui
     * @date   2017年11月27日 下午2:48:30
     */
    public static void testErgodic() {
        //学生数量， 8万
        int stuNum = 8 * 10000;

        // List集合，记录所有学生的分数
        List<Integer> scores = new LinkedList<>();
        // List<Integer> scores = new ArrayList<Integer>(stuNum);

        // 写入分数
        for (int i = 0; i < stuNum; i++) {
            scores.add(new Random().nextInt(150));
        }

        System.out.println("使用foreach遍历的方法");
        // 记录开始计算时间
        long start = System.currentTimeMillis();
        int sum = 0;
        // 遍历求和
        for (int i : scores) {
            sum += i;
        }
        // 除以人数，计算平均值
        System.out.println("平均分是：" + sum / scores.size());
        System.out.println("执行时间：" + (System.currentTimeMillis() - start) + "ms");

        
        System.out.println("使用迭代器方式遍历的方法");
        start = System.currentTimeMillis();
        sum = 0;
        for (Iterator<Integer> iterator = scores.iterator(); iterator.hasNext();) {
            sum += iterator.next();
        }
        System.out.println("平均分是：" + sum / scores.size());
        System.out.println("执行时间：" + (System.currentTimeMillis() - start) + "ms");

        
        System.out.println("使用下标方式遍历的方法");
        start = System.currentTimeMillis();
        sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        System.out.println("平均分是：" + sum / scores.size());
        System.out.println("执行时间：" + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static void testSubList() {
        //定义一个包含两个字符串的列表
        List<String> c = new ArrayList<String>();
        c.add("A");
        c.add("B");
        //构造一个包含c列表的字符串列表
        List<String> c1 = new ArrayList<String>(c);
        //subList生成与c相同的列表
        List<String> c2 = c.subList(0, c.size());
        //c2增加一个元素
        c2.add("C");
        System.out.println("c.hashcode:" + c.hashCode());
        System.out.println("c1.hashcode:" + c1.hashCode());
        System.out.println("c2.hashcode:" + c2.hashCode());
        System.out.println("c == c1? " + c.equals(c1));
        System.out.println("c == c2? " + c.equals(c2));

    }
    
    static class City implements Comparable<City> {
        // 城市编码
        private String code;
        // 城市名称
        private String name;

        public City(String _code, String _name) {
            code = _code;
            name = _name;
        }

        @Override
        public int compareTo(City o) {
            // 按照城市名称排序
            return new CompareToBuilder().append(name, o.name).toComparison();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            City city = (City) obj;
            // 根据code判断是否相等
            return new EqualsBuilder().append(code, city.code).isEquals();
        }
    }

    public static void testCompare() {
        List<City> cities = new ArrayList<City>();
        cities.add(new City("021", "上海"));
        cities.add(new City("021", "沪"));
        // 排序
        Collections.sort(cities);
        // 查找对象
        City city = new City("021", "沪");
        // indexOf方法取得索引值
        int index1 = cities.indexOf(city); // 使用equals方法
        // binarySearch查找到索引值
        int index2 = Collections.binarySearch(cities, city); // 使用compareTo方法
        System.out.println("索引值(indexOf)：" + index1);
        System.out.println("索引值（binarySearch)：" + index2);
    }
    
    static class SortBean {
        // 城市编码
        private String num;
        // 城市名称
        private String name;

        public SortBean(String _num, String _name) {
            num = _num;
            name = _name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    public static void testSort() {
        List<SortBean> list = new ArrayList<>();
        list.add(new SortBean("001", "aaaaaaaaa"));
        list.add(new SortBean("002", "bbbbbbbbb"));
        list.add(null);
        list.add(new SortBean("003", "ccccccccc"));
        list.add(new SortBean(null, "ddddddddd"));
        
        Collections.sort(list, new Comparator<SortBean>() {

            @Override
            public int compare(SortBean o1, SortBean o2) {
                if (o1 == null || o2 == null) {
                    return -1;
                }
                else if (o1.getNum() == null || o2.getNum() == null) {
                    return -1;
                }
                else {
                    return o1.getNum().compareTo(o2.getNum());
                }
            }
            
        });
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                System.out.println("第"+i+"个值："+list.get(i));
            }
            else {
                System.out.println("第"+i+"个值："+list.get(i).getNum()+","+list.get(i).getName());
            }
            
        }
    }
    
    public static void testShuffle() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        
        list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Collections.shuffle(list);
        System.out.println(list);
        
        list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Random rnd = new Random(100);
        Collections.shuffle(list, rnd);
        System.out.println(list);
    }
    
    public static void testHashMap() {
        final Runtime rt = Runtime.getRuntime();
        Thread hook = new Thread() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                long heapMaxSize = rt.maxMemory() >> 20;
                sb.append("最大可用内存：" + heapMaxSize + "M\n");
                long total = rt.totalMemory() >> 20;
                sb.append("堆内存大小：" + total + "M\n");
                long free = rt.freeMemory() >> 20;
                sb.append("空闲内存：" + free + "M");
                System.out.println(sb);
            }
        };
        // JVM终止前记录内存信息
        rt.addShutdownHook(hook);
        // 放入近40万键值对
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 393217; i++) {
            map.put("key" + i, "vlaue" + i);
        }
        
        /*List<String> list = new ArrayList<>();
        for (int i = 0; i < 393217; i++) {
            list.add("key" + i); list.add("vlaue" + i);
        }*/
        
        System.out.println("ending...");
    }
    
    /**
     * @author wuyingdui
     * @date   2017年8月21日 下午7:15:14
     * @param  args
     */
    public static void main(String[] args) {
        //objectConvert();
        //testOverride();
        //autoIncrease();
        //calBallArea(50.0);
        //scriptEngine();
        //tryInstanceof();
        integerCache();
        //contractBlock();
        //serialization();
        //testEqualNull();
        //stringBuilderTest();
        //nullObjToString();
        //enumTest();
        //listPageTest();
        
        //List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        //System.out.println(subList(nums, nums.size()-1, 4));
        
        //jumpFromDoubleFor();
        //testGetClass();
        //getHashCode();
        
        //testStringAddr();
        
        //testRegex();
        //encoding();
        //testArrays();
        
        //testErgodic();
        //testSubList();
        //testCompare();
        //testSort();
        
        //testShuffle();
        //testHashMap();
    }
}
