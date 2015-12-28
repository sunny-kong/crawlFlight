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
    public List<FlightInfo> findFlightInfoByDay(Timestamp day);
    public FlightInfo findFlightInfoByUniqueKey(String flightno,Timestamp departuretime,Timestamp landingtime,double price);
    public FlightInfo findFlightInfoByUniqueKeyByDay(String flightno,Timestamp departuretime,Timestamp landingtime,double price);
    public double findLowPrice(String flightno, Timestamp timeRange);

    List<FlightInfo> findLowPriceFlightInfoByDay();

    List<Timestamp> findOptionTimes();

    List<Timestamp> findDepartureTimes();

    List<FlightInfo> findFlightInfoByOptionTimeAndDepartureTime(String optionStartTime, String optionEndTime, String departureStartTime, String departureEndTime);
}
