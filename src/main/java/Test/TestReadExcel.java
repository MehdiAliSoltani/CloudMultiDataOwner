/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mehdi
 */
public class TestReadExcel {

    public static void main(String[] args) throws IOException {

        // Location of the source file
        String sourceFilePath = "/home/mehdi/123.xls";

        FileInputStream fileInputStream = null;

        // Array List to store the excel sheet data
        List excelData = new ArrayList();

        try {

            // FileInputStream to read the excel file
            fileInputStream = new FileInputStream(sourceFilePath);

            // Create an excel workbook
            HSSFWorkbook excelWorkBook = new HSSFWorkbook(fileInputStream);

            // Retrieve the first sheet of the workbook.
            HSSFSheet excelSheet = excelWorkBook.getSheetAt(0);

            // Iterate through the sheet rows and cells.
            // Store the retrieved data in an arrayList
            List<List<String>> excel = new ArrayList<List<String>>();
            Iterator rows = excelSheet.rowIterator();
            while (rows.hasNext()) {
                List<String> rrow = new  ArrayList<String>();
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                List cellData = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    cellData.add(cell);
                    rrow.add(cell.toString());
                }
                excel.add(rrow);
                excelData.add(cellData);
            }

            // Print retrieved data to the console
            for (int rowNum = 0; rowNum < excelData.size(); rowNum++) {

                List list = (List) excelData.get(rowNum);

                for (int cellNum = 0; cellNum < list.size(); cellNum++) {

                    HSSFCell cell = (HSSFCell) list.get(cellNum);

                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        System.out.print(cell.getRichStringCellValue().getString() + " ");
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        System.out.print(cell.getNumericCellValue() + " ");
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                        System.out.println(cell.getBooleanCellValue() + " ");
                    }
                }
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
