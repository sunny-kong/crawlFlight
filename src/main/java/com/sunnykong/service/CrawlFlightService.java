package com.sunnykong.service;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-12-8.
 */
public interface CrawlFlightService {
    public List<FlightInfo> crawl(AirPortCity from, AirPortCity to, Date date) throws IOException, ParseException;
}
