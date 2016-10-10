package com.dingzhi.miaohui.nullss;

import com.dingzhi.miaohui.enity.FindEnity;
import com.dingzhi.miaohui.enity.IncomeEnity;
import com.dingzhi.miaohui.enity.MovableEnity;
import com.dingzhi.miaohui.enity.PersonInfoEnity;
import com.dingzhi.miaohui.enity.PersonInterestEnity;
import com.dingzhi.miaohui.enity.WoBaoTaEnity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SRDZ on 2016/9/1.
 */
public class DataProvider
{

    public static List<WoBaoTaEnity> getwozutaList(int page){
        ArrayList<WoBaoTaEnity> arr = new ArrayList<>();
        arr.add(new WoBaoTaEnity("http://img2.dwstatic.com/lol/1607/333467056501/1469511859510.png","克烈","骑马",1,1,"2000-4-1",6300,10));
        arr.add(new WoBaoTaEnity("http://img5.dwstatic.com/lol/1605/326373421236/1462418224471.jpg","塔莉垭","打野",2,2,"2000-5-1",6300,11));
        arr.add(new WoBaoTaEnity("http://img4.dwstatic.com/lol/1602/320083658274/1456128461876.png","烬","射击",3,1,"2000-6-1",6300,12));
        arr.add(new WoBaoTaEnity("http://pic3.duowan.com/lol/1209/212665448739/212665677660.jpg","卡'兹克","打野",1,1,"2000-7-1",6300,13));
        arr.add(new WoBaoTaEnity("http://img2.dwstatic.com/lol/1411/279641746944/1415686553224.jpg","卡莉丝塔","射击",3,2,"2000-8-1",6300,14));
        arr.add(new WoBaoTaEnity("http://img4.dwstatic.com/lol/1310/246295394773/1382341114754.png","裟娜","辅助",2,2,"2000-9-1",3150,15));
        arr.add(new WoBaoTaEnity("http://img4.dwstatic.com/lol/1411/279647578275/1415692794873.png","迦娜","辅助",1,2,"2000-10-1",1350,16));
        arr.add(new WoBaoTaEnity("http://img1.dwstatic.com/lol/1407/269606339574/1405651153711.png","卡尔萨斯","重担",3,1,"2000-11-1",6300,17));
        arr.add(new WoBaoTaEnity("http://img5.dwstatic.com/lol/1411/279647578275/1415692869825.png","嘉文四世","打野",1,1,"2000-12-1",4800,18));
        arr.add(new WoBaoTaEnity("http://img1.dwstatic.com/lol/1311/249491473153/1385536285032.png","亚索","中单",3,1,"2001-6-1",6300,19));
        arr.add(new WoBaoTaEnity("http://img2.dwstatic.com/lol/1305/231871544650/231871561051.png","丽桑卓","中单",2,2,"2001-2-1",6300,20));
        return arr;
    }


    public static List<IncomeEnity> getIncomeAll(){
        ArrayList<IncomeEnity> arr = new ArrayList<>();
        arr.add(new IncomeEnity(1,"逛街",100,"2010-01-01","09:11:11",2,0));
        arr.add(new IncomeEnity(2,"看电影",200,"2011-01-01","10:11:11",2,0));
        arr.add(new IncomeEnity(3,"打代码",300,"2012-01-01","11:11:11",1,0));
        arr.add(new IncomeEnity(4,"改BUG",400,"2013-01-01","12:11:11",0,1));
        arr.add(new IncomeEnity(4,"",500,"2014-01-01","13:11:11",0,2));
        return arr;
    }
    public static List<IncomeEnity> getIncomechu(){
        ArrayList<IncomeEnity> arr = new ArrayList<>();
        arr.add(new IncomeEnity(2,"看电影",200,"2011-01-01","10:11:11",2,0));
        arr.add(new IncomeEnity(4,"改BUG",400,"2013-01-01","12:11:11",0,1));
        arr.add(new IncomeEnity(4,"",500,"2014-01-01","13:11:11",0,2));
        return arr;
    }
    public static List<IncomeEnity> getIncomeru(){
        ArrayList<IncomeEnity> arr = new ArrayList<>();
        arr.add(new IncomeEnity(1,"逛街",100,"2010-01-01","09:11:11",2,0));
        arr.add(new IncomeEnity(3,"打代码",300,"2012-01-01","11:11:11",1,0));
        return arr;
    }


    public static List<FindEnity> getfindlist(int page){
        ArrayList<FindEnity> arr = new ArrayList<>();
        arr.add(new FindEnity("胡欣语", 21, "http://d.hiphotos.baidu.com/image/h%3D360/sign=28a64cbda41ea8d395227202a70b30cf/43a7d933c895d143b6171f2571f082025aaf0756.jpg"));
        arr.add(new FindEnity("Norway", 21, "http://f.hiphotos.baidu.com/image/h%3D360/sign=827c3174af345982da8ae3943cf5310b/9358d109b3de9c82c4f95c8f6e81800a19d84315.jpg"));
        arr.add(new FindEnity("王清玉", 18, "http://c.hiphotos.baidu.com/image/h%3D360/sign=9917c703fadcd100d29cfe27428a47be/78310a55b319ebc415951b978026cffc1f1716ca.jpg"));
        arr.add(new FindEnity("测试1", 21, "http://a.hiphotos.baidu.com/image/h%3D360/sign=897344f534d3d539de3d09c50a86e927/ae51f3deb48f8c54d477a12438292df5e0fe7f10.jpg"));
        arr.add(new FindEnity("测试2", 21, "http://e.hiphotos.baidu.com/image/h%3D360/sign=9c4d0179af4bd1131bcdb1346aaea488/7af40ad162d9f2d303f4c1e5abec8a136227ccd7.jpg"));
        return arr;

    }


    public static  List<PersonInfoEnity> getinfo(){
        ArrayList<PersonInfoEnity> arr = new ArrayList<>();
        arr.add(new PersonInfoEnity("星座","水瓶座"));
        arr.add(new PersonInfoEnity("行业","互联网"));
        arr.add(new PersonInfoEnity("公司","阿里巴巴"));
        arr.add(new PersonInfoEnity("地区","浙江"));
        arr.add(new PersonInfoEnity("经常出没","杭州"));
        arr.add(new PersonInfoEnity("个性签名","静静静静静"));
        return arr;
    }

    public static List<PersonInterestEnity> getinterest(){
        ArrayList<PersonInterestEnity> arr = new ArrayList<>();
        arr.add(new PersonInterestEnity(1,"唱K,LOL"));
        arr.add(new PersonInterestEnity(2,"微博,微信"));
        arr.add(new PersonInterestEnity(3,"篮球,足球"));
        arr.add(new PersonInterestEnity(4,"我好想在哪见过你,来日方长,沙龙"));
        arr.add(new PersonInterestEnity(5,"三年高考五年模拟,毛泽东思想"));
        return arr;
    }

    public static  List<MovableEnity> getmovable(){
        ArrayList<MovableEnity> arr = new ArrayList<>();
        arr.add(new MovableEnity("http://img3.imgtn.bdimg.com/it/u=3902625037,1048505220&fm=21&gp=0.jpg","战斗之夜","三倍经验，三倍金钱"));
        arr.add(new MovableEnity("http://img3.imgtn.bdimg.com/it/u=4133366426,868782113&fm=21&gp=0.jpg","战斗之夜","三倍经验，三倍金钱"));
        arr.add(new MovableEnity("http://img2.imgtn.bdimg.com/it/u=2574058318,2411636996&fm=21&gp=0.jpg","战斗之夜","三倍经验，三倍金钱"));
        return arr;
    }
}
