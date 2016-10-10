package com.dingzhi.miaohui.enity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SRDZ on 2016/9/1.
 */
public class WoBaoTaEnity implements Parcelable {
    private String imghead;//头像
    private String name;//名字
    private String huodong;//活动内容
    private int type; //状态：1.待服务，2.已同意，3.以拒绝.
    private int sex; //性别 1.男，2.女
    private String date; //时间
    private int baojia; //报价
    private int age;//年龄

    public WoBaoTaEnity(String imghead, String name, String huodong, int type, int sex, String date, int baojia, int age) {
        this.imghead = imghead;
        this.name = name;
        this.huodong = huodong;
        this.type = type;
        this.sex = sex;
        this.date = date;
        this.baojia = baojia;
        this.age = age;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImghead() {
        return imghead;
    }

    public void setImghead(String imghead) {
        this.imghead = imghead;
    }

    public String getHuodong() {
        return huodong;
    }

    public void setHuodong(String huodong) {
        this.huodong = huodong;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBaojia() {
        return baojia;
    }

    public void setBaojia(int baojia) {
        this.baojia = baojia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imghead);
        dest.writeString(this.name);
        dest.writeString(this.huodong);
        dest.writeInt(this.type);
        dest.writeInt(this.sex);
        dest.writeString(this.date);
        dest.writeInt(this.baojia);
        dest.writeInt(this.age);
    }

    protected WoBaoTaEnity(Parcel in) {
        this.imghead = in.readString();
        this.name = in.readString();
        this.huodong = in.readString();
        this.type = in.readInt();
        this.sex = in.readInt();
        this.date = in.readString();
        this.baojia = in.readInt();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<WoBaoTaEnity> CREATOR = new Parcelable.Creator<WoBaoTaEnity>() {
        @Override
        public WoBaoTaEnity createFromParcel(Parcel source) {
            return new WoBaoTaEnity(source);
        }

        @Override
        public WoBaoTaEnity[] newArray(int size) {
            return new WoBaoTaEnity[size];
        }
    };
}
