package com.ita.edu.greencity.ui.pages.admin_customers;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Objects;


public class ExcelRowCountExample {

    public FileInputStream fis = null;
    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;
    String filename = null;


    private ExcelRowCountExample(String filename) throws IOException {
        this.filename = filename;
        try {
            fis = new FileInputStream(filename);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
    }

    public static int readFromExcelFile() {
        ExcelRowCountExample erce = null;
        try {
            erce = new ExcelRowCountExample("D:\\Downloads\\Customers-Table.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(erce).getRowCount("Sheet1") - 1;

    }

    public static void deleteExcelFile() {

        try {
            Files.deleteIfExists(
                    Paths.get("D:\\Downloads\\Customers-Table.xlsx"));
        } catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum() + 1;
        }
    }

}