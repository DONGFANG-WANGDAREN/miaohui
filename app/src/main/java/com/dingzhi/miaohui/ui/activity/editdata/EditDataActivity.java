package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.ui.activity.loginorres.SelectPicActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件名：EditDataActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:27.
 * 功能描述: 编辑页面
 * 函数/方法说明:
 */
public class EditDataActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_01)
    ImageView img01;
    @BindView(R.id.img_02)
    ImageView img02;
    @BindView(R.id.img_03)
    ImageView img03;
    @BindView(R.id.img_04)
    ImageView img04;
    @BindView(R.id.img_05)
    ImageView img05;
    @BindView(R.id.img_06)
    ImageView img06;
    @BindView(R.id.img_sex)
    ImageView imgSex;//性别
    @BindView(R.id.tv_name)
    TextView tvName;//名字
    @BindView(R.id.tv_age)
    TextView tvAge;//年龄
    @BindView(R.id.lay_myinfo)
    RelativeLayout layMyinfo;
    @BindView(R.id.tv_constellation)
    TextView tvConstellation;//星座
    @BindView(R.id.tv_industry)
    TextView tvIndustry;//行业
    @BindView(R.id.tv_company)
    TextView tvCompany;//公司
    @BindView(R.id.tv_area)
    TextView tvArea;//地区
    @BindView(R.id.tv_haunt)
    TextView tvHaunt;//经常出没
    @BindView(R.id.tv_signature)
    TextView tvSignature;//个性签名
    @BindView(R.id.tv_hobby)
    TextView tvHobby;//爱好
    @BindView(R.id.tv_app)
    TextView tvApp;//应用
    @BindView(R.id.tv_sport)
    TextView tvSport;//运动
    @BindView(R.id.tv_music)
    TextView tvMusic;//音乐
    @BindView(R.id.tv_book)
    TextView tvBook;//书
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;//微信
    @BindView(R.id.lay_info)
    LinearLayout layInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_editdata;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("静静静静静");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //返回
                finish();
                break;
            case R.id.action_complete: //完成
                Toast.makeText(EditDataActivity.this, "sure", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
    @OnClick({R.id.img_01, R.id.img_02, R.id.img_03, R.id.img_04, R.id.img_05, R.id.img_06,R.id.lay_myinfo, R.id.tv_industry, R.id.tv_area, R.id.tv_haunt, R.id.tv_signature, R.id.tv_weixin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_01: //跳转至拍照界面
                Intent PicIntent1 = new Intent(EditDataActivity.this, SelectPicActivity.class);
                startActivityForResult(PicIntent1, C.REQUEST_CODE_IMG01);
                break;
            case R.id.img_02:   //跳转至拍照界面
                Intent PicIntent2 = new Intent(EditDataActivity.this, SelectPicActivity.class);
                startActivityForResult(PicIntent2, C.REQUEST_CODE_IMG02);
                break;
            case R.id.img_03:   //跳转至拍照界面
                Intent PicIntent3 = new Intent(EditDataActivity.this, SelectPicActivity.class);
                startActivityForResult(PicIntent3, C.REQUEST_CODE_IMG03);
                break;
            case R.id.img_04:   //跳转至拍照界面
                Intent PicIntent4 = new Intent(EditDataActivity.this, SelectPicActivity.class);
                startActivityForResult(PicIntent4, C.REQUEST_CODE_IMG04);
                break;
            case R.id.img_05:   //跳转至拍照界面
                Intent PicIntent5 = new Intent(EditDataActivity.this, SelectPicActivity.class);
                startActivityForResult(PicIntent5, C.REQUEST_CODE_IMG05);
                break;
            case R.id.img_06:   //跳转至拍照界面
                Intent PicIntent6 = new Intent(EditDataActivity.this, SelectPicActivity.class);
                startActivityForResult(PicIntent6, C.REQUEST_CODE_IMG06);
                break;
            case R.id.lay_myinfo:   //跳转至账户信息
                Intent intent3 = new Intent(this, AccountActivity.class);
                intent3.putExtra("tv_name", tvName.getText().toString().trim());
                intent3.putExtra("tv_age", "1999-09-09");
                startActivityForResult(intent3, C.REQUEST_CODE_MYINFO);
                break;
            case R.id.tv_industry:  //跳转至选择行业
                Intent intent4 = new Intent(this, IndustryActivity.class);
                startActivityForResult(intent4, C.REQUEST_CODE_SELECT);
                break;
            case R.id.tv_area:  //跳转至选择家乡
                Intent intent6 = new Intent(this, AreaActivity.class);
                startActivityForResult(intent6, C.REQUEST_CODE_AREA);
                break;
            case R.id.tv_haunt:
                Intent intent5 = new Intent(this, WeiXinActivity.class);
                intent5.putExtra("tv", "");
                intent5.putExtra("title", "经常出没");
                startActivityForResult(intent5, C.REQUEST_CODE_CHUMO);
                break;
            case R.id.tv_signature:
                Intent intent2 = new Intent(this, SignatureActivity.class);
                intent2.putExtra("tv_signature", tvSignature.getText().toString().trim());
                startActivityForResult(intent2, C.REQUEST_CODE_SIGNATURE);
                break;
            case R.id.tv_weixin:
                Intent intent = new Intent(this, WeiXinActivity.class);
                intent.putExtra("tv", tvWeixin.getText().toString().trim());
                intent.putExtra("title", "微信");
                startActivityForResult(intent, C.REQUEST_CODE_WEIXIN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C.REQUEST_CODE_WEIXIN:
                if (resultCode == RESULT_OK) {
                    tvWeixin.setText(data.getExtras().getString("tv"));
                }
            case C.REQUEST_CODE_SIGNATURE:
                if (resultCode == RESULT_OK) {
                    tvSignature.setText(data.getExtras().getString("tv_signature"));
                }
                break;
            case C.REQUEST_CODE_MYINFO:
                if (resultCode == RESULT_OK) {
                    tvName.setText(data.getExtras().getString("tv_names"));
                    tvAge.setText(data.getExtras().getString("tv_age"));
                }
                break;
            case C.REQUEST_CODE_SELECT:
                if (resultCode == RESULT_OK) {
                    tvIndustry.setText(data.getExtras().getString("tv"));
                }
                break;
            case C.REQUEST_CODE_AREA:
                if (resultCode == RESULT_OK) {
                    tvArea.setText(data.getExtras().getString("tv"));
                }
                break;
            case C.REQUEST_CODE_CHUMO:
                if (resultCode == RESULT_OK) {
                    tvHaunt.setText(data.getExtras().getString("tv"));
                }
                break;
            case C.REQUEST_CODE_IMG01:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(img01);
                }
                break;
            case C.REQUEST_CODE_IMG02:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(img02);
                }
                break;
            case C.REQUEST_CODE_IMG03:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(img03);
                }
                break;
            case C.REQUEST_CODE_IMG04:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(img04);
                }
                break;
            case C.REQUEST_CODE_IMG05:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(img05);
                }
                break;
            case C.REQUEST_CODE_IMG06:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(img06);
                }
                break;
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_complete, menu);

        return super.onCreateOptionsMenu(menu);

    }




}
