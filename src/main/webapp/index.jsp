<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.sunnykong.utils.DateUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
    <!-- Bootstrap 3.3.2 -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- FontAwesome 4.3.0 -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <!-- Ionicons 2.0.0 -->
    <link href="css/ionicons.min.css" rel="stylesheet" type="text/css"/>

    <!-- iCheck for checkboxes and radio inputs -->
    <link href="plugins/iCheck/all.css" rel="stylesheet" type="text/css"/>

    <%--<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">--%>
    <link href="css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
    <link href="plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet"/>
    <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
    <script src="js/monitor.js"></script>
</head>
<body>

<div style="width: 700px;margin-top: 100px;margin-left: 80px">
    <div class="box-header" style="margin-left: 400px;margin-bottom: 50px">
        <h3 class="box-title">航班信息</h3>
    </div>
    <div class="form-group">
        <div class="input-group">
            <label class="col-md-12 control-label"> 选择查询日期</label>

            <div class="input-group-addon">
                <i class="fa fa-clock-o"></i>
            </div>
            <input type="text" class="form-control form_date pull-right"
                   id="optiontimeRange" placeholder="区间"
                   value="<%=DateUtils.getPreviousMonth()%>"/>
        </div>
    </div>
    <div class="form-group">
        <div class="input-group">
            <label class="col-md-12 control-label"> 选择出发日期</label>

            <div class="input-group-addon">
                <i class="fa fa-clock-o"></i>
            </div>
            <input type="text" class="form-control form_date pull-right"
                   id="departuretime" name="departuretime" placeholder="时间"
                   value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" style="margin-right: 120px"> 选择出发城市</label>
        <select title="请选择出发城市" id="departurecity" name="departurecity" data-style="form-control"
                style="margin-right: 43px;width: 60%;padding:7px 0">
            <option value="HET">呼和浩特</option>
            <option value="URC">乌鲁木齐</option>
        </select>
    </div>

    <div class="form-group">
        <label class="col-md-2 control-label" style="margin-right: 120px"> 选择到达城市</label>
        <select title="请选择到达城市" id="landingcity" name="landingcity" data-style="form-control"
                style="margin-right: 43px;width: 60%;padding:7px 0">
            <option value="HET">呼和浩特</option>
            <option value="URC">乌鲁木齐</option>
        </select>
    </div>

    <div class="form-group" style="margin-left: 150px">
        <button type="button" class="btn btn-success" onclick="findByday()" style="margin-left: 100px">&nbsp;按日查询
        </button>
        <button type="button" class="btn btn-success" onclick="findByWeek()" style="margin-left: 50px">&nbsp;按周查询
        </button>
        <button type="button" class="btn btn-success" onclick="showInfo()" style="margin-left: 50px">&nbsp;航班信息</button>
    </div>

    <div class="form-group">
        <div class="col-sm-14">
            <span id="data"></span>
        </div>
    </div>

</div>
</body>

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script src="js/bootstrap-select.min.js" type="text/javascript"></script>
<!-- daterangepicker -->
<script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- bootstrap time picker -->
<script src="plugins/timepicker/bootstrap-timepicker.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript" src="plugins/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script type="text/javascript">

    $(function () {
        $(".datetimepicker.datetimepicker-dropdown-bottom-right.dropdown-menu:eq(0)").remove();//删除多余的时间插件框
        //Date range picker
        $('#optiontimeRange').daterangepicker({
                    language: 'zh-CN',
                    format: 'YYYY-MM-DD', //控件中from和to 显示的日期格式
                    separator: '~',
                    locale: {
                        applyLabel: '确定',
                        cancelLabel: '取消',
                        fromLabel: '起始时间',
                        toLabel: '结束时间',
                        customRangeLabel: '自定义',
                        daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                            '七月', '八月', '九月', '十月', '十一月', '十二月'],
                        firstDay: 1
                    },
                    startDate: moment().subtract('days', 29),
                    endDate: moment()
                },
                function (start, end, label) {
                    $('#reportrange span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
                }
        );
    });

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'yyyy-mm-dd'
    });

</script>
<script type="text/javascript">
    function formatDate(now) {
        var year = now.getYear() + 1900;
        var month = now.getMonth() + 1;
        var date = now.getDate();
        var hour = now.getHours();
        var minute = now.getMinutes();
        var second = now.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }
</script>
<script type="text/javascript">
    function findByWeek() {
        var departuretime = $("#departuretime").val();
        var departurecity = $("#departurecity").val();
        var landingcity = $("#landingcity").val();
        window.location.href = "<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showCtripFlightInfoOneDay.jsp?departurecity=" + departurecity + "&landingcity=" + landingcity + "&departuretime=" + departuretime;
    }
    function findByday() {
        var optiontime = $("#optiontimeRange").val();
        var departuretime = $("#departuretime").val();
        var departurecity = $("#departurecity").val();
        var landingcity = $("#landingcity").val();
        window.location.href = "<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showCtripFlightInfoOneHourse.jsp?optiontime=" + optiontime + "&departuretime=" + departuretime + "&departurecity=" + departurecity + "&landingcity=" + landingcity;
    }
    function showInfo() {
        $("#data").empty();
        var optiontime = $("#optiontimeRange").val();
        var departuretime = $("#departuretime").val();
        var departurecity = $("#departurecity").val();
        var landingcity = $("#landingcity").val();

        $.getJSON("<%=StringUtils.substringBeforeLast(request.getRequestURL().toString(),"/")%>/showFlightInfo?optiontime=" + optiontime + "&departuretime=" + departuretime + "&departurecity=" + departurecity + "&landingcity=" + landingcity, function (json) {
            var flightInfoList = json.flightInfoList;
            var str;
            if (flightInfoList.length > 0) {
                str = "<div class=\"accordion-group\"><table class=\"table table-hover table-condensed\"><thead><tr><th class=\"alert alert-success\">共找到" + flightInfoList.length + "条数据</th></tr></thead></table>"
                str += "<table class=\"table table-hover table-condensed\"><thead><tr><td>航班号</td><td>起飞时间</td><td>降落时间</td><td>票价</td><td>起飞地点</td><td>降落地点</td><td>操作时间</td> <td>来源</td> </tr> </thead><tbody>";
                for (var i = 0; i < flightInfoList.length; i++) {
                    str += "<tr><td>" + flightInfoList[i].flightNo + "</td><td>" + flightInfoList[i].departuretime + "</td><td>" + flightInfoList[i].landingtime + "</td><td>" + flightInfoList[i].price + "</td><td>" + flightInfoList[i].departurecity + "</td><td>" + flightInfoList[i].landingcity + "</td><td>" + flightInfoList[i].optiontime + "</td><td>" + flightInfoList[i].parentname + "</td></tr>"
                }
                str += "</tbody></table></div>";
            } else {
                str = "<div class=\"accordion-group\"><table class=\"table table-hover table-condensed\"><thead><tr><th class=\"alert alert-danger\">没有找到数据 :(</th></tr></thead></table></div>";

            }
            $("#data").append(str);
        });


    }


</script>
</html>
