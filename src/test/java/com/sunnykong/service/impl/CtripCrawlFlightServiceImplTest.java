package com.sunnykong.service.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by xj on 15-12-8.
 */
public class CtripCrawlFlightServiceImplTest {

    @Test
    public void testCrawl() throws Exception {
        CrawlFlightService service = new CtripCrawlFlightServiceImpl();
        FlightInfo flightInfo = service.crawl(AirPortCity.HET, AirPortCity.URC, new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-05"));
        System.out.println(flightInfo);
    }
}