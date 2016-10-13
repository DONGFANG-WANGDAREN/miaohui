package com.dingzhi.miaohui.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 文件名：AppUtil.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:06.
 * 功能描述: APP相关
 * 函数/方法说明:
 */
public class AppUtil {

	 /**
	   * 函数名： AppName
	   * 创建人： TanXin.
	   * 创建日期： 2016/10/13 17:06.
	   * 功能描述：获取应用程序名称
	   */
	public static String AppName(Context context){
		try{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	 /**
	   * 函数名： getVersionName
	   * 创建人： TanXin.
	   * 创建日期： 2016/10/13 17:07.
	   * 功能描述：当前应用的版本号*/
	public static String getVersionName(Context context)
	{
		try{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
