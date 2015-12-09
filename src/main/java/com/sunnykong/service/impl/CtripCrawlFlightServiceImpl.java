package com.sunnykong.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xj on 15-12-8.
 */
public class CtripCrawlFlightServiceImpl implements CrawlFlightService {
    @Override
    public List<FlightInfo> crawl(AirPortCity from, AirPortCity to, Date date) throws IOException, ParseException {
        List<Header> headers = buildHeaders();
        CloseableHttpClient httpClient = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36").setDefaultHeaders(headers).build();
        HttpGet httpGet = new HttpGet("http://flights.ctrip.com/domesticsearch/search/SearchFirstRouteFlights?DCity1=HET&ACity1=URC&SearchType=S&DDate1=2016-02-06&rk=9.41909585148096213724&CK=47D506EA463C9C7FCC1D96E8C2AEE345&r=0.3195956253403258627319");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
         List<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
        if (entity != null) {
            String json = EntityUtils.toString(entity);
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(json, Map.class);
           List fisList= (List)map.get("fis");

            for (int i = 0; i < fisList.size(); i++) {
                FlightInfo flightInfo = new FlightInfo();
                flightInfo.setParentname("携程");
                flightInfo.setDeparturecity(String.valueOf(((Map) fisList.get(i)).get("dpc")));
                flightInfo.setLandingcity(String.valueOf(((Map) fisList.get(i)).get("apc")));
                flightInfo.setFlightNo(String.valueOf(((Map) fisList.get(i)).get("fn")));
                flightInfo.setDeparturetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(((Map) fisList.get(i)).get("dt"))));
                flightInfo.setLandingtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(((Map) fisList.get(i)).get("at"))));
                flightInfo.setPrice(Double.parseDouble(String.valueOf(((Map) fisList.get(i)).get("lp"))));
                flightInfoList.add(flightInfo);
            }
            EntityUtils.consume(entity);
        }
        return flightInfoList;
    }

    public List<Header> buildHeaders() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "*/*"));

        headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));

        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));

        headers.add(new BasicHeader("Connection", "keep-alive"));

        headers.add(new BasicHeader("Cookie", "ASP.NET_SessionId=s2pzc3o35c5wtyicc03ohbmd; i_v=o=0&i=waayuw&p=26&l=sh02svr2501.4x7mjfh05&s=2; i_b=i=hpqc0h8k; pv_id=v=2014010218385366; __lpi=p=101027&p2=100101991; AX-20480-flights_domestic=FIACAIAKFAAA; login_type=0; login_uid=9E937385AA896AD450071E495A119861; __zpc=9; __zpa=9.5.1413035158.1413035158.1.0; NSC_WT_Gmjhiut_80=ffffffff09079a7745525d5f4f58455e445a4a423660; PKGIDCOOKIEKEY=2610288; TMPORDERIDCOOKIEKEY=0; tempUserLogin=T; __utma=167964915.1430058799.1388202646.1433917949.1433917949.1; __utmc=167964915; __utmz=167964915.1433917949.1.1.utmcsr=vacations.ctrip.com|utmccn=(referral)|utmcmd=referral|utmcct=/around/p2045124s28.html; TraceSessionEx=F47122F0AC8232094917F790FB43054B; SMBID=; TraceSession=155020065; _abtest_userid=f963fffd-957f-4382-9a67-e482005ad19d; adscityen=Beijing; appFloatCnt=1; manualclose=1; _gat=1; Customer=HAL=ctrip_gb; __zpspc=9.23.1449584940.1449584962.3%233%7Cxiecheng.com%7C%7C%7C%7C%23; _ga=GA1.2.1430058799.1388202646; _jzqco=%7C%7C%7C%7C1449523926004%7C1.1602252351.1412774342848.1449584952995.1449584962645.1449584952995.1449584962645.undefined.0.0.323.323; LoginStatus=0%7c; _bfa=1.1388202645867.hbo96s.1.1449523922749.1449584937404.30.473; _bfs=1.5; _bfi=p1%3D100101991%26p2%3D101027%26v1%3D473%26v2%3D471; FD_SearchHistorty={\"type\":\"S\",\"data\":\"S%24%u547C%u548C%u6D69%u7279%28HET%29%24HET%242016-02-06%24%u4E4C%u9C81%u6728%u9F50%28URC%29%24URC%24%24%24\"}; ASP.NET_SessionSvc=MTAuMTUuMTM2LjI3fDkwOTB8b3V5YW5nfGRlZmF1bHR8MTQ0OTA1MTEwMDQyNA"));

        headers.add(new BasicHeader("Host", "flights.ctrip.com"));
        headers.add(new BasicHeader("RA-Sid", "B848F0B1-20140612-145858-bbd665-0bd87b"));
        headers.add(new BasicHeader("RA-Ver", "3.0.7"));

        headers.add(new BasicHeader("Referer", "http://flights.ctrip.com/booking/HET-URC-day-1.html"));

        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36"));
        return headers;
    }
}
