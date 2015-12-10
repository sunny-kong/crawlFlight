<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sunnykong.bean.FlightInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2015-12-07
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<FlightInfo> flightInfoList = (List<FlightInfo>) request.getSession().getAttribute("flightInfoList");
    for (FlightInfo flightInfo : flightInfoList) {
%>
<%=flightInfo.getFlightNo()%>
<%=flightInfo.getPrice()%>
<%
    }
%>