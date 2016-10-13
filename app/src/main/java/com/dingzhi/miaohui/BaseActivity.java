package com.dingzhi.miaohui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 文件名：BaseActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 14:38.
 * 功能描述: Activity基类
 * 函数/方法说明:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(setLayout());
        //绑定ButterKnife
        unbinder = ButterKnife.bind(this);
        initView();
    }

    protected abstract int setLayout();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑ButterKnife
        unbinder.unbind();
    }

     /**
       * 函数名： setStatusBar
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 14:42.
       * 功能描述：设置沉浸式状态栏
       * 参考文档：http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650237284&idx=1&sn=6e3b30cc63d675bae8cb3e7c9563384e&chksm=8863980bbf14111d1e3ac9522ae56433a3486d58e7ad5b58c1894270a126823983fc3a6017ac&scene=4#wechat_redirect
       */
    private void setStatusBar(){
        //5.0以上
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            View decorView  = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|layoutParams.flags);
        }
    }
}
