package com.dingzhi.miaohui.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;

/**
   * explain 个人详情图片滑动
   * created by TanXin.
   * created date 2016/9/20 9:09.
   */
public class PersonPhotoViewHolder implements Holder<String>{
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtils.getInstance().loadImageView(context, data, imageView);
    }
}
