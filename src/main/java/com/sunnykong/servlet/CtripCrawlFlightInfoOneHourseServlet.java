package com.sunnykong.servlet;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;
import com.sunnykong.utils.DateUtils;
import com.sunnykong.utils.ToBeJsonUtil;
import com.sunnykong.utils.Util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by KXJ on 2015-12-23.
 */
public class CtripCrawlFlightInfoOneHourseServlet extends HttpServlet {
    CrawlFlightService crawlFlightService = new CtripCrawlFlightServiceImpl();

    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String optiontime = request.getParameter("optiontime");
        String[] optiontimeStrs = optiontime.split("~");
        final String departuretime = request.getParameter("departuretime");
        String departureCityStr = request.getParameter("departurecity").trim();
        String landingcityStr = request.getParameter("landingcity").trim();

        final AirPortCity departureCity = Enum.valueOf(AirPortCity.class, departureCityStr);
        final AirPortCity landingCity = Enum.valueOf(AirPortCity.class, landingcityStr);

        final List<FlightInfo> newFlightLists = new ArrayList<FlightInfo>();
//        List<Timestamp> optionTimeList = crawlFlightService.findOptionTimesByHourse(departureCity);
        List<Timestamp> optionTimeList = DateUtils.getTimeRange(Timestamp.valueOf(optiontimeStrs[0] + " 00:00:00"), Timestamp.valueOf(optiontimeStrs[1] + " 23:59:59"));

        final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        long start = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (final Timestamp optionTime : optionTimeList) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    String departureStartTime = departuretime + " 00:00:00";
                    String departureEndTime = departuretime + " 23:59:59";
                    String optionStartTime = sdf1.format(optionTime);
                    String optionEndTime = sdf2.format(optionTime) + " 23:59:59";
                    List<FlightInfo> flightInfoList = crawlFlightService.findFlightInfoByOptionTimeAndDepartureTime(departureCity, landingCity, optionStartTime, optionEndTime, departureStartTime, departureEndTime);
                    for (FlightInfo flightInfo : flightInfoList) {
                        flightInfo.setOptiontime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(flightInfo.getOptiontime())));
                    }

                    List<Timestamp> timeRange = DateUtils.getTimeRange(Timestamp.valueOf(optionStartTime), Timestamp.valueOf(optionEndTime));

                    for (Timestamp timestamp : timeRange) {
                        List<FlightInfo> flightInfoList1 = new ArrayList<FlightInfo>();
                        for (FlightInfo flightInfo : flightInfoList) {
                            if (timestamp.equals(flightInfo.getOptiontime())) {
                                flightInfoList1.add(flightInfo);
                            } else {
                                break;
                            }
                        }
                        if (flightInfoList1.size() > 0) {
                            Collections.sort(flightInfoList1, new Comparator<FlightInfo>() {
                                @Override
                                public int compare(FlightInfo o1, FlightInfo o2) {
                                    return (int) (o1.getPrice() - o2.getPrice());
                                }
                            });
                            boolean have = false;
                            FlightInfo flightInfo = flightInfoList1.get(0);
                            for (FlightInfo newFlightList : newFlightLists) {
                                have |= newFlightList.getFlightNo().equals(flightInfo.getFlightNo()) && newFlightList.getOptiontime().equals(flightInfo.getOptiontime());
                            }
                            if (!have)
                                newFlightLists.add(flightInfo);
                        }
                    }
                }
            });
            pool.shutdown();
            if (pool.isTerminated()) {
                try {
                    pool.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(System.currentTimeMillis() - start);
        //构建newFlightLists，最终结果：
        // optionTimeList={"00","01","02","03","04","05",..."23"};
        // nameList=｛"2015-12-15","2015-12-16","2015-12-17",..."2015-12-28"｝
        //valuesList={{1000,1000,999,999,...1280},{1000,1000,999,999,...1280},{1000,1000,999,999,...1280},...}

        List<String> optionTimeStrList = new ArrayList<String>();
        List<String> nameList = new ArrayList<String>();
        List<List<Double>> valuesList = new ArrayList<List<Double>>();

        List<Timestamp> optionsLists = DateUtils.getTimeRange(Timestamp.valueOf("2015-12-15 00:00:00"), Timestamp.valueOf("2015-12-15 23:59:59"));
        for (Timestamp timestamp : optionsLists) {
            String timeStr = new SimpleDateFormat("HH").format(timestamp);
            optionTimeStrList.add(timeStr);
        }
        //构建hashMap｛“2015-12-15”，List<FlightInfo>，“2015-12-16”，List<FlightInfo>，“2015-12-17”，List<FlightInfo>｝
        Map<String, List<FlightInfo>> flightInfoMap = new LinkedHashMap<String, List<FlightInfo>>();

        for (FlightInfo flightInfo : newFlightLists) {
            String optiontimeStr = new SimpleDateFormat("yyyy-MM-dd").format(flightInfo.getOptiontime());

            if (flightInfoMap.containsKey(optiontimeStr)) {
                flightInfoMap.get(optiontimeStr).add(flightInfo);
            } else {
                List<FlightInfo> flightInfos = new ArrayList<FlightInfo>();
                flightInfos.add(flightInfo);
                flightInfoMap.put(optiontimeStr, flightInfos);
            }
        }

        //构建valuesList={{1000,1000,999,999,...1280},{1000,1000,999,999,...1280},{1000,1000,999,999,...1280},...}
        for (String key : flightInfoMap.keySet()) {
            nameList.add(key);
            List<FlightInfo> flightInfoListValues = flightInfoMap.get(key);
            Timestamp timeStart = Timestamp.valueOf(key + " 00:00:00");
            Timestamp timeEnd = Timestamp.valueOf(key + " 23:59:59");
            List<Timestamp> timestampList = DateUtils.getTimeRange(timeStart, timeEnd);
            List<Double> flightPrice = new ArrayList<Double>();
            for (Timestamp timestamp : timestampList) {//循环24小时
                boolean isAdd = false;
                for (FlightInfo flightInfo : flightInfoListValues) {
                    if (timestamp.equals(flightInfo.getOptiontime())) {
                        flightPrice.add(flightInfo.getPrice());
                        isAdd = true;
                    }
                }
                if (!isAdd) {
                    flightPrice.add(0.0);
                }
            }
            valuesList.add(flightPrice);
        }


        Map<String, Object> json = new HashMap<String, Object>();
        json.put("optionTime", optionTimeStrList);//查询时间(小时)集合
        json.put("departureTime", nameList);//起飞时间（天）集合
        json.put("prices", valuesList);//价格数组套数组集合

        json.put("optiontime", optiontime);
        json.put("departuretime", departuretime);
        json.put("landingcityStr", landingcityStr);
        json.put("departureCityStr", departureCityStr);

        ToBeJsonUtil.writeJson(json, request, response);

    }
}
