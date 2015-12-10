package com.sunnykong.servlet;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import com.sunnykong.dao.impl.CtripCrawlFlightDaoImpl;
import com.sunnykong.service.CrawlFlightService;
import com.sunnykong.service.impl.CtripCrawlFlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by KXJ on 2015-12-10.
 */
public class CtripCrawlFlightInfoServlet extends HttpServlet{
    CrawlFlightService crawlFlightService =new CtripCrawlFlightServiceImpl();
    Timer timer=new Timer();
    public void init(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    String datestr=sdf.format(new Date());
                    Date date = sdf.parse(datestr);
                    crawlFlightService.saveFlightInfo(crawlFlightService.crawl(AirPortCity.HET,AirPortCity.URC,date));
                    System.out.println("初始化servlet，将当前时间的机票信息保存到数据库----------------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        },new Date(),20*1000L);
    }
    public void service(HttpServletRequest request,HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            List<FlightInfo> flightInfoList = crawlFlightService.findAllFlightInfo();
            request.getSession().setAttribute("flightInfoList",flightInfoList);
            request.getRequestDispatcher("showCtripFlightInfo.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
