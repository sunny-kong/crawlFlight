package com.sunnykong.dao.impl;

import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.utils.MapToBeanUtil;
import com.sunnykong.utils.Util;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public void testLowPriceByDay() {
        List<FlightInfo> flightInfoList = dao.findLowPriceFlightInfoByDay();
        for (FlightInfo flightInfo : flightInfoList) {
            System.out.println(flightInfo);
        }
    }

    @Test
    public void testFindOptionTimes() {
        List<Timestamp> list1 = dao.findOptionTimes();
        List<Timestamp> list2 = dao.findDepartureTimes();
        for (Timestamp optiontime : list1) {
           /* System.out.println(optiontime);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(optiontime));*/
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(optiontime) + " 23:59:59");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(optiontime));


        }

    }

    @Test
    public void testFlightInfoByOptionTimeAndDepartureTime() {
       /* List<FlightInfo> flightInfoList=dao.findFlightInfoByOptionTimeAndDepartureTime("2015-12-23 00:00:00","2015-12-23 23:59:59","2016-02-05 00:00:00","2016-02-05 23:59:59");
        Collections.sort(flightInfoList, new Comparator<FlightInfo>() {
            @Override
            public int compare(FlightInfo o1, FlightInfo o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        for(FlightInfo flightInfo:flightInfoList){
            System.out.println(flightInfo.getFlightNo()+flightInfo.getDeparturetime()+flightInfo.getPrice()+flightInfo.getOptiontime());
        }*/

        List<Timestamp> optionTimeList = dao.findOptionTimes();
        System.out.println("@@@@@@@@" + optionTimeList);
        List<Timestamp> departureTimeList = dao.findDepartureTimes();
        List<FlightInfo> flightInfoListlowPrice = new ArrayList<FlightInfo>();
        for (Timestamp optionTime : optionTimeList) {
            for (Timestamp departureTime : departureTimeList) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String optionStartTime = sdf1.format(optionTime);
                String optionEndTime = sdf2.format(optionTime) + " 23:59:59";
                String departureStartTime = sdf1.format(departureTime);
                String departureEndTime = sdf2.format(departureTime) + " 23:59:59";
                List<FlightInfo> flightInfoList = dao.findFlightInfoByOptionTimeAndDepartureTime(optionStartTime, optionEndTime, departureStartTime, departureEndTime);
                Collections.sort(flightInfoList, new Comparator<FlightInfo>() {
                    @Override
                    public int compare(FlightInfo o1, FlightInfo o2) {
                        return (int) (o1.getPrice() - o2.getPrice());
                    }
                });
                flightInfoListlowPrice.add(flightInfoList.get(0));
            }
        }

        for (FlightInfo flightInfo : flightInfoListlowPrice) {
            System.out.println(flightInfo.getFlightNo() + "   " + flightInfo.getDeparturetime() + "   " + flightInfo.getPrice() + "   " + flightInfo.getOptiontime());
        }
    }

    @Test
    public void testOneHourseFlightInfo() {//2016-02-05在一个月内，每天的最低票价

        List<FlightInfo> newFlightLists = new ArrayList<FlightInfo>();
        List<Timestamp> optionTimeList = dao.findOptionTimesByHourse();
//        System.out.println(optionTimeList);
        for (Timestamp optionTime : optionTimeList) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String departureStartTime = "2016-02-05 00:00:00";
            String departureEndTime = "2016-02-05 23:59:59";
            String optionStartTime = sdf1.format(optionTime);
            String optionEndTime = sdf2.format(optionTime) + " 23:59:59";
            List<FlightInfo> flightInfoList = dao.findFlightInfoByOptionTimeAndDepartureTime(optionStartTime, optionEndTime, departureStartTime, departureEndTime);
            for (FlightInfo flightInfo : flightInfoList) {
                flightInfo.setOptiontime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(flightInfo.getOptiontime())));
            }

            List<Timestamp> timeRange = Util.getTimeRange(Timestamp.valueOf(optionStartTime), Timestamp.valueOf(optionEndTime));

            for (Timestamp timestamp : timeRange) {
//                System.out.println(timestamp);
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

       /* for (FlightInfo flightInfo : newFlightLists) {
            System.out.println(flightInfo.getFlightNo() + " " + flightInfo.getDeparturetime() + " " + flightInfo.getPrice() + " " + flightInfo.getOptiontime());
        }*/
        List<String> optionTimeStrList = new ArrayList<String>();
        List<String> nameList = new ArrayList<String>();
        List<List<Double>> valuesList = new ArrayList<List<Double>>();

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


      /*  for (String key:flightInfoMap.keySet()){
            System.out.println(key+" "+flightInfoMap.get(key));
        }*/

        for (String key : flightInfoMap.keySet()) {
            nameList.add(key);
            List<FlightInfo> flightInfoListValues = flightInfoMap.get(key);
            Timestamp timeStart = Timestamp.valueOf(key + " 00:00:00");
            Timestamp timeEnd = Timestamp.valueOf(key + " 23:59:59");
            List<Timestamp> timestampList = Util.getTimeRange(timeStart, timeEnd);
            System.out.println("@@@@@@@@@@@@" + timestampList);
            System.out.println("-------------------------------------");
            List<Double> flightPrice = new ArrayList<Double>();

            for (Timestamp timestamp : timestampList) {
                boolean isAdd=false;
                for (FlightInfo flightInfo : flightInfoListValues) {

                    if (timestamp.equals(flightInfo.getOptiontime())) {
                        flightPrice.add(flightInfo.getPrice());
                        isAdd=true;
                    }
                }
                if(!isAdd){
                    flightPrice.add(0.0);
                }
            }
            valuesList.add(flightPrice);

        }

        System.out.println(valuesList);

    }

    @Test
    public void test() {
        List<String> optionTimeStrList = new ArrayList<String>();
        List<String> nameList = new ArrayList<String>();
        List<List<Double>> valuesList = new ArrayList<List<Double>>();

        List<Timestamp> optionsLists = Util.getTimeRange(Timestamp.valueOf("2015-12-15 00:00:00"), Timestamp.valueOf("2015-12-15 23:59:59"));
        for (Timestamp timestamp : optionsLists) {
            String timeStr = new SimpleDateFormat("HH").format(timestamp);
            optionTimeStrList.add(timeStr);
        }
        System.out.println(optionTimeStrList.toString());
    }
}
