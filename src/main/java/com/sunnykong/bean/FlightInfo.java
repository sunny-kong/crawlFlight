package com.sunnykong.bean;


import java.util.Date;

/**
 * Created by xj on 15-12-8.
 */
public class FlightInfo {
    protected String parentname;
    protected String flightNo;
/*    protected String departuretime;
    protected String landingtime;*/
    protected Date departuretime;
    protected Date landingtime;
    protected String price;
    protected String departurecity;
    protected String landingcity;

    public String getLandingcity() {
        return landingcity;
    }

    public void setLandingcity(String landingcity) {
        this.landingcity = landingcity;
    }

    public String getDeparturecity() {

        return departurecity;
    }

    public void setDeparturecity(String departurecity) {
        this.departurecity = departurecity;
    }

  /*  public FlightInfo(String parentname, String flightNo, String departuretime, String landingtime, String price, String departurecity, String landingcity) {
        this.parentname = parentname;
        this.flightNo = flightNo;
        this.departuretime = departuretime;
        this.landingtime = landingtime;
        this.price = price;
        this.departurecity = departurecity;
        this.landingcity = landingcity;
    }

    public FlightInfo(String parentname, String flightNo, String departuretime, String landingtime, String price) {
        this.parentname = parentname;
        this.flightNo = flightNo;
        this.departuretime = departuretime;
        this.landingtime = landingtime;
        this.price = price;
    }*/

    public FlightInfo() {
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
/*
    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getLandingtime() {
        return landingtime;
    }

    public void setLandingtime(String landingtime) {
        this.landingtime = landingtime;
    }*/

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public FlightInfo(String parentname, String flightNo, Date departuretime, Date landingtime, String price, String departurecity, String landingcity) {
        this.parentname = parentname;
        this.flightNo = flightNo;
        this.departuretime = departuretime;
        this.landingtime = landingtime;
        this.price = price;
        this.departurecity = departurecity;
        this.landingcity = landingcity;
    }

    public Date getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(Date departuretime) {
        this.departuretime = departuretime;
    }

    public Date getLandingtime() {
        return landingtime;
    }

    public void setLandingtime(Date landingtime) {
        this.landingtime = landingtime;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "parentname='" + parentname + '\'' +
                ", flightNo='" + flightNo + '\'' +
                ", departuretime=" + departuretime +
                ", landingtime=" + landingtime +
                ", price='" + price + '\'' +
                ", departurecity='" + departurecity + '\'' +
                ", landingcity='" + landingcity + '\'' +
                '}';
    }
}
