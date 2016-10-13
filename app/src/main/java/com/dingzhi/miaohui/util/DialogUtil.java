package com.dingzhi.miaohui.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * 文件名：DialogUtil.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:09.
 * 功能描述: DialogUtil
 * 函数/方法说明:
 */
public class DialogUtil {

    /**
      * 函数名： showDialogDate
      * 创建人： TanXin.
      * 创建日期： 2016/10/13 17:09.
      * 功能描述：选择日期 */

    public static void showDialogDate(final TextView timeText, Context context) {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                //选择完日期后弹出选择时间对话框
                timeText.setText(time);
                timeText.setTextColor(Color.parseColor("#4c4c4c"));
            }
        }, year, month, day){
            @Override
            protected void onStop() {
                //在4.0版本会回调两次
            }
        };
        //弹出选择日期对话框
        datePickerDialog.show();
    }
}
