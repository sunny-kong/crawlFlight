package com.sunnykong.dao.impl;

import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public class CrawlFlightDaoImpl implements CrawlFlightDao {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    static JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

    @Override
    public void saveFlightInfo(List<FlightInfo> flightInfoList) {
        String sql = "INSERT INTO flightinfo(flightNo,parentname,departuretime,landingtime,price,departurecity,landingcity)VALUES (?,?,?,?,?,?,?)";
        for (FlightInfo flightInfo : flightInfoList) {
            jdbcTemplate.update(sql, new Object[]{flightInfo.getFlightNo(), flightInfo.getParentname(), flightInfo.getDeparturetime(), flightInfo.getLandingtime(), flightInfo.getPrice(), flightInfo.getDeparturecity(), flightInfo.getLandingcity()});
        }
    }
}
