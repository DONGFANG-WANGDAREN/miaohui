package com.dingzhi.miaohui.enity;

/**
 * Created by SRDZ on 2016/10/9.
 */
public class MovableEnity {
    private String img;
    private String title;
    private String content;

    public MovableEnity(String img,String title,String content){
        this.img = img;
        this.title = title;
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
