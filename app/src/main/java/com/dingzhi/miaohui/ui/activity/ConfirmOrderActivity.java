package com.dingzhi.miaohui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.CheckSelectAdapter;
import com.dingzhi.miaohui.widget.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
 /**
  * 文件名：ConfirmOrderActivity.
  * 版权所有：SRDZ
  * 创建人：TANXIN
  * 创建日期:2016/10/13 16:13.
  * 功能描述:确认订单
  * 函数/方法说明:
  */
public class ConfirmOrderActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.grid_time)
    ExpandableHeightGridView gridTime;
    @BindView(R.id.radio_skill)
    RadioGroup radioSkill;
    @BindView(R.id.grid_skill)
    ExpandableHeightGridView gridSkill;

    private HashMap<Integer, String> timeHashMap = new HashMap<>();
    private CheckSelectAdapter timeAdapter;
    private List<String> timelist;

    private HashMap<Integer,String> skillHashMap = new HashMap<>();
    private CheckSelectAdapter skillAdapter;
    private List<String> skillList = new ArrayList<>();
    private List<String> onlineList;
    private List<String> thelineList;


    @Override
    protected int setLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("确认订单");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTime();
        setSkill();

    }

      /**
        * 函数名： setSkill
        * 创建人： TanXin.
        * 创建日期： 2016/10/13 16:13.
        * 功能描述：初始化技能
        */
    private void setSkill() {
        onlineList = new ArrayList<>();
        onlineList.add("陪玩陪聊");
        onlineList.add("情感交流");
        thelineList = new ArrayList<>();
        thelineList.add("吃饭");
        thelineList.add("看电影");
        thelineList.add("不可描述");

        if (onlineList.size()>0){
            for(int i= 0;i<onlineList.size();i++){
                skillList.add(onlineList.get(i));
            }
        }
        skillAdapter = new CheckSelectAdapter(this,skillList,skillHashMap);
        gridSkill.setAdapter(skillAdapter);

        gridSkill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                skillHashMap.clear();
                if (skillAdapter.isBlank(skillHashMap.get(i))) {
                    skillHashMap.put(i, skillList.get(i));
                } else {
                    skillHashMap.remove(i);
                }
                skillAdapter.change(skillList, skillHashMap);
            }
        });

        radioSkill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_online:
                        if (onlineList.size()>0){
                            skillList.clear();
                            skillHashMap.clear();
                            for(int j = 0;j<onlineList.size();j++){
                                skillList.add(onlineList.get(j));
                            }
                            skillAdapter.change(skillList,skillHashMap);
                        }
                        break;
                    case R.id.radio_theline:
                        if (thelineList.size()>0){
                            skillList.clear();
                            skillHashMap.clear();
                            for(int j = 0;j<thelineList.size();j++){
                                skillList.add(thelineList.get(j));
                            }
                            skillAdapter.change(skillList,skillHashMap);
                        }
                        break;
                }


            }
        });
    }


      /**
        * 函数名： setTime
        * 创建人： TanXin.
        * 创建日期： 2016/10/13 16:14.
        * 功能描述：初始化时间
        */
    private void setTime() {
        timelist = new ArrayList<>();
        timelist.add("2小时");
        timelist.add("3小时");
        timelist.add("4小时");
        timelist.add("5小时");
        timeAdapter = new CheckSelectAdapter(this, timelist, timeHashMap);
        gridTime.setAdapter(timeAdapter);
        gridTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                timeHashMap.clear();
                if (timeAdapter.isBlank(timeHashMap.get(i))) {
                    timeHashMap.put(i, timelist.get(i));
                } else {
                    timeHashMap.remove(i);
                }
                timeAdapter.change(timelist, timeHashMap);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @OnClick(R.id.btn_sure)
    public void onClick() {
        //获取时间
        String time = "";
        Iterator iter = timeHashMap.keySet().iterator();
        while (iter.hasNext()) {
            int key = (Integer) iter.next();
            String val = timeHashMap.get(key);
            time += (val);
        }
        //获取技能
        String skill = "";
        Iterator iters = skillHashMap.keySet().iterator();
        while (iters.hasNext()) {
            int key = (Integer) iters.next();
            String val = skillHashMap.get(key);
            skill += (val);
        }

        if (time.isEmpty()) {
            Toast.makeText(ConfirmOrderActivity.this, "请选择时间", Toast.LENGTH_SHORT).show();
        } else if (skill.isEmpty()) {
            Toast.makeText(ConfirmOrderActivity.this, "请选择技能", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ConfirmOrderActivity.this, skill+"~"+time, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,SubmitOrderActivity.class));
        }

    }

}
