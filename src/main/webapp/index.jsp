<%@ page import="org.apache.commons.lang3.StringUtils" %>
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
</head>
<body>

<div style="align-content: center">
      <div>查询信息</div>
      选择查询日期:<input name="" /><br>
      选择出发日期：<input name=""/><br>
      <button>航班信息</button>
      <button>按日查询</button>
      <button id="byMonth" onclick="findByMonth()">按周查询</button>
      <div id="data" style="height: 500px"></div>

</div>

</body>
<script type="text/javascript">
  function findByMonth(){
    window.location.href = "<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showCtripFlightInfoOneDay.jsp";
  }
</script>
</html>
