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
 * Created by KXJ on 2015-12-10.
 */
public class CtripCrawlFlightInfoServlet2 extends HttpServlet {
    CrawlFlightService crawlFlightService = new CtripCrawlFlightServiceImpl();
    String[] dataArry = new String[]{"2016-02-01", "2016-02-02", "2016-02-03", "2016-02-04", "2016-02-05", "2016-02-06", "2016-02-07"};
    Timer timer = new Timer();
/*
    public void init() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                   *//* SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date=sdf.format(new Date());*//*

                    for (String date : dataArry) {
                        List<FlightInfo> flightInfoList = crawlFlightService.crawl(AirPortCity.HET, AirPortCity.URC, date);
                        System.out.println(flightInfoList);
                        for (FlightInfo flightInfo : flightInfoList) {
                            FlightInfo flightInfo1 = crawlFlightService.findFlightInfoByUniqueKey(flightInfo.getFlightNo(), flightInfo.getDeparturetime(), flightInfo.getLandingtime(), flightInfo.getPrice());
                            if (flightInfo1 == null) {
                                crawlFlightService.saveFlightInfo(flightInfo);
                            } else {
                                System.out.println("暂时没有机票信息跟新" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            }
                        }
                    }
                    System.out.println("将当前时间的机票信息保存到数据库----------------" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, new Date(), 1800 * 1000L);
    }*/

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            List<FlightInfo> flightInfoList = crawlFlightService.findAllFlightInfo();
            List<String> timeRangeStr = new ArrayList<String>();
            List<List<String>> flightNos= new ArrayList<List<String>>();
            List<List<Double>> prices= new ArrayList<List<Double>>();

            Map<String,List<FlightInfo>> flightInfoMapByTime=new HashMap<String, List<FlightInfo>>();
            for(FlightInfo flightInfo:flightInfoList){
                String timekey = new SimpleDateFormat("yyyy-MM-dd").format(flightInfo.getDeparturetime());
                if(!timeRangeStr.contains(timekey)){
                    timeRangeStr.add(timekey);
                }
                if(flightInfoMapByTime.containsKey(timekey)){
                    flightInfoMapByTime.get(timekey).add(flightInfo);
                }else{
                    List<FlightInfo> flightInfoList1=new ArrayList<FlightInfo>();
                    flightInfoList1.add(flightInfo);
                    flightInfoMapByTime.put(timekey,flightInfoList1);
                }

                Collections.sort(timeRangeStr);
            }

            Map<String,List<FlightInfo>> stringListMap=new HashMap<String, List<FlightInfo>>();
            Map<String,Map<String,List<FlightInfo>>> Mapdatas=new HashMap<String, Map<String, List<FlightInfo>>>();

            for(String timeRange:flightInfoMapByTime.keySet()){
             List<FlightInfo> flightInfoList1=flightInfoMapByTime.get(timeRange);

                List<String> flightList=new ArrayList<String>();
                List<Double> prices1=new ArrayList<Double>();
                for(FlightInfo flightInfo:flightInfoList1){
                    String flightno = flightInfo.getFlightNo();
                    flightList.add(flightno);
                    double price=crawlFlightService.findLowPrice(flightno,flightInfo.getDeparturetime());
                    prices1.add(price);
                    if(stringListMap.containsKey(flightno)){
                        stringListMap.get(flightno).add(flightInfo);
                    }else{
                        List<FlightInfo> flightInfoList2=new ArrayList<FlightInfo>();
                        flightInfoList2.add(flightInfo);
                        stringListMap.put(flightno,flightInfoList2);
                    }
                }
                flightNos.add(flightList);
                prices.add(prices1);
                Mapdatas.put(timeRange,stringListMap);
            }

            Map<String,Object> json=new HashMap<String, Object>();
            json.put("timeRange",timeRangeStr);
            json.put("flightNos",flightNos);
            json.put("prices",prices);

            ToBeJsonUtil.writeJson(json,request,response);
           /* request.getSession().setAttribute("flightInfoList", flightInfoList);
            request.getRequestDispatcher("showCtripFlightInfo.jsp").forward(request, response);*/
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
