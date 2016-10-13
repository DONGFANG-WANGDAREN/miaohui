package com.dingzhi.miaohui.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.MyinfoAdapter;
import com.dingzhi.miaohui.enity.MyInfoEnity;
import com.dingzhi.miaohui.ui.activity.editdata.EditDataActivity;
import com.dingzhi.miaohui.ui.activity.myinfo.BaojiaActivity;
import com.dingzhi.miaohui.ui.activity.myinfo.SettingActivity;
import com.dingzhi.miaohui.ui.activity.myinfo.SkillActivity;
import com.dingzhi.miaohui.ui.activity.myinfo.TaZuWoActivity;
import com.dingzhi.miaohui.ui.activity.myinfo.qianbao.WallterActivity;
import com.dingzhi.miaohui.ui.activity.myinfo.WoZuTaActivity;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：MyFragment.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:57.
 * 功能描述: 我的界面
 * 函数/方法说明:
 */
public class MyFragment extends Fragment implements View.OnClickListener{

    private ImageView img_head;
    private LinearLayout lay_edit;
    private ListView lv_my;
    private MyinfoAdapter adapter;
    private List<MyInfoEnity> mdatas;

    public static final String MOCK_DATA_URL = "http://a.hiphotos.baidu.com/image/h%3D360/sign=a2eb7a0eb6de9c82b965ff895c8080d2/d1160924ab18972be4b49efde3cd7b899e510a7e.jpg";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view  = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        initDtat();
        return view;
    }


    private void initDtat() {
        GlideUtils.getInstance().loadRoundImageView(getActivity(), MOCK_DATA_URL, img_head);
        lay_edit.setOnClickListener(this);
        addData();
        adapter = new MyinfoAdapter(getActivity(),mdatas);
        lv_my.setAdapter(adapter);
        lv_my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: //我租了TA
                        startActivity(new Intent(getActivity(), WoZuTaActivity.class));
                        break;
                    case 1:  //TA租了我
                        startActivity(new Intent(getActivity(), TaZuWoActivity.class));
                        break;
                    case 2:  //报价
                        startActivity(new Intent(getActivity(), BaojiaActivity.class));
                        break;
                    case 3:  //技能服务
                        startActivity(new Intent(getActivity(), SkillActivity.class));
                        break;
                    case 4:  //我的钱包
                        startActivity(new Intent(getActivity(), WallterActivity.class));
                        break;
                    case 5:  //设置
                        startActivity(new Intent(getActivity(), SettingActivity.class));
                        break;
                    case 6:  //联系我们

                        break;
                    case 7:  //分享软件

                        break;
                }
            }
        });

    }
    private void addData(){
        mdatas = new ArrayList<MyInfoEnity>();
        mdatas.add(new MyInfoEnity("我租了TA",R.mipmap.my_icon_wozuta));
        mdatas.add(new MyInfoEnity("TA租了我",R.mipmap.my_icon_tazuwo));
        mdatas.add(new MyInfoEnity("报价",R.mipmap.my_icon_baojia));
        mdatas.add(new MyInfoEnity("技能服务",R.mipmap.my_icon_jineng));
        mdatas.add(new MyInfoEnity("我的钱包",R.mipmap.my_icon_qianbao));
        mdatas.add(new MyInfoEnity("设置",R.mipmap.my_icon_setting));
        mdatas.add(new MyInfoEnity("联系我们",R.mipmap.my_icon_lianxi));
        mdatas.add(new MyInfoEnity("分享软件",R.mipmap.my_icon_wozuta));
    }

    private void initView(View view) {
        img_head = (ImageView) view.findViewById(R.id.img_head);
        lay_edit = (LinearLayout) view.findViewById(R.id.lay_edit);
        lv_my = (ListView) view.findViewById(R.id.lv_my);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lay_edit: //跳转至编辑
                startActivity(new Intent(getActivity(), EditDataActivity.class));
                break;
        }
    }


}
