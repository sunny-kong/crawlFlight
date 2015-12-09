package com.sunnykong.dao;

import com.sunnykong.bean.FlightInfo;

import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public interface CrawlFlightDao {
    public void saveFlightInfo(List<FlightInfo> flightInfoList);
}
