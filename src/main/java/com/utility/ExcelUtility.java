package com.utility;

import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {

    public static Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();
            data = new Object[totalRows][totalColumns];

            for (int i = 1; i<=totalRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j<totalColumns; j++) {
                    Cell cell = row.getCell(j);
                    data[i-1][j] = (cell == null) ? "" : cell.toString();
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            Assert.fail("ERROR : OCCURRED DURING FILE INPUT CREATION " + e.getMessage());
        }
        return data;
    }


}
