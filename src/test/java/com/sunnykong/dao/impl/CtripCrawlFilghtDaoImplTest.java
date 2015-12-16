package com.sunnykong.dao.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.utils.MapToBeanUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xj on 15-12-9.
 */
public class CtripCrawlFilghtDaoImplTest {
    CrawlFlightDao dao = new CtripCrawlFlightDaoImpl();

    @Test
    public void ctripFlightInfoDaoTest() throws ParseException {
        List<FlightInfo> flightInfos = new ArrayList<FlightInfo>();
        FlightInfo flightInfo = new FlightInfo();
        flightInfo.setFlightNo("JD5119");
        flightInfo.setDeparturecity("呼和浩特(HET)");
        flightInfo.setDeparturetime(Timestamp.valueOf("2016-02-06 09:05:00"));
        flightInfo.setLandingcity("乌鲁木齐(URC)");
        flightInfo.setLandingtime(Timestamp.valueOf("2016-02-06 12:40:00"));
        flightInfo.setParentname("携程");
        flightInfo.setPrice(1325);
        flightInfo.setOptiontime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString()));
        dao.saveFlightInfo(flightInfo);
    }

    @Test
    public void testfindAllCrawlFightInfo() {
        List<FlightInfo> flightInfoList = dao.findAllFlightInfo();
        for (FlightInfo flightInfo : flightInfoList) {
            System.out.println(flightInfo.toString());
        }
    }

    @Test
    public void testfindCrawlFightInfoByUniqueKey() {
        FlightInfo flightInfo = dao.findFlightInfoByUniqueKey("CZ0011", Timestamp.valueOf("2016-02-06 18:55:00"), Timestamp.valueOf("2016-02-06 22:30:00"), 981);
        if (flightInfo != null) {
            System.out.println(flightInfo.getFlightNo());
        } else {
            System.out.println("该航班不存在！");
        }

    }

    @Test
    public void MapToBeanUtil() {
        List<FlightInfo> flightInfoList = dao.findAllFlightInfo();
        Map<String, List<FlightInfo>> flightMap = MapToBeanUtil.buildMap(flightInfoList);
        System.out.println(flightMap.keySet());
        for (String key : flightMap.keySet()) {
            System.out.println(key + ":::::::::::::" + flightMap.get(key));
        }

    }

    @Test
    public void testGetLowPrice() {
        double price = dao.findLowPrice("CA1265", Timestamp.valueOf("2016-02-07 11:30:00"));
        System.out.println("同一航班最低票价为：" + price);
    }
    @Test
    public void testLowPriceByDay(){
        List<FlightInfo> flightInfoList=dao.findLowPriceFlightInfoByDay();
        for(FlightInfo flightInfo:flightInfoList){
            System.out.println(flightInfo);
        }
    }


}
