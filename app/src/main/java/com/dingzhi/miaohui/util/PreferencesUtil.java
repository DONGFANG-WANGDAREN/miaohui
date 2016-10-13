package com.dingzhi.miaohui.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 文件名：PreferencesUtil.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:12.
 * 功能描述: SharedPreferencesUtil
 * 函数/方法说明:
 */
public class PreferencesUtil {
	public static SharedPreferences sharedPreferences = null;
	public static SharedPreferences.Editor editor = null;
	public static final String PREFERENCES_DEFAULT = "preferences_store";
	public static final String PREFERENCES_HTTP = "preferences_http";

	/**
	 * Environment 获取sharedPreferences对象
	 * 
	 * @param context
	 * @return
	 */
	public static SharedPreferences getPreferences(Context context, String str) {
		str = str == null ? PREFERENCES_DEFAULT : str;
		sharedPreferences = context.getSharedPreferences(str,
				Context.MODE_PRIVATE);
		return sharedPreferences;
	}

	/**
	 * 获取editor对象
	 * 
	 * @param context
	 * @return
	 */
	public static SharedPreferences.Editor getEditor(Context context, String str) {
		if (editor == null) {
			if (sharedPreferences == null) {
				str = str == null ? PREFERENCES_DEFAULT : str;
				sharedPreferences = getPreferences(context, str);
			}
			editor = sharedPreferences.edit();
		} else if (!PREFERENCES_DEFAULT.equals(str) && null != str) {
			sharedPreferences = getPreferences(context, str);
			editor = sharedPreferences.edit();
		}

		return editor;
	}

	/**
	 * 设置int类型的数�?
	 * 
	 * @param context
	 * @param name
	 * @param value
	 */
	public static void putIng(Context context, String fileName, String name,
			int value) {
		if (editor == null) {
			editor = getEditor(context, fileName);
		}
		editor.putInt(name, value);
		editor.commit();
	}

	/**
	 * 设置String 类型的数�?
	 * 
	 * @param context
	 * @param name
	 * @param value
	 */
	public static void putString(Context context, String fileName, String name,
			String value) {
		if (editor == null) {
			editor = getEditor(context, fileName);
		}
		editor.putString(name, value);
		editor.commit();
	}

	/**
	 * 设置boolean类型的数�?
	 * 
	 * @param context
	 * @param name
	 * @param value
	 */
	public static void putBoolean(Context context, String FileName,
			String name, Boolean value) {
		if (editor == null) {
			editor = getEditor(context, FileName);
		}
		editor.putBoolean(name, value);
		editor.commit();
	}

	/**
	 * 设置Long 类型的数�?
	 * 
	 * @param context
	 * @param name
	 * @param value
	 */
	public static void putLong(Context context, String fileName, String name,
			Long value) {
		if (editor == null) {
			editor = getEditor(context, fileName);
		}
		editor.putLong(name, value);
		editor.commit();
	}

	/**
	 * 设置float类型，默认返回�?0
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static void putFloat(Context context, String fileName, String name,
			Float value) {
		if (editor == null) {
			editor = getEditor(context, fileName);
		}
		editor.putFloat(name, value);
		editor.commit();
	}

	/**
	 * 获取int类型，默认返回�?-1
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static int getInt(Context context, String fileName, String name) {
		if (sharedPreferences == null) {
			sharedPreferences = getPreferences(context, fileName);
		}
		return sharedPreferences.getInt(name, -1);
	}

	/**
	 * 获取String 类型，默认返回�?""
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static String getString(Context context, String fileName, String name) {
		if (sharedPreferences == null) {
			sharedPreferences = getPreferences(context, fileName);
		}
		return sharedPreferences.getString(name, "");
	}

	/**
	 * 获取boolean类型，默认返回�?false
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static boolean getBoolean(Context context, String FileName,
			String name) {
		if (sharedPreferences == null) {
			sharedPreferences = getPreferences(context, FileName);
		}
		return sharedPreferences.getBoolean(name, false);
	}

	/**
	 * 获取Long类型，默认返回�?-1
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static Long getLong(Context context, String FileName, String name) {
		if (sharedPreferences == null) {
			sharedPreferences = getPreferences(context, FileName);
		}
		return sharedPreferences.getLong(name, -1);
	}

	/**
	 * 获取float类型，默认返回�?0
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static Float getFloat(Context context, String FileName, String name) {
		if (sharedPreferences == null) {
			sharedPreferences = getPreferences(context, FileName);
		}
		return sharedPreferences.getFloat(name, 0);
	}
}
