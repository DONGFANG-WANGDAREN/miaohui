package com.dingzhi.miaohui.ui.activity.myinfo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.SkillAdapter;
import com.dingzhi.miaohui.widget.DragSelectRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件名：SkillActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:07.
 * 功能描述:技能服务
 * 函数/方法说明:
 */
public class SkillActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle_online)
    DragSelectRecyclerView recycleOnline;
    @BindView(R.id.ed_online)
    EditText edOnline;
    @BindView(R.id.recycle_theline)
    DragSelectRecyclerView recycleTheline;
    @BindView(R.id.ed_theline)
    EditText edTheline;
    @BindView(R.id.btn_sure)
    Button btnSure;
    private SkillAdapter onlineAdapter;
    private List<String> onlineList;
    private final static String[] onlineSkill = {"聊天", "心理咨询", "情感交流", "陪玩游戏"};
    private SkillAdapter thelineAdapter;
    private List<String> thelineList;
    private final static String[] thelineSkill = {"吃饭", "打台球", "喝酒", "逛街", "跑步", "导游",
            "健身", "喝咖啡", "喝茶", "看电影", "情侣", "唱歌"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onlineAdapter.restoreInstanceState(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_skill;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("技能服务");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        onLine();
        theLine();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        onlineAdapter.saveInstanceState(outState);
        thelineAdapter.saveInstanceState(outState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //返回
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (onlineAdapter.getSelectedCount() > 0) {
            onlineAdapter.clearSelected();
        } else if (thelineAdapter.getSelectedCount() > 0) {
            thelineAdapter.clearSelected();
        } else super.onBackPressed();
    }


     /**
       * 函数名： online
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 16:09.
       * 功能描述：获取线上技能
       */
    private void onLine() {
        onlineList = new ArrayList<>();
        Collections.addAll(onlineList, onlineSkill);
        onlineAdapter = new SkillAdapter(onlineList);
        recycleOnline.setLayoutManager(new GridLayoutManager(this, 4));
        recycleOnline.setAdapter(onlineAdapter);
        onlineAdapter.setCallback(new SkillAdapter.ClickListener() {
            @Override
            public void onClick(int index) {
                onlineAdapter.toggleSelected(index);
            }
        });
    }

     /**
       * 函数名： theline
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 16:09.
       * 功能描述：获取线下技能
       */
    private void theLine() {
        thelineList = new ArrayList<>();
        Collections.addAll(thelineList, thelineSkill);
        thelineAdapter = new SkillAdapter(thelineList);
        recycleTheline.setLayoutManager(new GridLayoutManager(this, 4));
        recycleTheline.setAdapter(thelineAdapter);
        thelineAdapter.setCallback(new SkillAdapter.ClickListener() {
            @Override
            public void onClick(int index) {
                thelineAdapter.toggleSelected(index);
            }
        });
    }


    @OnClick(R.id.btn_sure)
    public void onClick() {
        StringBuilder onsb = new StringBuilder();
        int traverse = 0;
        for (Integer index : onlineAdapter.getSelectedIndices()) {
            if (traverse > 0) onsb.append(", ");
            onsb.append(onlineAdapter.getItem(index));
            traverse++;
        }

        StringBuilder thesb = new StringBuilder();
        int traverse1 = 0;
        for (Integer index : thelineAdapter.getSelectedIndices()) {
            if (traverse1 > 0) thesb.append(", ");
            thesb.append(thelineAdapter.getItem(index));
            traverse1++;
        }

        Toast.makeText(this,
                String.format(onlineAdapter.getSelectedCount() + "～华丽的分割线～" + onsb.toString() +
                        "～华丽的分割线～" + thelineAdapter.getSelectedCount() + "～华丽的分割线～" + thesb.toString()),
                Toast.LENGTH_LONG).show();
        onlineAdapter.clearSelected();
        thelineAdapter.clearSelected();
    }
}
