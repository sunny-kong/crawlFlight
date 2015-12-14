package com.sunnykong.utils;

import com.sunnykong.bean.FlightInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KXJ on 2015-12-14.
 */
public class MapToBeanUtil {
    public static Map<String, List<FlightInfo>> buildMap(List<FlightInfo> flightInfoList) {
        Map<String,List<FlightInfo>> flightInfoMap=new HashMap<String, List<FlightInfo>>();
        for(FlightInfo flightInfo:flightInfoList){
            if(flightInfoMap.containsKey(flightInfo.getFlightNo())){
                flightInfoMap.get(flightInfo.getFlightNo()).add(flightInfo);
            }else{
                List<FlightInfo> flightInfoList1=new ArrayList<FlightInfo>();
                flightInfoList1.add(flightInfo);
                flightInfoMap.put(flightInfo.getFlightNo(),flightInfoList1);
            }
        }
        return flightInfoMap;
    }
}
