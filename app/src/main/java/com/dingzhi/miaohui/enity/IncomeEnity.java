package com.dingzhi.miaohui.enity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SRDZ on 2016/9/8.
 */
public class IncomeEnity implements Parcelable {
    private int type;//1,收入  2,支出  3,退款  4,提现
    private String huodong; //逛街，看电影
    private int money; //钱
    private String date; //日期 1998-03-9
    private String time; //时间 17:98:90
    private int huodongtype; // 0,null 1,线上  2,线下
    private int moneytype; //0,没有提现 1,处理中   2,成功

    public IncomeEnity(int type,String huodong,int money,String date,String time,int huodongtype,int moneytype){
        this.type = type;
        this.huodong = huodong;
        this.money = money;
        this.date = date;
        this.time = time;
        this.huodongtype = huodongtype;
        this.moneytype = moneytype;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHuodong() {
        return huodong;
    }

    public void setHuodong(String huodong) {
        this.huodong = huodong;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHuodongtype() {
        return huodongtype;
    }

    public void setHuodongtype(int huodongtype) {
        this.huodongtype = huodongtype;
    }

    public int getMoneytype() {
        return moneytype;
    }

    public void setMoneytype(int moneytype) {
        this.moneytype = moneytype;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.huodong);
        dest.writeInt(this.money);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeInt(this.huodongtype);
        dest.writeInt(this.moneytype);
    }

    protected IncomeEnity(Parcel in) {
        this.type = in.readInt();
        this.huodong = in.readString();
        this.money = in.readInt();
        this.date = in.readString();
        this.time = in.readString();
        this.huodongtype = in.readInt();
        this.moneytype = in.readInt();
    }

    public static final Parcelable.Creator<IncomeEnity> CREATOR = new Parcelable.Creator<IncomeEnity>() {
        @Override
        public IncomeEnity createFromParcel(Parcel source) {
            return new IncomeEnity(source);
        }

        @Override
        public IncomeEnity[] newArray(int size) {
            return new IncomeEnity[size];
        }
    };
}
