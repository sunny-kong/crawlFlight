package com.sunnykong.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a
 * Date: 15-5-13
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    public static String getPostfix(String path) {
        if (path == null || "".equals(path.trim())) {
            return "";
        }
        if (path.contains(".")) {
            return path.substring(path.lastIndexOf(".") + 1, path.length());
        }
        return "";
    }
    public static Workbook create(InputStream inp) throws IOException,InvalidFormatException {
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if (POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }

   /* public static List<Timestamp> getTimeRange(Timestamp startTime, Timestamp endTime) {
        List<Timestamp> timeRanges = new ArrayList();
        for (; startTime.before(endTime) || startTime.equals(endTime); ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            timeRanges.add(new Timestamp(cal.getTimeInMillis()));
            cal.add(Calendar.HOUR_OF_DAY, 1);
            startTime.setTime(cal.getTimeInMillis()); int totalNum=timeRanges.size();
        }
        return timeRanges;
    }*/
}
