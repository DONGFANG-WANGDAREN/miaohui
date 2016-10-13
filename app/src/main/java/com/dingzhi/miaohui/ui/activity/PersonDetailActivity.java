package com.dingzhi.miaohui.ui.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.CheckSelectAdapter;
import com.dingzhi.miaohui.adapter.CheckSelectWhiteAdapter;
import com.dingzhi.miaohui.adapter.PersonInfoAdapter;
import com.dingzhi.miaohui.adapter.PersonInterestAdapter;
import com.dingzhi.miaohui.nullss.DataProvider;
import com.dingzhi.miaohui.viewholder.PersonPhotoViewHolder;
import com.dingzhi.miaohui.widget.DividerItemDecoration;
import com.dingzhi.miaohui.widget.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

 /**
  * 文件名：PersonDetailActivity.
  * 版权所有：SRDZ
  * 创建人：TANXIN
  * 创建日期:2016/10/13 16:16.
  * 功能描述: 个人详情
  * 函数/方法说明:
  */
public class PersonDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_name)
    TextView tvName;    //名字
    @BindView(R.id.tv_age)
    TextView tvAge;     //年龄
    @BindView(R.id.img_sex)
    ImageView imgSex;   //性别
    @BindView(R.id.tv_distance)
    TextView tvDistance;    //距离
    @BindView(R.id.tv_lasttime)
    TextView tvLasttime;    //上一次活跃时间
    @BindView(R.id.btn_zuta)
    Button btnZuta;        //租他
    @BindView(R.id.btn_baojia)
    Button btnBaojia;       //报价
    @BindView(R.id.lay_friends)
    LinearLayout layFriends;    //朋友圈
    @BindView(R.id.lay_skill)
    LinearLayout laySkill;  //技能
    @BindView(R.id.tv_onlinetime)
    TextView tvOnlinetime;  //出租时间——线上
    @BindView(R.id.tv_thelinetime)
    TextView tvThelinetime; //出租时间——线下
    @BindView(R.id.lay_time)
    LinearLayout layTime;   //出租时间
    @BindView(R.id.lay_myinfo)
    LinearLayout layMyinfo; //  我的信息
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;      //微信
    @BindView(R.id.lay_socially)
    LinearLayout laySocially;   //社交
    @BindView(R.id.lay_interest)
    LinearLayout layInterest;   //兴趣
    @BindView(R.id.tv_synopsis)
    TextView tvSynopsis;    //简介
    @BindView(R.id.lay_synopsis)
    LinearLayout laySynopsis;//简介
    @BindView(R.id.lv_list)
    ListView lvList; //我的信息列表
    @BindView(R.id.CBanner)
    ConvenientBanner CBanner;
    @BindView(R.id.recycle_interest)
    RecyclerView recycleInterest;

     private Dialog dialog;
     private HashMap<Integer,String> hashMap = new HashMap<>();
     private CheckSelectWhiteAdapter skillAdapter;
     private List<String> skillList = new ArrayList<>();
     private List<String> onlineList;
     private List<String> thelineList;

    @Override
    protected int setLayout() {
        return R.layout.activity_person_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("静静静静静");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setbanner();
        setinfo();
        setinterest();
    }

    /**
     * explain 初始化照片
     * created by TanXin.
     * created date 2016/9/20 10:48.
     */
    private void setbanner() {
        String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
                "http://img2.3lian.com/2014/f2/37/d/40.jpg",
                "http://img2.3lian.com/2014/f2/37/d/39.jpg",
                "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
                "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};

        List<String> bannerImages = Arrays.asList(images);
        CBanner.setPages(new CBViewHolderCreator<PersonPhotoViewHolder>() {

            @Override
            public PersonPhotoViewHolder createHolder() {
                return new PersonPhotoViewHolder();
            }
        }, bannerImages).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});

    }

    /**
     * explain 初始化我的信息
     * created by TanXin.
     * created date 2016/9/20 14:14.
     */
    private void setinfo() {
        List list = DataProvider.getinfo();
        lvList.setAdapter(new PersonInfoAdapter(this, list));
    }

    /**
     * explain 初始化我的兴趣
     * created by TanXin.
     * created date 2016/9/20 14:15.
     */
    private void setinterest() {
        List list = DataProvider.getinterest();
        recycleInterest.setLayoutManager(new LinearLayoutManager(this));
        recycleInterest.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recycleInterest.setAdapter(new PersonInterestAdapter(this,list));
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

    @OnClick({R.id.btn_zuta, R.id.btn_baojia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zuta: //跳转至订单详情
                startActivity(new Intent(this,ConfirmOrderActivity.class));
                break;
            case R.id.btn_baojia:
                showBaoJia(); //开启报价对话框
                break;
        }
    }


      /**
        * 函数名： showBaoJia
        * 创建人： TanXin.
        * 创建日期： 2016/10/13 16:17.
        * 功能描述：报价对话框
        */
     public void showBaoJia(){
         dialog = new Dialog(PersonDetailActivity.this,R.style.MyDialog);
         dialog.setCancelable(false);

         View view = getLayoutInflater().inflate(R.layout.dialog_baojia,null);
         RadioGroup radio_skill = (RadioGroup) view.findViewById(R.id.radio_skill);
         RadioButton radio_online = (RadioButton) view.findViewById(R.id.radio_online);
         RadioButton radio_theline = (RadioButton) view.findViewById(R.id.radio_theline);
         ExpandableHeightGridView grid_skill = (ExpandableHeightGridView) view.findViewById(R.id.grid_skill);
         final EditText ed_money = (EditText) view.findViewById(R.id.ed_money);
         Button btn_sure = (Button) view.findViewById(R.id.btn_sure);
         Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

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
         skillAdapter = new CheckSelectWhiteAdapter(this,skillList,hashMap);
         grid_skill.setAdapter(skillAdapter);

         dialog.setContentView(view);

         grid_skill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 hashMap.clear();
                 if (skillAdapter.isBlank(hashMap.get(i))) {
                     hashMap.put(i, skillList.get(i));
                 } else {
                     hashMap.remove(i);
                 }
                 skillAdapter.change(skillList, hashMap);
             }
         });

         radio_skill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 switch (i) {
                     case R.id.radio_online:
                         if (onlineList.size()>0){
                             skillList.clear();
                             hashMap.clear();
                             for(int j = 0;j<onlineList.size();j++){
                                 skillList.add(onlineList.get(j));
                             }
                             skillAdapter.change(skillList,hashMap);
                         }
                         break;
                     case R.id.radio_theline:
                         if (thelineList.size()>0){
                             skillList.clear();
                             hashMap.clear();
                             for(int j = 0;j<thelineList.size();j++){
                                 skillList.add(thelineList.get(j));
                             }
                             skillAdapter.change(skillList,hashMap);
                         }
                         break;
                 }
             }
         });

         btn_sure.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                //获取技能
                 String skill = "";
                 Iterator iters = hashMap.keySet().iterator();
                 while (iters.hasNext()) {
                     int key = (Integer) iters.next();
                     String val = hashMap.get(key);
                     skill += (val);
                 }
                 String money = ed_money.getText().toString().trim();
                 if (money.isEmpty()) {
                     Toast.makeText(PersonDetailActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                 } else if (skill.isEmpty()) {
                     Toast.makeText(PersonDetailActivity.this, "请选择技能", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(PersonDetailActivity.this, skill+"~"+money, Toast.LENGTH_SHORT).show();
                     skillList.clear();
                     hashMap.clear();
                     dialog.dismiss();
                     dialog = null;

                 }
             }
         });
         btn_cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 skillList.clear();
                 hashMap.clear();
                 dialog.dismiss();
                 dialog = null;
             }
         });

         dialog.show();
     }


}
