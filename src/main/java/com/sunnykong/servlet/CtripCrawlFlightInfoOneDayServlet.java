package com.sunnykong.servlet;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;
import com.sunnykong.utils.ToBeJsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by KXJ on 2015-12-15.
 */
public class CtripCrawlFlightInfoOneDayServlet extends HttpServlet {
    CrawlFlightService crawlFlightService = new CtripCrawlFlightServiceImpl();
    Timer timer = new Timer();
    Date currentTime=new Date();
//    String timeStr="2016-02-05";
    String[] dataArry = new String[]{"2016-02-01", "2016-02-02", "2016-02-03", "2016-02-04", "2016-02-05", "2016-02-06", "2016-02-07"};
    public void init() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (String date : dataArry) {
                    List<FlightInfo> flightInfoList = null;
                    try {
                        flightInfoList = crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC, date);
                        System.out.println(flightInfoList);
                        for (FlightInfo flightInfo : flightInfoList) {
                            crawlFlightService.saveFlightInfo(flightInfo);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, currentTime, 3600 * 1000L);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        List<FlightInfo> flightInfoList=crawlFlightService.findLowPriceFlightInfoByDay();//获取固定一天的每一个自然日的时间
        List<String> flightno=new ArrayList<String>();
        List<Timestamp> times=new ArrayList<Timestamp>();
        List<Double> prices=new ArrayList<Double>();
        for(FlightInfo flightInfo:flightInfoList){
            flightno.add(flightInfo.getFlightNo());
            times.add(flightInfo.getOptiontime());
            prices.add(flightInfo.getPrice());
        }
        Map<String,Object> json=new HashMap<String, Object>();
        json.put("flightno",flightno);
        json.put("times",times);
        json.put("prices",prices);
        ToBeJsonUtil.writeJson(json,request,response);
    }
}
