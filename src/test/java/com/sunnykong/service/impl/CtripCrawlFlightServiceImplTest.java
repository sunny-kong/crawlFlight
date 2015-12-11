package com.sunnykong.service.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.dao.impl.CtripCrawlFlightDaoImpl;
import com.sunnykong.service.CrawlFlightService;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-12-8.
 */
public class CtripCrawlFlightServiceImplTest {

    @Test
    public void testCtripCrawl() throws Exception {
/*        CrawlFlightService crawlFlightService= new CtripCrawlFlightServiceImpl();
        Date time=new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-14");
        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.URC, AirPortCity.HET, time);
//        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.BJS, AirPortCity.URC, time);
//        CrawlFlightDao dao=new CtripCrawlFlightDaoImpl();
//        dao.saveFlightInfo(flightInfoList);
        for(FlightInfo flightIn:flightInfoList){
            System.out.println(flightIn);
        }*/
        CrawlFlightService crawlFlightService= new CtripCrawlFlightServiceImpl();
        Timestamp date=new Timestamp(new Date().getTime());
        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC,date);
        for(FlightInfo flightIn:flightInfoList){
            System.out.println(flightIn);
        }

    }
}