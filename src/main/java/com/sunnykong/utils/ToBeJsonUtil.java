package com.sunnykong.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by KXJ on 2015-12-09.
 */
public class ToBeJsonUtil {
    public static void writeJson(Map<String, Object> json, HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonfromMap =  mapper.writeValueAsString(json);
            PrintWriter writer = response.getWriter();
            writer.println(jsonfromMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
