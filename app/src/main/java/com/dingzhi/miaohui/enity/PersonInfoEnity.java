package com.dingzhi.miaohui.enity;

/**
 * Created by SRDZ on 2016/9/19.
 */
public class PersonInfoEnity {
    private String infoname;
    private String infocontext;

    public PersonInfoEnity(String infoname, String infocontext){
        this.infoname = infoname;
        this.infocontext = infocontext;
    }

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname;
    }

    public String getInfocontext() {
        return infocontext;
    }

    public void setInfocontext(String infocontext) {
        this.infocontext = infocontext;
    }
}
