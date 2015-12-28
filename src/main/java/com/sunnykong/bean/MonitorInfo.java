package com.sunnykong.bean;

/**
 * Created with IntelliJ IDEA.
 * User: a
 * Date: 15-5-12
 * Time: 下午3:00
 * To change this template use File | Settings | File Templates.
 */
public class MonitorInfo {
    private String monitorcompanyname;
    private String homepageaddr;
    private String monitorjs;
    private String monitorrequestaddr;

    public MonitorInfo(String monitorcompanyname, String homepageaddr, String monitorjs, String monitorrequestaddr) {
        this.monitorcompanyname = monitorcompanyname;
        this.homepageaddr = homepageaddr;
        this.monitorjs = monitorjs;
        this.monitorrequestaddr = monitorrequestaddr;
    }

    public MonitorInfo() {
    }

    public String getMonitorcompanyname() {
        return monitorcompanyname;
    }

    public void setMonitorcompanyname(String monitorcompanyname) {
        this.monitorcompanyname = monitorcompanyname;
    }

    public String getHomepageaddr() {
        return homepageaddr;
    }

    public void setHomepageaddr(String homepageaddr) {
        this.homepageaddr = homepageaddr;
    }

    public String getMonitorjs() {
        return monitorjs;
    }

    public void setMonitorjs(String monitorjs) {
        this.monitorjs = monitorjs;
    }

    public String getMonitorrequestaddr() {
        return monitorrequestaddr;
    }

    public void setMonitorrequestaddr(String monitorrequestaddr) {
        this.monitorrequestaddr = monitorrequestaddr;
    }

    @Override
    public String toString() {
        return "MonitorInfo{" +
                "monitorcompanyname='" + monitorcompanyname + '\'' +
                ", homepageaddr='" + homepageaddr + '\'' +
                ", monitorjs='" + monitorjs + '\'' +
                ", monitorrequestaddr='" + monitorrequestaddr + '\'' +
                '}';
    }
}
