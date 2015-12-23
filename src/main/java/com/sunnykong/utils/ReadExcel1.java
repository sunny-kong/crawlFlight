package com.sunnykong.utils;

import com.sunnykong.bean.MonitorInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a
 * Date: 15-5-13
 * Time: 上午9:45
 * To change this template use File | Settings | File Templates.
 */
public class ReadExcel1 {
        /**
         * read the Excel file
         * @param path the path of the Excel file
         * @return
         * @throws IOException
         */
        public List<MonitorInfo>  readExcel(String path) throws IOException, InvalidFormatException {
            if (path == null || "".equals(path)) {
                return null;
            } else {
                String postfix = Util.getPostfix(path);
                if (!"".equals(postfix)) {
                    if ("xls".equals(postfix)) {
                        return readXls(path);
                    } else if ("xlsx".equals(postfix)) {
                        return readXlsx(path);
                    }
                } else {
                    System.out.println(path + ": Not the Excel file!");
                }
            }
            return null;
        }

        /**
         * Read the Excel 2010
         * @param path the path of the excel file
         * @return
         * @throws IOException
         */
        public List<MonitorInfo> readXlsx(String path) throws IOException, InvalidFormatException {
            System.out.println("Processing...." + path);
            InputStream is = new FileInputStream(path);
            Workbook xssfWorkbook = Util.create(is);
//            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            MonitorInfo monitorInfo = null;
            List<MonitorInfo> list = new ArrayList<MonitorInfo>();
            // Read the Sheet
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                Sheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // Read the Row
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    Row xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                        monitorInfo = new MonitorInfo();
                        Cell monitorCompanyName = xssfRow.getCell(0);
                        Cell homePageAddr = xssfRow.getCell(1);
                        Cell monitorJs = xssfRow.getCell(2);
                        Cell monitorRequestAddr = xssfRow.getCell(3);
                        monitorInfo.setMonitorcompanyname(getValue((HSSFCell) monitorCompanyName));
                        monitorInfo.setHomepageaddr(getValue((HSSFCell) homePageAddr));
                        monitorInfo.setMonitorjs(getValue((HSSFCell) monitorJs));
                        monitorInfo.setMonitorrequestaddr(getValue((HSSFCell) monitorRequestAddr));
                        list.add(monitorInfo);
                    }
                }
            }
            return list;
        }

        /**
         * Read the Excel 2003-2007
         * @param path the path of the Excel
         * @return
         * @throws IOException
         */
        public List<MonitorInfo> readXls(String path) throws IOException {
            System.out.println("Processing@@@@@" + path);
            InputStream is = new FileInputStream(path);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            MonitorInfo monitorInfo = null;
            List<MonitorInfo> list = new ArrayList<MonitorInfo>();
            // Read the Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // Read the Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        monitorInfo = new MonitorInfo();
                        HSSFCell monitorCompanyName = hssfRow.getCell(0);
                        HSSFCell homePageAddr = hssfRow.getCell(1);
                        HSSFCell monitorJs = hssfRow.getCell(2);
                        HSSFCell monitorRequestAddr = hssfRow.getCell(3);
                        monitorInfo.setMonitorcompanyname(getValue(monitorCompanyName));
                        monitorInfo.setHomepageaddr(getValue(homePageAddr));
                        monitorInfo.setMonitorjs(getValue(monitorJs));
                        monitorInfo.setMonitorrequestaddr(getValue(monitorRequestAddr));
                        list.add(monitorInfo);
                    }
                }
            }
            return list;
        }

        @SuppressWarnings("static-access")
        private String getValue(XSSFCell xssfRow) {
            if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
                return String.valueOf(xssfRow.getBooleanCellValue());
            } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
                return String.valueOf(xssfRow.getNumericCellValue());
            } else {
                return String.valueOf(xssfRow.getStringCellValue());
            }
        }


        private String getValue(HSSFCell hssfCell) {
            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(hssfCell.getBooleanCellValue());
            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                return String.valueOf(hssfCell.getNumericCellValue());
            } else {
                return String.valueOf(hssfCell.getStringCellValue());
            }
        }
    }

