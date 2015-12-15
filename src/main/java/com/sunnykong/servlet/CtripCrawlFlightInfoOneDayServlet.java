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

    public void init() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    List<FlightInfo> flightInfoList = crawlFlightService.crawl(AirPortCity.HET,AirPortCity.URC,"2016-02-05");
                    for(FlightInfo flightInfo:flightInfoList){
                        crawlFlightService.saveFlightInfo(flightInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, currentTime, 1800 * 1000L);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {


    }
}
