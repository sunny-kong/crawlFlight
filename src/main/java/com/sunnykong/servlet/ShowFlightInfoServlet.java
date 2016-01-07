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

     /*   var optiontime = $("#optiontimeRange").val();
        var departuretime = $("#departuretime").val();
        var departurecity = $("#departurecity").val();
        var landingcity = $("#landingcity").val();*/

        String optiontime=request.getParameter("optiontime");
        String departuretime=request.getParameter("departuretime");
        String departurecityStr= request.getParameter("departurecity");
        String landingcityStr=request.getParameter("landingcity");
        AirPortCity departurecity=Enum.valueOf(AirPortCity.class,departurecityStr.trim());
        AirPortCity landingcity=Enum.valueOf(AirPortCity.class,landingcityStr.trim());
        String[] optiontimestrs=optiontime.split("~");
        List<FlightInfo> flightInfoList=crawlFlightService.findFlightInfoByOptionTimeAndDepartureTime(departurecity,landingcity,optiontimestrs[0]+" 00:00:00",optiontimestrs[1]+" 23:59:59",departuretime+" 00:00:00",departuretime+" 23:59:59");
        Map<String,List<FlightInfo>> json=new HashMap<String, List<FlightInfo>>();
        json.put("flightInfoList",flightInfoList);

        ObjectMapper objectMapper=new ObjectMapper();
        String str=objectMapper.writeValueAsString(json);
        PrintWriter out=response.getWriter();
        out.print(str);
    }
}
