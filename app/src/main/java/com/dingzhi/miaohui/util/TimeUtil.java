package com.dingzhi.miaohui.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 文件名：TimeUtil.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:15.
 * 功能描述:TimeUtil
 * 函数/方法说明:
 */
public class TimeUtil {

     /**
       * 函数名： getDateNow
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 17:15.
       * 功能描述：获取当前日期
      * */
    public static String getDateNow(){
        Date date1 = new Date();
        SimpleDateFormat af = new SimpleDateFormat("yyyy-MM-dd");
        String datenow = af.format(date1);

        return datenow;
    }

     /**
       * 函数名： getAge
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 17:15.
       * 功能描述：根据日期算出现在年龄
       */
    public static int getAge(Date birthDay) throws Exception {
        //获取当前系统时间
        Calendar cal = Calendar.getInstance();
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "出生日期大于现在时间");
        }
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        //将日期设置为出生日期
        cal.setTime(birthDay);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        System.out.println("age:"+age);
        return age;
    }


     /**
       * 函数名：StringToDate
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 17:16.
       * 功能描述：String 转Date */
    public static Date StringToDate (String string){
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         try {
           date = sdf.parse(string);
            System.out.println(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}
