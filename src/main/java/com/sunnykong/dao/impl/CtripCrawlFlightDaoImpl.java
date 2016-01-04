package com.sunnykong.dao.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.dao.CrawlFlightDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
        jdbcTemplate.update(sql, new Object[]{flightInfo.getFlightNo(), flightInfo.getParentname(), flightInfo.getDeparturetime(), flightInfo.getLandingtime(), flightInfo.getPrice(), flightInfo.getDeparturecity(), flightInfo.getLandingcity(), flightInfo.getOptiontime()});
        System.out.println("数据库跟新成功" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
    }

    @Override
    public List<FlightInfo> findAllFlightInfo() {
        return jdbcTemplate.query("SELECT * FROM flightinfo", new RowMapper<FlightInfo>() {
            @Override
            public FlightInfo mapRow(ResultSet resultSet, int i) throws SQLException {
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
    }

    @Override
    public FlightInfo findFlightInfoByUniqueKey(String flightno, Timestamp departuretime, Timestamp landingtime, double price) {
        String sql = "SELECT * FROM flightinfo WHERE flightno=? AND departuretime=? AND landingtime=? AND price=?";
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
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public double findLowPrice(String flightno, Timestamp departuretime) {
        String sql = "select min(price) from flightinfo where flightno=? and departuretime=? GROUP BY departuretime";
        return jdbcTemplate.queryForInt(sql, new Object[]{flightno, departuretime});
    }

    @Override
    public List<FlightInfo> findFlightInfoByDay(Timestamp day) {
        return jdbcTemplate.query("SELECT * FROM flightinfo where optiontime", new RowMapper<FlightInfo>() {
            @Override
            public FlightInfo mapRow(ResultSet resultSet, int i) throws SQLException {
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
    }

    @Override
    public List<FlightInfo> findLowPriceFlightInfoByDay() {
        String sql = "SELECT f2.id,f2.flightno,f2.parentname,f2.departuretime,f2.landingtime,f2.departurecity,f2.landingcity,f2.price,f1.optiontime FROM flightinfo f2,(select min(price) price ,DATE_FORMAT(optiontime, \"%Y-%m-%d\") optiontime from flightinfo group by   DATE_FORMAT(optiontime, \"%Y-%m-%d\"))f1 WHERE f2.price=f1.price and DATE_FORMAT(f2.optiontime, \"%Y-%m-%d\")=f1.optiontime";
        return jdbcTemplate.query(sql, new RowMapper<FlightInfo>() {
            @Override
            public FlightInfo mapRow(ResultSet resultSet, int i) throws SQLException {
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
    }

    @Override
    public List<Timestamp> findOptionTimes(AirPortCity departurecity) {
        String sql="select distinct(DATE_FORMAT(optiontime, \"%Y-%m-%d\"))optiontime from flightinfo WHERE departurecity=? order by optiontime";
//        String sql = "select distinct(DATE_FORMAT(optiontime, \"%Y-%m-%d\"))optiontime from flightinfo order by optiontime";
        return jdbcTemplate.query(sql,new Object[]{departurecity.toString()}, new RowMapper<Timestamp>() {
            @Override
            public Timestamp mapRow(ResultSet resultSet, int i) throws SQLException {
                Timestamp optiontime = resultSet.getTimestamp("optiontime");
                return optiontime;
            }
        });
    }

    @Override
    public List<Timestamp> findDepartureTimes() {
        String sql = "select distinct(DATE_FORMAT(departuretime, \"%Y-%m-%d\"))departuretime from flightinfo order by departuretime";
        return jdbcTemplate.query(sql, new RowMapper<Timestamp>() {
            @Override
            public Timestamp mapRow(ResultSet resultSet, int i) throws SQLException {
                Timestamp departuretime = resultSet.getTimestamp("departuretime");
                return departuretime;
            }
        });
    }

    @Override
    public List<FlightInfo> findFlightInfoByOptionTimeAndDepartureTime(AirPortCity departurecity,AirPortCity landingcity,String optionStartTimeStr, String optionEndTimeStr, String departureStartTimeStr, String departureEndTimeStr) {
        Timestamp optionStartTime = Timestamp.valueOf(optionStartTimeStr);
        Timestamp optionEndTime = Timestamp.valueOf(optionEndTimeStr);
        Timestamp departureStartTime = Timestamp.valueOf(departureStartTimeStr);
        Timestamp departureEndTime = Timestamp.valueOf(departureEndTimeStr);
        String departurecityStr=departurecity.toString();
        String landingcityStr=landingcity.toString();
        String sql="select * from (select * from  flightinfo where optiontime between ? and ? AND departurecity=? AND landingcity=?) f where f.departuretime between ? and ?";
        return jdbcTemplate.query(sql, new Object[]{optionStartTime,optionEndTime,departurecityStr,landingcityStr,departureStartTime,departureEndTime}, new RowMapper<FlightInfo>() {
            @Override
            public FlightInfo mapRow(ResultSet resultSet, int i) throws SQLException {
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
    }

    @Override
    public List<Timestamp> findOptionTimesByHourse(AirPortCity departurecity) {
        String sql = "select distinct(DATE_FORMAT(optiontime, \"%Y-%m-%d %H:00:00\"))optiontime from flightinfo where departurecity=?  order by optiontime";
        return jdbcTemplate.query(sql,new Object[]{departurecity.toString()}, new RowMapper<Timestamp>() {
            @Override
            public Timestamp mapRow(ResultSet resultSet, int i) throws SQLException {
                Timestamp optiontime = resultSet.getTimestamp("optiontime");
                return optiontime;
            }
        });
    }
}
