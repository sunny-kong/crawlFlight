package com.sunnykong.service.impl;

import com.sunnykong.bean.AirPortCity;
import com.sunnykong.bean.FlightInfo;
import com.sunnykong.service.CrawlFlightService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KXJ on 2015-12-09.
 */
public class QunarCrawlFilghtServiceImpl implements CrawlFlightService {

    @Override
    public List<FlightInfo> crawl(AirPortCity from, AirPortCity to, String date) throws IOException {
        List<Header> headers = buildHeaders();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultHeaders(headers).build();
        HttpGet httpget = new HttpGet("http://flight.qunar.com/twell/longwell?&http%3A%2F%2Fwww.travelco.com%2FsearchArrivalAirport=%E4%B9%8C%E9%B2%81%E6%9C%A8%E9%BD%90&http%3A%2F%2Fwww.travelco.com%2FsearchDepartureAirport=%E5%91%BC%E5%92%8C%E6%B5%A9%E7%89%B9&http%3A%2F%2Fwww.travelco.com%2FsearchDepartureTime=2016-02-05&http%3A%2F%2Fwww.travelco.com%2FsearchReturnTime=2016-02-05&locale=zh&nextNDays=0&searchLangs=zh&searchType=OneWayFlight&tags=1&mergeFlag=0&xd=f1449634593000&wyf=0P8HfQdSbQP%2Fll2%2FERP8flP00YAFfQt%2FbdP%2FuUd8lyeFlUd%2F%7C1441321882698&from=tejia_iow_qiri&_token=6308");
        CloseableHttpResponse httpResponse = httpClient.execute(httpget);
        HttpEntity entity = httpResponse.getEntity();
        List<FlightInfo> flightInfoList = null;
        if (entity != null) {
            FlightInfo flightInfo = new FlightInfo();
//            flightInfo.setInfo(EntityUtils.toString(entity));
            EntityUtils.consume(entity);
        }


        return flightInfoList;
    }

    @Override
    public void saveFlightInfo(FlightInfo crawl) {

    }



    @Override
    public List<FlightInfo> findAllFlightInfo() {
        return null;
    }

    @Override
    public FlightInfo findFlightInfoByUniqueKey(String flightno, Timestamp departuretime, Timestamp landingtime, double price) {
        return null;
    }

    @Override
    public double findLowPrice(String flightno, Timestamp timeRange) {
        return 0;
    }

    private List<Header> buildHeaders() {
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(new BasicHeader("Accept", "*/*"));
        headerList.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));
        headerList.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));
        headerList.add(new BasicHeader("Connection", "keep-alive"));
        headerList.add(new BasicHeader("Cookie", "JSESSIONID=417389B7804BE4FFED974E5D8516BCA2; QN99=1696; QN1=O5cLFFYdrQiMO8IIMpT5Ag==; PHPSESSID=1h4d43mbno1u0boj9f64r1a8e2; QunarGlobal=10.88.170.33_-6999347_15063df12a7_-5e69|1444785422744; QN170=218.240.62.35_2c3b6b_0_6PyiU3bj8xF0aKpw5IqMzg4vrL88YNmzQnCYlD4QK28%3D; QN6=auto_4e0d874a; flight.trends=%u5317%u4EAC-%u4E0A%u6D77%3B%u5317%u4EAC-%u5E7F%u5DDE; QN171=\"weiyi,A200733258||0000\"; QN219=\"2347f03f-12c9-4b63-abf2-7998fcdff3bb-j(Af^gsa\"; QN25=dc07f09a-90c6-407a-a225-06ddb651a53b-9f992f90; _challenge=49b94394-5fd4-454d-bcf0-4d4a7cc178dd; QN43=2; QN42=bhvh4938; _q=U.ycjr4475; _t=24153597; csrfToken=qeplgtesdPv3kgRtt1tgzBqUqpD0JzkN; _v=i6ILYCJl85f-p4Cgbks4UHcoQQTN8Bbwv0yYUsFObFAcofvL-_zCEs4DewG6o76ckKhrq7kitRyLT8G_WVxhph4NDbjt1lXA4c_D7e2LeMRfMg3p9ImqRLCP6mpvZ9IYKGCBJ-3SirjBVvJJ65hp1NSf1qbxXwmGKr0qajW6Xs46; __ag_cm_=1449455725736; TWELLJSESSIONID=507912706E58F75D99E0BAFEF170B793; QN163=0; Hm_lvt_75154a8409c0f82ecd97d538ff0ab3f3=1448615238; Hm_lpvt_75154a8409c0f82ecd97d538ff0ab3f3=1449624425; ag_fid=0NFietmLhRNKKg7F; RT=s=1449634615399&r=http%3A%2F%2Fflight.qunar.com%2Fsite%2Foneway_list.htm%3FsearchDepartureAirport%3D%25E5%2591%25BC%25E5%2592%258C%25E6%25B5%25A9%25E7%2589%25B9%26searchArrivalAirport%3D%25E4%25B9%258C%25E9%25B2%2581%25E6%259C%25A8%25E9%25BD%2590%26searchDepartureTime%3D2016-02-05%26searchArrivalTime%3D2016-02-05%26nextNDays%3D0%26startSearch%3Dtrue%26fromCode%3DHET%26toCode%3DURC%26from%3Dfi_re_search%26lowestPrice%3Dnull; QN44=ycjr4475; _i=RBTKSStR1d_83KHwsQ0fxxIP45Hx; _vi=J-gdfV0arAkIwyllHz6GT5MSey-QwHOnZzTVohf4j_2BKbhxEOy62XfxbQW2Ngt1t8pA-3cpqkdi70klMEcz8gl9SHn19xiYE_KHJWwhZLvfpQt5RdAZTp7lF5Py_sBdwhsPgF1LuoOA-HKCF4456S_rVB9NuG1A1N028spi7AOO"));
        headerList.add(new BasicHeader("Host", "flight.qunar.com"));
        headerList.add(new BasicHeader("RA-Sid", "B848F0B1-20140612-145858-bbd665-0bd87b"));
        headerList.add(new BasicHeader("RA-Ver", "3.0.7"));
        headerList.add(new BasicHeader("Referer", "http://flight.qunar.com/site/oneway_list.htm?searchDepartureAirport=%E5%91%BC%E5%92%8C%E6%B5%A9%E7%89%B9&searchArrivalAirport=%E4%B9%8C%E9%B2%81%E6%9C%A8%E9%BD%90&searchDepartureTime=2016-02-05&searchArrivalTime=2016-02-05&nextNDays=0&startSearch=true&fromCode=HET&toCode=URC&from=fi_re_search&lowestPrice=null"));
        headerList.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36"));
        return headerList;
    }
}
