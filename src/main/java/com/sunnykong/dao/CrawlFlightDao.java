package com.sunnykong.dao;

import com.sunnykong.bean.FlightInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public interface CrawlFlightDao {
//    public void saveFlightInfo(List<FlightInfo> flightInfoList);
    public void saveFlightInfo(FlightInfo flightInfo);
    public List<FlightInfo> findAllFlightInfo();
    public FlightInfo findFlightInfoByUniqueKey(String flightno,Timestamp departuretime,Timestamp landingtime,double price);
    public double findLowPrice(String flightno, Timestamp timeRange);

    List<FlightInfo> findFlightInfoByDay(Timestamp day);

    List<FlightInfo> findLowPriceFlightInfoByDay();


    List<Timestamp> findOptionTimes();

    List<Timestamp> findDepartureTimes();

    List<FlightInfo> findFlightInfoByOptionTimeAndDepartureTime(String optionStartTime, String optionEndTime, String departureStartTime, String departureEndTime);

    List<Timestamp> findOptionTimesByHourse();
}
