package utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviders {
    String fileName = "//testData//users.xlsx";
    String sheetName = "Sheet1";

    @DataProvider(name = "allData")
    public String[][] getAllData() throws IOException{
        String path = System.getProperty("user.dir") + fileName;
        XLUtility excel = new XLUtility(path);

        int rowNo = excel.getTotalRowOfASheet(sheetName);
        int colNo = excel.getTotalCellOfARow(sheetName,1);

        String apiData[][] = new String[rowNo][colNo];
        for (int i = 1; i<=rowNo;i++){
            for (int j = 0; j<colNo;j++){
                apiData[i-1][j] = excel.getCellData(sheetName,i,j);
            }
        }
        return apiData;
    }
    @DataProvider(name = "userNames")
    public String[] getUserName() throws IOException{
        String path = System.getProperty("user.dir") + fileName;
        XLUtility excel = new XLUtility(path);

        int rowNo = excel.getTotalRowOfASheet(sheetName);
        int colNo = excel.getTotalCellOfARow(sheetName,1);

        String apiData[] = new String[rowNo];
        for (int i = 1; i<=rowNo;i++){
            apiData[i-1] = excel.getCellData(sheetName,i,1);
        }
        return apiData;
    }
}
