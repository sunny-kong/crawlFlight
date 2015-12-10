package com.sunnykong.bean;

/**
 * Created by xj on 15-12-8.
 */
public enum AirPortCity {
    HET{public  String getName(){return "呼和浩特";}},
    URC{public  String getName(){return "乌鲁木齐";}},
    BJS{public  String getName(){return "北京";}},
    SHA{public  String getName(){return "上海";}};

    public abstract String getName();
}
