package com.dingzhi.miaohui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * 文件名：NoScrollListview.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 18:03.
 * 功能描述: 不滑动listview，和scrollview 配合使用
 * 函数/方法说明:
 */
public class NoScrollListview extends ListView {
  
    public NoScrollListview(Context context) {
        // TODO Auto-generated method stub  
        super(context);  
    }  
  
    public NoScrollListview(Context context, AttributeSet attrs) {
        // TODO Auto-generated method stub  
        super(context, attrs);  
    }  
  
    public NoScrollListview(Context context, AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub  
        super(context, attrs, defStyle);  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }  
}  