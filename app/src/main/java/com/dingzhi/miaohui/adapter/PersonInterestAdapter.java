package com.dingzhi.miaohui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.PersonInterestEnity;
import com.dingzhi.miaohui.widget.BGAFlowLayout;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SRDZ on 2016/9/20.
 */
public class PersonInterestAdapter extends RecyclerView.Adapter<PersonInterestAdapter.ViewHolder> {
    private Context context;
    private List<PersonInterestEnity> list;

    public PersonInterestAdapter(Context context, List<PersonInterestEnity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_person_interest, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PersonInterestEnity personInterest = list.get(position);
        String[] sArray = personInterest.getInterestContext().split(",");
        for (int i = 0; i < sArray.length; i++) {
            holder.layFlow.addView(getLabel(sArray[i]), holder.layFlow.getChildCount() - 1, new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
        }
        switch (personInterest.getInterestName()){
            case 1:
                holder.tvName.setBackgroundResource(R.mipmap.icon_hobby);
                break;
            case 2:
                holder.tvName.setBackgroundResource(R.mipmap.icon_app);
                break;
            case 3:
                holder.tvName.setBackgroundResource(R.mipmap.icon_sport);
                break;
            case 4:
                holder.tvName.setBackgroundResource(R.mipmap.icon_music);
                break;
            case 5:
                holder.tvName.setBackgroundResource(R.mipmap.icon_book);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        ImageView tvName;
        @BindView(R.id.lay_flow)
        BGAFlowLayout layFlow;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private TextView getLabel(String text) {
        TextView label = new TextView(context);
        label.setTextColor(Color.WHITE);
        label.setBackgroundResource(GetTagBack());
        label.setGravity(Gravity.CENTER);
        label.setSingleLine(true);
        label.setEllipsize(TextUtils.TruncateAt.END);
        label.setPadding(20, 5, 20, 5);
        label.setText(text);
        return label;
    }


    /**
     * explain 随机选择Tag背景
     * created by TanXin.
     * created date 2016/9/20 14:18.
     */
    public static int GetTagBack(){
        int arr[] = {R.drawable.tag_back_1,R.drawable.tag_back_2,R.drawable.tag_back_3,R.drawable.tag_back_4,
                R.drawable.tag_back_5, R.drawable.tag_back_6,R.drawable.tag_back_7,R.drawable.tag_back_8,
                R.drawable.tag_back_9};
        int len = arr.length;
        Random random = new Random();
        int arrIx = random.nextInt(len-1);
        int num = arr[arrIx];
        return num;
    }
}
