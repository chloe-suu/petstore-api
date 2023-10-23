package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path){
        this.path = path;
    }

    public int getTotalRowOfASheet(String sheetName) throws IOException{
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int totalRows = sheet.getLastRowNum();

        workbook.close();
        fi.close();
        return totalRows;
    }
    public int getTotalCellOfARow(String sheetName, int rowNo) throws IOException{
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNo);
        int totalCells = row.getLastCellNum();

        workbook.close();
        fi.close();
        return totalCells;
    }
    public String getCellData(String sheetName, int rowNo, int cellNo) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNo);
        cell = row.getCell(cellNo);

        DataFormatter formatter= new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); //return data as string value
        } catch (Exception e){
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }
}
