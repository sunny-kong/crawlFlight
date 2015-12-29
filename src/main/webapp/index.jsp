<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sunnykong.bean.FlightInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2015-12-23
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="js/monitor.js"></script>
</head>
<body>

<div style="align-content: center">
    <div>查询信息</div>
    选择查询日期:<input name=""/><br>
    选择出发日期：<input name=""/><br>
    <button onclick="showInfo()">航班信息</button>
    <button id="byDay" onclick="findByday()">按日查询</button>
    <button id="byWeek" onclick="findByWeek()">按周查询</button>
    <div id="data" style="height: 500px"></div>
</div>

</body>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    function formatDate(now) {
        var year=now.getYear()+1900;
        var month=now.getMonth()+1;
        var date=now.getDate();
        var hour=now.getHours();
        var minute=now.getMinutes();
        var second=now.getSeconds();
        return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
    }
</script>
<script type="text/javascript">
    function findByWeek() {
        window.location.href = "<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showCtripFlightInfoOneDay.jsp";
    }
    function findByday() {
        window.location.href = "<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showCtripFlightInfoOneHourse.jsp";
    }
    function showInfo() {
        alert(formatDate(new Date()))
        $.getJSON("<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showFlightInfo",function(json){
            var flightInfoList=json.flightInfoList;
//            alert(flightInfoList);
            var str;
            if(flightInfoList.length>0){
                str+="<table><thead><tr><td>航班号</td><td>起飞时间</td><td>降落时间</td><td>票价</td><td>起飞地点</td><td>降落地点</td><td>操作时间</td> <td>来源</td> </tr> </thead><tbody>";
                for(var i=0;i<flightInfoList.length;i++){
                    str+="<tr><td>"+flightInfoList[i].flightNo+"</td><td>"+flightInfoList[i].departuretime+"</td><td>"+flightInfoList[i].landingtime+"</td><td>"+flightInfoList[i].price+"</td><td>"+flightInfoList[i].departurecity+"</td><td>"+flightInfoList[i].landingcity+"</td><td>"+flightInfoList[i].optiontime+"</td><td>"+flightInfoList[i].parentname+"</td></tr>"
                }
                str+="</tbody></table>";
            }else{
                str+="<thead><tr>未找到数据，请更换条件重试</tr></thead>";
            }
            $("#data").append(str);

        });
    }


</script>
</html>
