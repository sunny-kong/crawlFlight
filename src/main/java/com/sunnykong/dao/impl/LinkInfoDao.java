package com.sunnykong.dao.impl;

import com.sunnykong.bean.LinkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by KXJ on 2015-12-21.
 */
public class LinkInfoDao {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    static JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

    public void saveAll(List<LinkInfo> linkInfoList) {
        for(LinkInfo linkInfo:linkInfoList){
            jdbcTemplate.update("INSERT INTO linkinfo(url,name,title,rule,info)VALUES (?,?,?,?,?)",new Object[]{linkInfo.getUrl(),linkInfo.getName(),linkInfo.getTitle(),linkInfo.getRule(),linkInfo.getInfo()});
            System.out.println("保存成功");
        }
    }
}
