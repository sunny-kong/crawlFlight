package com.sunnykong.service;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;

import java.io.IOException;
import java.util.Date;

/**
 * Created by xj on 15-12-8.
 */
public interface CrawlFlightService {
    public FlightInfo crawl(AirPortCity from, AirPortCity to, Date date) throws IOException;
}
