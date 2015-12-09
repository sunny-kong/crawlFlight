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
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");
    JdbcTemplate jdbcTemplate= (JdbcTemplate) context.getBean("jdbcTemplate");
    @Override
    public void saveFlightInfo(List<FlightInfo> flightInfoList) {

    }
}
