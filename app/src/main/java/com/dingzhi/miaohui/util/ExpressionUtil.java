package com.dingzhi.miaohui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SRDZ on 2016/8/27.
 */
public class ExpressionUtil {

    
    /**
    *Description:判断字符串是否为手机号 <br>
    *@auther TX <br>
    *created at 2016/8/29 16:36
    */
    public static boolean isMobileNO(String mobiles){

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
        }
}
