/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class ExcelRW {

    public void writeListToExcel(List<List<String>> sheet, String fileName, String sheetName) {

        // Using XSSF for xlsx format, for xls use HSSF
        Workbook workbook = new XSSFWorkbook();

        Sheet sheets = workbook.createSheet(sheetName);

        int rowIndex = 0;
        for (List<String> rows : sheet) {
            Row row = sheets.createRow(rowIndex++);
            int cellIndex = 0;

            for (String cell : rows) {
                row.createCell(cellIndex++).setCellValue(cell);

            }
        }

        //write this workbook in excel file.
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            workbook.write(fos);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public List<List<String>> readExcel(String fileName) throws IOException {
        FileInputStream fileInputStream = null;
        List<List<String>> excel = new ArrayList<List<String>>();
        // Array List to store the excel sheet data
        try {
            // FileInputStream to read the excel file
            fileInputStream = new FileInputStream(fileName);
            // Create an excel workbook
            HSSFWorkbook excelWorkBook = new HSSFWorkbook(fileInputStream);
            // Retrieve the first sheet of the workbook.
            HSSFSheet excelSheet = excelWorkBook.getSheetAt(0);
            // Iterate through the sheet rows and cells.
            // Store the retrieved data in an arrayList

            Iterator rows = excelSheet.rowIterator();
            while (rows.hasNext()) {
                List<String> rrow = new ArrayList<String>();
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    rrow.add(cell.toString());
                }
                excel.add(rrow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return excel;
    }
}
