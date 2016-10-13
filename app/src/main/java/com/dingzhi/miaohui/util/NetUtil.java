package com.dingzhi.miaohui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 文件名：NetUtil.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:11.
 * 功能描述: 网络相关
 * 函数/方法说明:
 */
public class NetUtil {

     /**
       * 函数名： isConnected
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 17:11.
       * 功能描述：网络是否可用
       * */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
