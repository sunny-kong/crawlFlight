package com.sunnykong.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KXJ on 2015-12-29.
 */
public class ShowFlightInfoServlet extends HttpServlet {
    CrawlFlightService crawlFlightService=new CtripCrawlFlightServiceImpl();
    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<FlightInfo> flightInfoList=crawlFlightService.findFlightInfoByOptionTimeAndDepartureTime(AirPortCity.HET,AirPortCity.URC,"2015-12-18 00:00:00","2015-12-18 23:59:59","2016-02-05 00:00:00","2016-02-05 23:59:59");
        Map<String,List<FlightInfo>> json=new HashMap<String, List<FlightInfo>>();
        json.put("flightInfoList",flightInfoList);

        ObjectMapper objectMapper=new ObjectMapper();
        String str=objectMapper.writeValueAsString(json);
        PrintWriter out=response.getWriter();
        out.print(str);
    }
}
