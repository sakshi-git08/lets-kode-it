package com.letskodeit.overview;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReadExample {
    public static void main(String q[]) {
        XSSFWorkbook ExcelWBook;
        XSSFSheet ExcelWSheet;
        XSSFCell ExcelCell;

        String path = System.getProperty("user.dir") + "//src//main//resources//testData//ExampleData.xlsx";
        String sheetName = "Scenario1";

        try {
            FileInputStream ExcelFile = new FileInputStream(path);//Read excel file into the input Stream
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);// al these getSheet() are provided by apache poi
            ExcelCell = ExcelWSheet.getRow(0).getCell(0);

            String cellData = ExcelCell.getStringCellValue();// this method will fail if we have numbers stored in excel sheet
            System.out.println("Cell Data Value is : " + cellData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}