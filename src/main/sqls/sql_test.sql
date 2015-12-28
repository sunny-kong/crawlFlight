//查询所有起飞时间在2016-02-05  select * from flightinfo where departuretime between '2016-02-05 0:00:00' and '2016-02-05 23:59:59'
//查询每天的最低价 select min(price) from flightinfo group by to_char(optiontime,'dd')
//select min(price) ,optiontime from flightinfo group by   DATE_FORMAT(optiontime, "%d")
//select min(price),flightno from  flightinfo where optiontime between '2015-12-16 0:00:00' and '2015-12-16 23:59:59'
//select min(price) ,DATE_FORMAT(optiontime, "%Y-%m-%d") from flightinfo group by   DATE_FORMAT(optiontime, "%Y-%m-%d")
//SELECT f2.id,f2.flightno,f2.parentname,f2.departuretime,f2.landingtime,f2.departurecity,f2.landingcity,f2.price,f1.optiontime FROM flightinfo f2,(select min(price) price ,DATE_FORMAT(optiontime, "%Y-%m-%d") optiontime from flightinfo group by   DATE_FORMAT(optiontime, "%Y-%m-%d"))f1 WHERE f2.price=f1.price and DATE_FORMAT(f2.optiontime, "%Y-%m-%d")=f1.optiontime





select min(price),max(flightno),max(departuretime),min(optiontime) from  flightinfo group by DATE_FORMAT(departuretime, "%Y-%m-%d")

select * from (select * from  flightinfo GROUP BY  DATE_FORMAT(departuretime, "%Y-%m-%d") HAVING optiontime between '2015-12-16 0:00:00' and '2015-12-16 23:59:59') f where f.departuretime between '2016-02-05 0:00:00' and '2016-02-05 23:59:59'

//
select min(price) from (select * from (select * from  flightinfo where optiontime between '2015-12-16 0:00:00' and '2015-12-16 23:59:59') f where f.departuretime between '2016-02-05 0:00:00' and '2016-02-05 23:59:59') f2 group by DATE_FORMAT(optiontime, '%Y-%m-%d %H:%i:%s')
//操作时间，精确到小时
select distinct(DATE_FORMAT(optiontime, "%Y-%m-%d %H"))optiontime from flightinfo order by optiontime

1、查询所有02-05的数据
SELECT * from flightinfo where departuretime BETWEEN '2016-02-05 0:00:00' and '2016-02-05 23:59:59'
2、查询每天每小时的数据

select * from (select * from (select * from  flightinfo where optiontime between '2015-12-18 0:00:00' and '2015-12-18 23:59:59') f where f.departuretime between '2016-02-05 0:00:00' and '2016-02-05 23:59:59') f1 group by DATE_FORMAT(f1.optiontime, "%Y-%m-%d %H")


SELECT * from flightinfo where departuretime BETWEEN '2016-02-05 0:00:00' and '2016-02-05 23:59:59' and  optiontime BETWEEN '2016-02-05 0:00:00' and '2016-02-05 23:59:59'




