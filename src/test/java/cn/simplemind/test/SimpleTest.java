package cn.simplemind.test;


/**
 * 测试
 * @author wuyingdui
 * @date   2017年6月1日 下午2:57:48
 */
public class SimpleTest {

    /**
     * 
     * @author wuyingdui
     * @date   2017年6月1日 下午2:57:48
     * @param  args
     */
    public static void main(String[] args) {
        String str="222,";
        System.out.println(str.lastIndexOf(","));
        if (str.lastIndexOf(",") > 0) {
            str=str.substring(0,str.lastIndexOf(","));
        }
        System.out.println(str);
    }

}
