package com.dingzhi.miaohui.ui.activity;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.ui.fragment.ChatFragment;
import com.dingzhi.miaohui.ui.fragment.FindFragment;
import com.dingzhi.miaohui.ui.fragment.LikeFragment;
import com.dingzhi.miaohui.ui.fragment.MovableFragment;
import com.dingzhi.miaohui.ui.fragment.MyFragment;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.util.PreferencesUtil;
import com.dingzhi.miaohui.widget.TabStripView;

import butterknife.BindView;
/**
 * 文件名：MainActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 14:38.
 * 功能描述: 主页面
 * 函数/方法说明:
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.navigateTabBar)
    TabStripView navigateTabBar;
    private ViewStub stubGuideSlide;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    protected void initView() {
        //添加底部按钮
        navigateTabBar.addTab(FindFragment.class, new TabStripView.TabParam(R.mipmap.icon_bottom_find, R.mipmap.icon_bottom_find_select, R.string.bottom_find));
        navigateTabBar.addTab(LikeFragment.class, new TabStripView.TabParam(R.mipmap.icon_bottom_like, R.mipmap.icon_bottom_like_select, R.string.bottom_like));
        navigateTabBar.addTab(MovableFragment.class, new TabStripView.TabParam(R.mipmap.icon_bottom_movable, R.mipmap.icon_bottom_movable_select, R.string.bottom_movable));
        navigateTabBar.addTab(ChatFragment.class, new TabStripView.TabParam(R.mipmap.icon_bottom_chat, R.mipmap.icon_bottom_chat_select, R.string.bottom_chat));
        navigateTabBar.addTab(MyFragment.class, new TabStripView.TabParam(R.mipmap.icon_bottom_my, R.mipmap.icon_bottom_my_select, R.string.bottom_my));
        showGuide();

    }

     /**
       * 函数名： showGuide
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 14:37.
       * 功能描述：引导页
       */
    private void showGuide() {
        if (PreferencesUtil.getBoolean(MainActivity.this,C.FILE_NAME,"guide_find")){
            return;
        }

        stubGuideSlide = (ViewStub) findViewById(R.id.guide_root_slide);
        final View guideSlideView = stubGuideSlide.inflate();
        LinearLayout rl = (LinearLayout) guideSlideView.findViewById(R.id.guide_root);
        ImageView img = (ImageView) guideSlideView.findViewById(R.id.img);
        img.getBackground().setAlpha(180);
        if (rl != null) {
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   guideSlideView.setVisibility(View.GONE);
                }
            });
        }
        PreferencesUtil.putBoolean(MainActivity.this, C.FILE_NAME,"guide_find",true);
    }

}
