package CommonFunctions;

import Configs.TestConfigsHerokuApp;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static String currentDate_YYYY_MM_DD;
    public static String currentDate_yyyyMMdd;
    public static String currentTime_hhMMss;
    public static String currentTime_hh;
    public static String currentTime_MM;
    public static String currentTime_ss;
    public static String currentTime_SSS;
    public static String currentTime_hhMMssSSS;

    private File excelFile=null;
    private FileInputStream fis = null;
    private XSSFWorkbook workbook= null;


    public Utility(){
        Date currDateTm = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String currentDate = sdfDate.format(currDateTm);

        currentDate_YYYY_MM_DD = currentDate.substring(0,10);
        currentTime_hh = currentDate.substring(11,13);
        currentTime_MM = currentDate.substring(14,16);
        currentTime_ss = currentDate.substring(17,19);
        currentTime_SSS = currentDate.substring(20,23);
        currentTime_hhMMss = currentTime_hh+currentTime_MM+currentTime_ss;
        currentTime_hhMMssSSS = currentTime_hh+currentTime_MM+currentTime_ss+currentTime_SSS;

        sdfDate = new SimpleDateFormat("yyyyMMdd");
        currentDate = sdfDate.format(currDateTm);

        currentDate_yyyyMMdd = currentDate;
    }

    public void saveScreenPrints(WebDriver driver, String fileName){
        Utility currDateTm = new Utility();//Instantiate each time to get new timestamp
        File screenPrint = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String pathToSave= TestConfigsHerokuApp.PATH_FOR_SCREEN_SHOTS + Utility.currentDate_YYYY_MM_DD + "/" + fileName + "_" + Utility.currentTime_hhMMssSSS+ ".png";
        try {
            FileUtils.copyFile(screenPrint, new File(pathToSave));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//saveScreenPrints

    public XSSFWorkbook getTestDataFile(){
        excelFile = new File(TestConfigsHerokuApp.PATH_TO_DATA_FILE);
        try {
            fis = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;

    }

    public void closeFileConnections(){
        try {
            fis.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}//end class Utility
