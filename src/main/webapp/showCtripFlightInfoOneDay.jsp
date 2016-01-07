<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2015-12-15
  Time: 18:10
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
    $.getJSON('/ctripcrawlonedayservlet?<%=request.getQueryString()%>', function (json) {
      if(json.departurecity=="HET"){
        $("h3").append("起飞时间：2016-02-01 到2016-02-07,出发城市："+json.departurecity+",到达城市："+json.landingcity+"统计时间段:  ,指定一周每天机票最低价曲线");
      }else{
        $("h3").append("起飞时间：2016-02-13 到2016-02-16,出发城市："+json.departurecity+",到达城市："+json.landingcity+"统计时间段:  ,指定4天每天机票最低价曲线");
      }

      showCompareEchart(json.optionTime, json.prices, json.departureTime);

      $("#button").append("<button type='button'  onclick='history.go(-1)'>返回</button>");
    });

    function showCompareEchart(optionTime, prices, departureTime) {//显示图表
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
                    data: departureTime
                  },
                  toolbox: {
                    show : true,
                    orient: 'vertical',
                    y: 'center',
                    feature : {
                      mark : {show: true},
                      dataView : {show: true, readOnly: false},
                      magicType : {show: true, type: ['line', 'bar']},
                      restore : {show: true},
                      saveAsImage : {show: true}
                    }
                  },
                  calculable: true,
                  xAxis: [
                    {
                      type: 'category',
                      boundaryGap: false,
                      data: optionTime
                    }
                  ],
                  yAxis: [
                    {
                      type: 'value'
                    }
                  ],
                  series: function () {
                    var serie = [];
                    for (var i = 0; i < departureTime.length; i++) {
                      var item = {
                        name: departureTime[i],
                        type: 'line',
//                                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: prices[i],

                        markPoint : {
                          data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                          ]
                        }/*,
                        markLine : {
                          data : [
                            {type : 'average', name: '平均值'}
                          ]
                        }*/
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
<h3 style="margin-left: 400px">
</h3>
<div id="chart" style="width: 750px;height: 500px;margin: 0 auto;"></div>
<div id="button" style="margin-left: 50%"></div>
</body>
</html>


