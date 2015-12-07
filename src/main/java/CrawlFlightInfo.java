import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.lang.model.element.Element;
import java.io.IOException;

/**
 * Created by KXJ on 2015-12-07.
 */
public class CrawlFlightInfo {
    public static void main(String[] args) throws IOException {
        String url = "http://flights.ctrip.com/booking/HET-URC-day-1.html";
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc);
        String title = doc.title();
        System.out.println(title);
        Elements flightlist2= doc.select("#J_flightlist2");
        System.out.println(flightlist2);
    }
}
