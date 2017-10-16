package cn.simplemind.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
        Student jerry = (Student) human;
        
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
            
            System.out.println("\n====" + ii + " 的相等判断======");
            // 两个通过new产生的Integer对象
            Integer i = new Integer(ii);
            Integer j = new Integer(ii);
            
            System.out.println("new产生的对象：" + (i == j));
            // 基本类型转为包装类型后比较
            i=ii;
            j=ii;
            
            System.out.println("基本类型转换的对象：" + (i==j));
            // 通过静态方法生成一个实例
            i=Integer.valueOf(ii); 
            j = Integer.valueOf(ii);
            System.out.println("valueOf产生的对象：" + (i == j));
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
        //integerCache();
        //contractBlock();
        //serialization();
        //testEqualNull();
        //stringBuilderTest();
        //nullObjToString();
        //enumTest();
        listPageTest();
    }
}
