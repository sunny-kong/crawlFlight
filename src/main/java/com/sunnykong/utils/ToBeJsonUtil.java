package com.sunnykong.utils;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public class ToBeJsonUtil {
    public static void main(String[] args) throws ParseException, IOException {
        CrawlFlightService crawlFlightService= new CtripCrawlFlightServiceImpl();

        Timestamp time=new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-06").getTime());

        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC, time);
        for(FlightInfo flightIn:flightInfoList){
            System.out.println(flightIn);
        }
    }
}
