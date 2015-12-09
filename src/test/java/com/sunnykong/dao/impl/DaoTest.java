package com.sunnykong.dao.impl;

import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.dao.impl.CrawlFlightDaoImpl;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xj on 15-12-9.
 */
public class DaoTest {
    @Test
    public void ctripFlightInfoDaoTest() throws ParseException {
        CrawlFlightDao dao = new CrawlFlightDaoImpl();
        List<FlightInfo> flightInfos=new ArrayList<FlightInfo>();
        FlightInfo flightInfo=new FlightInfo();
        flightInfo.setFlightNo("JD5119");
        flightInfo.setDeparturecity("呼和浩特(HET)");
        flightInfo.setDeparturetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2016-02-06 09:05"));
        flightInfo.setLandingcity("乌鲁木齐(URC)");
        flightInfo.setLandingtime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2016-02-06 12:40"));
        flightInfo.setParentname("携程");
        flightInfo.setPrice(1325);
        flightInfos.add(flightInfo);
        dao.saveFlightInfo(flightInfos);
    }
}
