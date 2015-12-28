package com.sunnykong.dao.impl;


import com.sunnykong.bean.LinkInfo;
import com.sunnykong.bean.MonitorInfo;
import com.sunnykong.utils.ReadExcel1;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXJ on 2015-12-21.
 */
public class ScriptProTest {
    @Test
    public void testSaveLinkInfo(){
        List<LinkInfo> linkInfoList=new ArrayList<LinkInfo>();
        LinkInfo linkInfo1=new LinkInfo();
        linkInfo1.setUrl("http://price.pcauto.com.cn/");
        linkInfo1.setName("汽车网报价库");
        linkInfo1.setTitle("查找汽车网报价库中所有车系终端页");
        linkInfo1.setRule("a[href~=http://price.pcauto.com.cn/sg[0-9]+/$]");
        linkInfo1.setInfo("汽车网报价内页 -> 产品终端页");
        linkInfoList.add(linkInfo1);
        LinkInfo linkInfo2=new LinkInfo();
        linkInfo2.setUrl("http://mobile.pconline.com.cn/");
        linkInfo2.setName("电脑网手机首页");
        linkInfo2.setTitle("查找手机首页中的所有文章");
        linkInfo2.setRule("a[href~=http://mobile.pconline.com.cn/[0-9]+/[0-9]+.html]");
        linkInfo2.setInfo("电脑网手机首页 -> 文章");
        linkInfoList.add(linkInfo2);
        LinkInfo linkInfo3=new LinkInfo();
        linkInfo3.setUrl("http://product.pconline.com.cn/notebook/");
        linkInfo3.setName("电脑网笔记本报价库");
        linkInfo3.setTitle("查找笔记本报价库中所有产品终端页");
        linkInfo3.setRule("a[href~=product.pconline.com.cn/notebook/[a-z]+/[0-9]+.html]");
        linkInfo3.setInfo("电脑网报价内页 -> 产品终端页");
        linkInfoList.add(linkInfo3);
        LinkInfo linkInfo4=new LinkInfo();
        linkInfo4.setUrl("http://dl.pconline.com.cn/");
        linkInfo4.setName("电脑网下载首页");
        linkInfo4.setTitle("查找下载首页所有软件终端页");
        linkInfo4.setRule("a[href~=http://dl.pconline.com.cn/download/[0-9]+.html]");
        linkInfo4.setInfo("电脑网下载首页 -> 软件终端页");
        linkInfoList.add(linkInfo4);
        LinkInfo linkInfo5=new LinkInfo();
        linkInfo5.setUrl("http://v.pconline.com.cn/video-15936.html");
        linkInfo5.setName("电脑网视频内页");
        linkInfo5.setTitle("查找视频页面中的播放计数器");
        linkInfo5.setRule("script[src~=\\/__counter\\\\.jsp\\\\?video=[0-9]+]");
        linkInfo5.setInfo("电脑网视频内页 -> 播放计数器");
        linkInfoList.add(linkInfo5);
        LinkInfo linkInfo6=new LinkInfo();
        linkInfo6.setUrl("http://try.pconline.com.cn/mobile/act-153.html");
        linkInfo6.setName("电脑网试用内页");
        linkInfo6.setTitle("查找试用页面中的关注此活动计数器");
        linkInfo6.setRule("script[src~=\\/activity\\/view\\\\.jsp\\\\?activityId=[0-9]+]");
        linkInfo6.setInfo("电脑网试用内页 -> 关注活动计数器");
        linkInfoList.add(linkInfo6);
        LinkInfo linkInfo7=new LinkInfo();
        linkInfo7.setUrl("http://my.pclady.com.cn/a/z-20344.html");
        linkInfo7.setName("时尚网摩登学院内页");
        linkInfo7.setTitle("查找页面中的访问计数器");
        linkInfo7.setRule("script[src~=\\/syncCounter\\\\.jsp\\\\?id=[0-9]+]");
        linkInfo7.setInfo("时尚网学院内页 -> 访问计数器");
        linkInfoList.add(linkInfo7);
        LinkInfo linkInfo8=new LinkInfo();
        linkInfo8.setUrl("http://cosme.pclady.com.cn/product/117614.html");
        linkInfo8.setName("时尚网化妆品库");
        linkInfo8.setTitle("查找页面中的感兴趣计数器");
        linkInfo8.setRule("script[src~=\\/_counter\\\\.jsp\\\\?id=[0-9]+]");
        linkInfo8.setInfo("时尚网化妆品库内页 -> 感兴趣计数器");
        linkInfoList.add(linkInfo8);
        LinkInfo linkInfo9=new LinkInfo();
        linkInfo9.setUrl("http://try.pclady.com.cn/2757.html");
        linkInfo9.setName("时尚网试用页面");
        linkInfo9.setTitle("查找试用页面中的人气计数器");
        linkInfo9.setRule("script[src~=\\/_counter\\\\.jsp\\\\?id=[0-9]+]");
        linkInfo9.setInfo("时尚网试用内页 -> 人气计数器");
        linkInfoList.add(linkInfo9);
        LinkInfo linkInfo10=new LinkInfo();
        linkInfo10.setUrl("http://bbs.pchouse.com.cn/topic-638775.htm");
        linkInfo10.setName("论坛帖子");
        linkInfo10.setTitle("查找帖子中的查看计数器");
        linkInfo10.setRule("script:counter.ajax");
        linkInfo10.setInfo("帖子页面 -> 查看计数器");
        linkInfoList.add(linkInfo10);
        LinkInfo linkInfo11=new LinkInfo();
        linkInfo11.setUrl("http://kuaiwen.pcbaby.com.cn/");
        linkInfo11.setName("亲子网快问首页");
        linkInfo11.setTitle("查找快问中最新问题回复");
        linkInfo11.setRule("a[href~=http://kuaiwen.pcbaby.com.cn/question/[0-9]+.html]");
        linkInfo11.setInfo("亲子网快问首页 -> 最新问题回复");
        linkInfoList.add(linkInfo11);
        LinkInfoDao dao=new LinkInfoDao();
        dao.saveAll(linkInfoList);
    }
    @Test
    public void testSaveMonitorInfo(){
        String excel2003_2007 = "E:\\app\\data\\a1.xls";
        try {
            List<MonitorInfo> list = new ReadExcel1().readExcel(excel2003_2007);
            MonitorInfoDao dao = new MonitorInfoDao();
            dao.saveAll(list);
            for(MonitorInfo monitorInfo:list){
                System.out.println(monitorInfo.getMonitorrequestaddr());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
