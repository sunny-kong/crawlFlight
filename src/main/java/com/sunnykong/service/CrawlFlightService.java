package com.sunnykong.service;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-12-8.
 */
public interface CrawlFlightService {
    public List<FlightInfo> crawl(AirPortCity from, AirPortCity to, String date) throws IOException, ParseException;
    public void saveFlightInfo(FlightInfo crawl);
    public List<FlightInfo> findAllFlightInfo();
    public FlightInfo findFlightInfoByUniqueKey(String flightno,Timestamp departuretime,Timestamp landingtime,double price);
    public double findLowPrice(String flightno, Timestamp timeRange);
}
