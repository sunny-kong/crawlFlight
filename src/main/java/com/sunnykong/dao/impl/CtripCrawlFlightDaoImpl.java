package com.sunnykong.dao.impl;

import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public class CtripCrawlFlightDaoImpl implements CrawlFlightDao {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    static JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

    @Override
    public void saveFlightInfo(List<FlightInfo> flightInfoList) {
        String sql = "INSERT INTO flightinfo(flightno,parentname,departuretime,landingtime,price,departurecity,landingcity)VALUES (?,?,?,?,?,?,?)  on DUPLICATE KEY UPDATE price=?";
        for (FlightInfo flightInfo : flightInfoList) {
            jdbcTemplate.update(sql, new Object[]{flightInfo.getFlightNo(), flightInfo.getParentname(), flightInfo.getDeparturetime(), flightInfo.getLandingtime(), flightInfo.getPrice(), flightInfo.getDeparturecity(), flightInfo.getLandingcity(),flightInfo.getPrice()});
            System.out.println("数据库跟新成功");
        }
    }

    @Override
    public List<FlightInfo> findAllFlightInfo() {
        return jdbcTemplate.query("SELECT * FROM flightinfo", new RowMapper<FlightInfo>() {
            @Override
            public FlightInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                FlightInfo flightInfo=new FlightInfo();
                flightInfo.setId(resultSet.getInt("id"));
                flightInfo.setFlightNo(resultSet.getString("flightno"));
                flightInfo.setParentname(resultSet.getString("parentname"));
                flightInfo.setDeparturetime(resultSet.getDate("departuretime"));
                flightInfo.setLandingtime(resultSet.getDate("landingtime"));
                flightInfo.setPrice(resultSet.getDouble("price"));
                flightInfo.setDeparturecity(resultSet.getString("departurecity"));
                flightInfo.setLandingcity(resultSet.getString("landingcity"));
                return flightInfo;
            }
        });
    }
}
