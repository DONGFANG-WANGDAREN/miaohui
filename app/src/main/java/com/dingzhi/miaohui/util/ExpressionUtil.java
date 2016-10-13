package com.dingzhi.miaohui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名：ExpressionUtil.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:10.
 * 功能描述: 正则表达式
 * 函数/方法说明:
 */
public class ExpressionUtil {

    
    /**
      * 函数名：isMobileNO
      * 创建人： TanXin.
      * 创建日期： 2016/10/13 17:10.
      * 功能描述：判断字符串是否为手机号
      * */
    public static boolean isMobileNO(String mobiles){

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
        }
}
