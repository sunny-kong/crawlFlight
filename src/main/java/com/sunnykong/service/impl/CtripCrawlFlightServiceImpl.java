package com.sunnykong.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.dao.impl.CtripCrawlFlightDaoImpl;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.utils.CtripCrawlFlightUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xj on 15-12-8.
 */
public class CtripCrawlFlightServiceImpl implements CrawlFlightService {
    CrawlFlightDao crawlFlightDao=new CtripCrawlFlightDaoImpl();
    @Override
    public List<FlightInfo> crawl(AirPortCity from, AirPortCity to, String date) throws IOException, ParseException {
        List<Header> headers = CtripCrawlFlightUtil.buildHeaders();
        CloseableHttpClient httpClient = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").setDefaultHeaders(headers).build();
        String url= CtripCrawlFlightUtil.getURL(from, to, date);
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
         List<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
        if (entity != null) {
            String json = EntityUtils.toString(entity);
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(json, Map.class);
           List fisList= (List)map.get("fis");

            for (int i = 0; i < fisList.size(); i++) {
                FlightInfo flightInfo = new FlightInfo();
                flightInfo.setParentname("携程");
                flightInfo.setDeparturecity(String.valueOf(((Map) fisList.get(i)).get("dpc")));
                flightInfo.setLandingcity(String.valueOf(((Map) fisList.get(i)).get("apc")));
                flightInfo.setFlightNo(String.valueOf(((Map) fisList.get(i)).get("fn")));
                flightInfo.setDeparturetime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(((Map) fisList.get(i)).get("dt"))).getTime()));
                flightInfo.setLandingtime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(((Map) fisList.get(i)).get("at"))).getTime()));
                flightInfo.setPrice(Double.parseDouble(String.valueOf(((Map) fisList.get(i)).get("lp"))));
                flightInfo.setOptiontime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                flightInfoList.add(flightInfo);
            }
            EntityUtils.consume(entity);
        }
        return flightInfoList;
    }

    @Override
    public void saveFlightInfo(FlightInfo flightInfo) {
        crawlFlightDao.saveFlightInfo(flightInfo);
//        crawlFlightDao.saveFlightInfo(flightInfoList);
    }

    @Override
    public List<FlightInfo> findAllFlightInfo() {
        return crawlFlightDao.findAllFlightInfo();
    }

    @Override
    public List<FlightInfo> findFlightInfoByDay(Timestamp day) {
        return crawlFlightDao.findFlightInfoByDay(day);
    }

    @Override
    public FlightInfo findFlightInfoByUniqueKey(String flightno, Timestamp departuretime, Timestamp landingtime, double price) {
        return crawlFlightDao.findFlightInfoByUniqueKey(flightno,departuretime,landingtime,price);
    }

    @Override
    public FlightInfo findFlightInfoByUniqueKeyByDay(String flightno, Timestamp departuretime, Timestamp landingtime, double price) {
        return null;
    }


    @Override
    public double findLowPrice(String flightno, Timestamp timeRange) {
        return crawlFlightDao.findLowPrice(flightno, timeRange);
    }

    @Override
    public List<FlightInfo> findLowPriceFlightInfoByDay() {
        return crawlFlightDao.findLowPriceFlightInfoByDay();
    }

    @Override
    public List<Timestamp> findOptionTimes() {
        return crawlFlightDao.findOptionTimes();
    }

    @Override
    public List<Timestamp> findDepartureTimes() {
        return crawlFlightDao.findDepartureTimes();
    }

    @Override
    public List<FlightInfo> findFlightInfoByOptionTimeAndDepartureTime(String optionStartTime, String optionEndTime, String departureStartTime, String departureEndTime) {
        return crawlFlightDao.findFlightInfoByOptionTimeAndDepartureTime(optionStartTime,optionEndTime,departureStartTime,departureEndTime);
    }


}
