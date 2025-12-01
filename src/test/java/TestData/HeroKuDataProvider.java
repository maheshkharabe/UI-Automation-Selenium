package TestData;

import CommonFunctions.Utility;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;

public class HeroKuDataProvider {

    Utility objUtility = new Utility();


    @DataProvider(name = "BasicAuthData")
    public Object[][] getBasicAuthData() throws Exception {

        XSSFWorkbook workbook = objUtility.getTestDataFile();
        XSSFSheet sheetBasicAuth = workbook.getSheet("BASIC_AUTH");
        int numberOfRows = sheetBasicAuth.getPhysicalNumberOfRows();
        int numberOfColumns = sheetBasicAuth.getRow(0).getLastCellNum();
        Object[][] dataSet = new Object[numberOfRows-1][numberOfColumns];

       DataFormatter dataFormatter =  new DataFormatter();
        for (int i = 0; i < numberOfRows-1; i++) {

            for (int j = 0; j < numberOfColumns; j++) {

                //Exclude first row as it contains Headers
                //ready value as String using 'formatCellValue'
                dataSet[i][j]=dataFormatter.formatCellValue(sheetBasicAuth.getRow(i+1).getCell(j));
                //System.out.println(dataSet[i][j].toString());

            }
            //System.out.println();
        }

        workbook.close();

        objUtility.closeFileConnections();

        return dataSet;

    }//end getPetData

    @DataProvider(name = "ChallengingDOMData",parallel = true)
    public Object[][] getDataForChallengingDOM() throws Exception {

        XSSFWorkbook workbook = objUtility.getTestDataFile();
        XSSFSheet sheet = workbook.getSheet("CHALLENGING_DOM");
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        int numberOfColumns = sheet.getRow(0).getLastCellNum();
        Object[][] dataSet = new Object[numberOfRows-1][numberOfColumns];

        DataFormatter dataFormatter =  new DataFormatter();
        for (int i = 0; i < numberOfRows-1; i++) {

            for (int j = 0; j < numberOfColumns; j++) {

                //Exclude first row as it contains Headers
                //ready value as String using 'formatCellValue'
                dataSet[i][j]=dataFormatter.formatCellValue(sheet.getRow(i+1).getCell(j));
                //System.out.println(dataSet[i][j].toString());

            }
            //System.out.println();
        }

        workbook.close();

        objUtility.closeFileConnections();

        return dataSet;

    }//end getPetData
}
