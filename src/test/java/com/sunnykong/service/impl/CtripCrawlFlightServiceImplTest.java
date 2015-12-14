package com.sunnykong.service.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.dao.impl.CtripCrawlFlightDaoImpl;
import com.sunnykong.service.CrawlFlightService;
import org.junit.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-12-8.
 */
public class CtripCrawlFlightServiceImplTest {
    CrawlFlightService crawlFlightService= new CtripCrawlFlightServiceImpl();
    @Test
    public void testCtripCrawl() throws Exception {

        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.URC, AirPortCity.HET, "2016-02-14");
//        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.BJS, AirPortCity.URC, time);
        CrawlFlightDao dao=new CtripCrawlFlightDaoImpl();
        for(FlightInfo flightInfo:flightInfoList){
            crawlFlightService.saveFlightInfo(flightInfo);
            System.out.println(flightInfo);
        }
       /* CrawlFlightService crawlFlightService= new CtripCrawlFlightServiceImpl();
        List<FlightInfo> flightInfoList=crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC,"2016-02-05");
        for(FlightInfo flightIn:flightInfoList){
            System.out.println(flightIn);
        }*/

    }
    @Test
    public void testCtripCrawlFlightInfoByDateArray(){
        String[] dataArry = new String[]{"2016-02-04","2016-02-05","2016-02-06"};
        for(String date:dataArry){
            List<FlightInfo> flightInfoList= null;
            try {
                flightInfoList = crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC,date);
                System.out.println(flightInfoList);
                for(FlightInfo flightInfo:flightInfoList){
                    crawlFlightService.saveFlightInfo(flightInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}