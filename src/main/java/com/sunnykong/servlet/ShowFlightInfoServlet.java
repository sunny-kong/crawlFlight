package com.sunnykong.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.bean.Pager;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

        String optiontime=request.getParameter("optiontime");
        String departuretime=request.getParameter("departuretime");
        String departurecityStr= request.getParameter("departurecity");
        String landingcityStr=request.getParameter("landingcity");
        int currentPage=Integer.parseInt(request.getParameter("page"));

        AirPortCity departurecity=Enum.valueOf(AirPortCity.class,departurecityStr.trim());
        AirPortCity landingcity=Enum.valueOf(AirPortCity.class,landingcityStr.trim());
        String[] optiontimestrs=optiontime.split("~");
        List<FlightInfo> flightInfoListTotal=crawlFlightService.findFlightInfoByOptionTimeAndDepartureTime(departurecity,landingcity,optiontimestrs[0]+" 00:00:00",optiontimestrs[1]+" 23:59:59",departuretime+" 00:00:00",departuretime+" 23:59:59");

        Map<String, Object> json = new HashMap();
        ObjectMapper objectMapper=new ObjectMapper();
        PrintWriter out=response.getWriter();

        if(flightInfoListTotal.size()>0){
            List<FlightInfo> flightInfoListNew=new ArrayList<FlightInfo>();
            int numPerPage = 10;
            int totalNum = flightInfoListTotal.size();
            int totalPage = totalNum % numPerPage == 0 ? (totalNum / numPerPage) : (totalNum / numPerPage + 1);

            for (int i = (currentPage - 1) * numPerPage; i < (currentPage * numPerPage) && i < totalNum; i++) {//分页
                flightInfoListNew.add(flightInfoListTotal.get(i));
            }

            Pager page = new Pager();
            page.setPageNo(currentPage);//设置当前页
            page.setPageSize(totalPage);//设置总页数
            page.setTotal(totalNum);//总记录条数


            json.put("flightInfoListTotal",flightInfoListTotal);
            json.put("flightInfoList",flightInfoListNew);
            json.put("page",page);
            String str=objectMapper.writeValueAsString(json);
            out.print(str);
        }else{
            json.put("flightInfoListTotal",0);
            String str=objectMapper.writeValueAsString(json);
            out.print(str);
        }


    }
}
