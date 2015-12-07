<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2015-12-07
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpClient httpClient = new DefaultHttpClient();

    //創建一個httpGet方法

    HttpGet httpGet = new HttpGet("http://flights.ctrip.com/domesticsearch/search/SearchFirstRouteFlights?DCity1=HET&ACity1=URC&SearchType=S&DDate1=2016-02-05&rk=0.15502697555348277161409&CK=13C4E5660E9B6133599F46314B4803B5&r=0.1527101166119021581315");


    //設置httpGet的头部參數信息

    httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

    httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

    httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");

    httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

    httpGet.setHeader("Connection", "keep-alive");

    httpGet.setHeader("Cookie", "_abtest_userid=d9acd9e0-420c-44f1-acd8-d22a86b0efc4; ASP.NET_SessionSvc=MTAuMTUuMTM2LjMxfDkwOTB8b3V5YW5nfGRlZmF1bHR8MTQ0NDI5NzI2NzI1NA; StartCity_Pkg=PkgStartCity=1; Session=SmartLinkCode=U167348&SmartLinkKeyWord=&SmartLinkQuary=&SmartLinkHost=&SmartLinkLanguage=zh; Union=AllianceID=3052&SID=167348&OUID=000401app-; adscityen=Beijing; appFloatCnt=4; _bfa=1.1449198788546.48efta.1.1449471157651.1449476051349.5.28; _bfs=1.1; mkt_ps=; _bfi=p1%3D101027%26p2%3D101027%26v1%3D28%26v2%3D27; _ga=GA1.2.134208463.1449198794; _gat=1; __zpspc=9.5.1449476054.1449476054.1%234%7C%7C%7C%7C%7C%23; _jzqco=%7C%7C%7C%7C1449455613620%7C1.96987684.1449198795340.1449471160976.1449476054531.1449471160976.1449476054531.undefined.0.0.14.14; FD_SearchHistorty={\"type\":\"S\",\"data\":\"S%24%u547C%u548C%u6D69%u7279%28HET%29%24HET%242016-02-05%24%u4E4C%u9C81%u6728%u9F50%28URC%29%24URC\"}");

    httpGet.setHeader("Host", "flights.ctrip.com");

    httpGet.setHeader("refer", "http://flights.ctrip.com/booking/HET-URC-day-1.html");

    httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
%>