package com.sunnykong.dao.impl;


import com.sunnykong.bean.MonitorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by KXJ on 2015-12-21.
 */

public class MonitorInfoDao {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    static JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    public void saveAll(List<MonitorInfo> monitorInfos) {
        for(MonitorInfo monitorInfo:monitorInfos){
            jdbcTemplate.update("INSERT INTO monitorinfo(monitorcompanyname,homepageaddr,monitorjs,monitorrequestaddr)VALUES (?,?,?,?)",new Object[]{monitorInfo.getMonitorcompanyname(),monitorInfo.getHomepageaddr(),monitorInfo.getMonitorjs(),monitorInfo.getMonitorrequestaddr()});
            System.out.println("保存成功"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }
}
