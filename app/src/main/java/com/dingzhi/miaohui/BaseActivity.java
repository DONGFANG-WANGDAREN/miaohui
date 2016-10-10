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
 * Created by SRDZ on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(setLayout());
        unbinder = ButterKnife.bind(this);
        initView();
    }

    protected abstract int setLayout();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
