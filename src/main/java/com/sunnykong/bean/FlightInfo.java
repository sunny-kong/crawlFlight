package com.sunnykong.bean;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by xj on 15-12-8.
 */
public class FlightInfo {
    protected int id;
    protected String parentname;
    protected String flightNo;
    protected Timestamp departuretime;
    protected Timestamp landingtime;
    protected double price;
    protected String departurecity;
    protected String landingcity;
    protected Timestamp optiontime;

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

    public Timestamp getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(Timestamp departuretime) {
        this.departuretime = departuretime;
    }

    public Timestamp getLandingtime() {
        return landingtime;
    }

    public void setLandingtime(Timestamp landingtime) {
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

    public Timestamp getOptiontime() {
        return optiontime;
    }

    public void setOptiontime(Timestamp optiontime) {
        this.optiontime = optiontime;
    }

    public FlightInfo(int id, String parentname, String flightNo, Timestamp departuretime, Timestamp landingtime, double price, String departurecity, String landingcity, Timestamp optiontime) {
        this.id = id;
        this.parentname = parentname;
        this.flightNo = flightNo;
        this.departuretime = departuretime;
        this.landingtime = landingtime;
        this.price = price;
        this.departurecity = departurecity;
        this.landingcity = landingcity;
        this.optiontime = optiontime;
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
                ", optiontime=" + optiontime +
                '}';
    }
}
