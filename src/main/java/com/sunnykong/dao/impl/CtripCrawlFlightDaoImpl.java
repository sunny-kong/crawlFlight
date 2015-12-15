package com.sunnykong.dao.impl;

import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public class CtripCrawlFlightDaoImpl implements CrawlFlightDao {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    static JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

    public void saveFlightInfo(FlightInfo flightInfo) {
        String sql = "INSERT INTO flightinfo(flightno,parentname,departuretime,landingtime,price,departurecity,landingcity,optiontime)VALUES (?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, new Object[]{flightInfo.getFlightNo(), flightInfo.getParentname(), flightInfo.getDeparturetime(), flightInfo.getLandingtime(), flightInfo.getPrice(), flightInfo.getDeparturecity(), flightInfo.getLandingcity(),flightInfo.getOptiontime()});
            System.out.println("数据库跟新成功");
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
                flightInfo.setDeparturetime(resultSet.getTimestamp("departuretime"));
                flightInfo.setLandingtime(resultSet.getTimestamp("landingtime"));
                flightInfo.setPrice(resultSet.getDouble("price"));
                flightInfo.setDeparturecity(resultSet.getString("departurecity"));
                flightInfo.setLandingcity(resultSet.getString("landingcity"));
                flightInfo.setOptiontime(resultSet.getTimestamp("optiontime"));
                return flightInfo;
            }
        });
    }

    @Override
    public FlightInfo findFlightInfoByUniqueKey(String flightno, Timestamp departuretime, Timestamp landingtime, double price) {
            String sql="SELECT * FROM flightinfo WHERE flightno=? AND departuretime=? AND landingtime=? AND price=?";
       try {
           return (FlightInfo) jdbcTemplate.queryForObject(sql, new Object[]{flightno, departuretime, landingtime, price}, new RowMapper<Object>() {
               @Override
               public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                   FlightInfo flightInfo = new FlightInfo();
                   flightInfo.setId(resultSet.getInt("id"));
                   flightInfo.setFlightNo(resultSet.getString("flightno"));
                   flightInfo.setParentname(resultSet.getString("parentname"));
                   flightInfo.setDeparturetime(resultSet.getTimestamp("departuretime"));
                   flightInfo.setLandingtime(resultSet.getTimestamp("landingtime"));
                   flightInfo.setPrice(resultSet.getDouble("price"));
                   flightInfo.setDeparturecity(resultSet.getString("departurecity"));
                   flightInfo.setLandingcity(resultSet.getString("landingcity"));
                   flightInfo.setOptiontime(resultSet.getTimestamp("optiontime"));
                   return flightInfo;
               }
           });
       }catch(EmptyResultDataAccessException e){
           return null;
       }
    }

    @Override
    public double findLowPrice(String flightno, Timestamp departuretime) {
        String sql="select min(price) from flightinfo where flightno=? and departuretime=? GROUP BY departuretime";
        return jdbcTemplate.queryForInt(sql,new Object[]{flightno,departuretime});
    }

    @Override
    public List<FlightInfo> findFlightInfoByDay(Timestamp day) {
        return jdbcTemplate.query("SELECT * FROM flightinfo where optiontime", new RowMapper<FlightInfo>() {
            @Override
            public FlightInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                FlightInfo flightInfo=new FlightInfo();
                flightInfo.setId(resultSet.getInt("id"));
                flightInfo.setFlightNo(resultSet.getString("flightno"));
                flightInfo.setParentname(resultSet.getString("parentname"));
                flightInfo.setDeparturetime(resultSet.getTimestamp("departuretime"));
                flightInfo.setLandingtime(resultSet.getTimestamp("landingtime"));
                flightInfo.setPrice(resultSet.getDouble("price"));
                flightInfo.setDeparturecity(resultSet.getString("departurecity"));
                flightInfo.setLandingcity(resultSet.getString("landingcity"));
                flightInfo.setOptiontime(resultSet.getTimestamp("optiontime"));
                return flightInfo;
            }
        });
    }
}
