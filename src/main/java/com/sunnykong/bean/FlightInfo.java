package com.sunnykong.bean;

/**
 * Created by xj on 15-12-8.
 */
public class FlightInfo {
    protected String info;

    @Override
    public String toString() {
        return "FlightInfo{" +
                "info='" + info + '\'' +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
