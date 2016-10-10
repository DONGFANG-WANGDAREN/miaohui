package com.dingzhi.miaohui.enity;

/**
 * Created by SRDZ on 2016/9/19.
 */
public class PersonInterestEnity {


    private int interestName;//1,爱好2,应用3,运动4,音乐5,书
    private String interestContext;

    public PersonInterestEnity(int interestName, String interestContext){
        this.interestName = interestName;
        this.interestContext = interestContext;
    }

    public String getInterestContext() {
        return interestContext;
    }

    public void setInterestContext(String interestContext) {
        this.interestContext = interestContext;
    }

    public int getInterestName() {

        return interestName;
    }

    public void setInterestName(int interestName) {
        this.interestName = interestName;
    }
}
