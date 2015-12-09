package com.sunnykong.service.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xj on 15-12-8.
 */
public class CtripCrawlFlightServiceImplTest {

    @Test
    public void testCrawl() throws Exception {
        CrawlFlightService crawlFlightService= new CtripCrawlFlightServiceImpl();
        Date time=new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-06");
        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC, time);
        for(FlightInfo flightIn:flightInfoList){
            System.out.println(flightIn);
            System.out.println(AirPortCity.HET.getName());
        }
    }
}