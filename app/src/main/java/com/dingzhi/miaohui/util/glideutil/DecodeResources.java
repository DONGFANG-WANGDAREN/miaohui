package com.dingzhi.miaohui.util.glideutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

@SuppressLint("NewApi")
public class DecodeResources
{
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static Drawable Drawable(Context context, int id)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			return context.getResources().getDrawable(id, context.getTheme());
		}
		else
		{
			return context.getResources().getDrawable(id);
		}
	}

	@SuppressWarnings("deprecation")
	public static void setDrawable(Context context, View view,int id)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			view.setBackground(Drawable(context, id));
		}
		else
		{
			view.setBackgroundDrawable(Drawable(context, id));
		}
	}
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static int Color(Context context, int id)
	{
		if (Build.VERSION.SDK_INT >= 23)
		{
			return ContextCompat.getColor(context, id);
		}
		else
		{
			return context.getResources().getColor(id);
		}
	}
}
