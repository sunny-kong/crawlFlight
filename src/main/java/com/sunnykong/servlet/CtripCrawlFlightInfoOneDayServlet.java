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
    Date currentTime = new Date();
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
        //获取数据库中的起飞时间以及操作时间 yyyy-MM-dd
        List<Timestamp> optionTimeList = crawlFlightService.findOptionTimes();
        List<Timestamp> departureTimeList = crawlFlightService.findDepartureTimes();
        List<FlightInfo> flightInfoListlowPrice = new ArrayList<FlightInfo>();
        for (Timestamp optionTime : optionTimeList) {
            for (Timestamp departureTime : departureTimeList) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String optionStartTime = sdf1.format(optionTime);
                String optionEndTime = sdf2.format(optionTime) + " 23:59:59";
                String departureStartTime = sdf1.format(departureTime);
                String departureEndTime = sdf2.format(departureTime) + " 23:59:59";
                List<FlightInfo> flightInfoList = crawlFlightService.findFlightInfoByOptionTimeAndDepartureTime(optionStartTime, optionEndTime, departureStartTime, departureEndTime);
                Collections.sort(flightInfoList, new Comparator<FlightInfo>() {
                    @Override
                    public int compare(FlightInfo o1, FlightInfo o2) {
                        return (int) (o1.getPrice() - o2.getPrice());
                    }
                });
                flightInfoListlowPrice.add(flightInfoList.get(0));
            }
        }

        List<String> optionTimeStrList = new ArrayList<String>();
        List<String> departureTimeStrList = new ArrayList<String>();
        List<List<Double>> prices = new ArrayList<List<Double>>();
//将Timestamp类型的optionTime转换为String
        for (Timestamp optionTime : optionTimeList) {
            String optionTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(optionTime);
            optionTimeStrList.add(optionTimeStr);
        }

//构建{{"02-01",List<FlightInfo>,"02-02",List<FlightInfo>,"02-03",List<FlightInfo>}} 这样的map集合，LinkedHashMap有序的
        Map<String, List<FlightInfo>> flightMap = new LinkedHashMap<String, List<FlightInfo>>();
        for (FlightInfo flightInfo : flightInfoListlowPrice) {
            //起飞时间
            String departureTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(flightInfo.getDeparturetime());
            if (flightMap.containsKey(departureTimeStr)) {
                flightMap.get(departureTimeStr).add(flightInfo);
            } else {
                List<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
                flightInfoList.add(flightInfo);
                flightMap.put(departureTimeStr, flightInfoList);
            }
        }
//根据以上map的keySet，拼凑最低价集合，如：{{{1000,980},{1000,980},{…}}} ，{1000,980}：表示12-15---12-16。。。在02-01这天的最低票价，是一个数组套数组的形式
        for (String departureTimeStr : flightMap.keySet()) {
            departureTimeStrList.add(departureTimeStr);
            List<FlightInfo> flightInfoListValues = flightMap.get(departureTimeStr);
            List<Double> flightPrice = new ArrayList<Double>();
            for (FlightInfo flightInfo : flightInfoListValues) {
                flightPrice.add(flightInfo.getPrice());
            }
            prices.add(flightPrice);
        }


        Map<String, Object> json = new HashMap<String, Object>();
        json.put("optionTime", optionTimeStrList);//查询时间集合
        json.put("departureTime", departureTimeStrList);//起飞时间集合
        json.put("prices", prices);//价格数组套数组集合
        ToBeJsonUtil.writeJson(json, request, response);

    }
}
