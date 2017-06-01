package cn.simplemind.jerry.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串格式校验
 * @author wuyingdui
 * @date   2017年5月19日 下午6:13:46
 */
public class StringValid {
    
    /**
     * 校验手机合法性
     *
     * @author wuyingdui
     * @param  phone
     * @return
     */
    public static boolean isPhone(String phone)
    {
        return isChinaInlandPhone(phone) || isHKPhone(phone);
    }
    
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数
     * 17+除4、9的任意数
     * 18+任意数 
     * 147 
     */  
    public static boolean isChinaInlandPhone(String phone) {  
        if (phone == null || phone.length() == 0)
        {
            return false;
        }
        
        String regExp = "^((13[0-9])|(15[^4])|(17[0-3,5-8])|(18[0-9])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(phone);  
        return m.matches();
    }  
  
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    public static boolean isHKPhone(String phone) {  
        if (phone == null || phone.length() == 0)
        {
            return false;
        }
        
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(phone);  
        return m.matches();  
    }

    /**
     * 校验电子邮箱
     *
     * @param  email
     * @return
     * @author zhoujian
     */
    public static boolean isEmail(String email)
    {
        if (email == null || email.length() == 0)
        {
            return false;
        }

        String check = "([a-zA-Z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&\'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);

        return matcher.matches();
    }

    /** 
     * 电话号码/传真验证 
     * 固话和传真的格式是010-12345678-0123
     * 区号：0开头，长度3-4，
     * 号码：非0、1开头，长度7-8，
     * 分机号：长度1-4
     * 
     * @author wuyingdui
     * @param  telephone 
     * @return 验证通过返回true 
     */
    public static boolean isTelephone(String telephone)
    {
        if (telephone == null || telephone.length() == 0)
        {
            return false;
        }

        Pattern p = Pattern.compile("^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})(-[0-9]{1,4})?$");
        Matcher m = p.matcher(telephone);
        boolean b = m.matches();
        
        return b;
    }
    
    /**
     * 判断字符串是否为数字（浮点数，包含正负数、整数、小数等）
     * 
     * @author wuyingdui
     * @date   2017年5月27日 上午1:03:35
     * @param  numStr
     * @return
     */
    public static boolean isNumeric(String numStr) {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$"); 
        Matcher isNum = pattern.matcher(numStr);
        if( !isNum.matches() ){
            return false; 
        } 
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isNumeric("-100000.o11"));
    }
}
