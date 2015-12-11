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

<table>
    <%
        List<FlightInfo> flightInfoList = (List<FlightInfo>) request.getSession().getAttribute("flightInfoList");
        for (FlightInfo flightInfo : flightInfoList) {
    %>
    <tr>
        <td><%=flightInfo.getFlightNo()%></td>
        <td><%=flightInfo.getPrice()%></td>
        <td><%=flightInfo.getDeparturetime()%></td>
        <td><%=flightInfo.getLandingtime()%></td>
    </tr>
    <%
        }
    %>
</table>


