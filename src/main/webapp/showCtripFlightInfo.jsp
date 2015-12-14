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
<html>
<head>
    <title>机票信息显示曲线</title>
    <script type="text/javascript" src="js/dist/echarts.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        // 配置路径
        require.config({
            paths: {
                echarts: 'js/dist'
            }
        });
        $.getJSON('/ctripcrawlservlet', function (json) {
            showCompareEchart(json.timeRange, json.prices, json.flightNos);
        });

        function showCompareEchart(categories, values, flightNos) {//显示图表
            // 按需加载
            require(
                    [
                        'echarts',
                        'echarts/chart/bar',
                        'echarts/chart/line'
                    ],
                    function (ec) {
                        var chart = document.getElementById('chart');
                        echart = ec.init(chart);

                        echart.showLoading({
                            text: '正在努力加载中...'
                        });
                        // 同步执行
                        $.ajaxSettings.async = false;
                        option = {
                            tooltip: {
                                trigger: 'axis'
                            },
                            legend: {
                                data: flightNos
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    magicType : {show: true, type: ['line', 'bar']},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
//                            toolbox: {
//                                show: true,
//                                orient: 'vertical',
//                                y: 'center',
//                                feature: {
//                                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
//                                }
//                            },
                            calculable: true,
                            xAxis: [
                                {
                                    type: 'category',
                                    boundaryGap: false,
                                    data: categories
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value'
                                }
                            ],
                            series: function () {
                                var serie = [];
                                for (var i = 0; i < flightNos.length; i++) {
                                    var item = {
                                        name: flightNos[i],
                                        type: 'line',
//                                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                                        data: values[i]
                                    }
                                    serie.push(item);
                                }
                                return serie;
                            }()
                        };
                        echart.setOption(option);
                        echart.hideLoading();
                        _myChart = echart;
                    }
            );
        }
    </script>

</head>

<body>
<div id="chart" style="width: 100%;height: 100%;margin: 0 auto;"></div>
</body>
</html>

