package com.sunnykong.bean;


import java.util.Date;

/**
 * Created by xj on 15-12-8.
 */
public class FlightInfo {
    protected int id;
    protected String parentname;
    protected String flightNo;
    protected Date departuretime;
    protected Date landingtime;
    protected double price;
    protected String departurecity;
    protected String landingcity;

    public FlightInfo(int id, String parentname, String flightNo, Date departuretime, Date landingtime, double price, String departurecity, String landingcity) {
        this.id = id;
        this.parentname = parentname;
        this.flightNo = flightNo;
        this.departuretime = departuretime;
        this.landingtime = landingtime;
        this.price = price;
        this.departurecity = departurecity;
        this.landingcity = landingcity;
    }

    public FlightInfo() {
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "id=" + id +
                ", parentname='" + parentname + '\'' +
                ", flightNo='" + flightNo + '\'' +
                ", departuretime=" + departuretime +
                ", landingtime=" + landingtime +
                ", price=" + price +
                ", departurecity='" + departurecity + '\'' +
                ", landingcity='" + landingcity + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeparturecity() {
        return departurecity;
    }

    public void setDeparturecity(String departurecity) {
        this.departurecity = departurecity;
    }

    public String getLandingcity() {
        return landingcity;
    }

    public void setLandingcity(String landingcity) {
        this.landingcity = landingcity;
    }
}
