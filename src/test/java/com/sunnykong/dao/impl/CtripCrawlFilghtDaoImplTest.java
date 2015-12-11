package com.sunnykong.dao.impl;

import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-12-9.
 */
public class CtripCrawlFilghtDaoImplTest {
    CrawlFlightDao dao = new CtripCrawlFlightDaoImpl();
    @Test
    public void ctripFlightInfoDaoTest() throws ParseException {
        List<FlightInfo> flightInfos=new ArrayList<FlightInfo>();
        FlightInfo flightInfo=new FlightInfo();
        flightInfo.setFlightNo("JD5119");
        flightInfo.setDeparturecity("呼和浩特(HET)");
        flightInfo.setDeparturetime(Timestamp.valueOf("2016-02-06 09:05:00"));
        flightInfo.setLandingcity("乌鲁木齐(URC)");
        flightInfo.setLandingtime(Timestamp.valueOf("2016-02-06 12:40:00"));
        flightInfo.setParentname("携程");
        flightInfo.setPrice(1325);
        flightInfo.setOptiontime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString()));
        flightInfos.add(flightInfo);
        System.out.println(flightInfo);
        dao.saveFlightInfo(flightInfos);
    }
    @Test
    public void testfindAllCrawlFightInfo(){
        List<FlightInfo> flightInfoList=dao.findAllFlightInfo();
        for(FlightInfo flightInfo:flightInfoList){
            System.out.println(flightInfo.toString());
        }
    }
}
