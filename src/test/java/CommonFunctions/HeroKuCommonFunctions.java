package CommonFunctions;

import Configs.TestConfigsHerokuApp;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class HeroKuCommonFunctions {

    public WebDriver launchHeroKu(ExtentTest extentTest) {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities desCap = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.merge(desCap);

        if(TestConfigsHerokuApp.URL.equals(null) || TestConfigsHerokuApp.URL.equals("")){
            System.out.println("\n******** Setting Env to SIT for individual method runs ********\n");
            TestConfigsHerokuApp.setConfigsForHeroku("SIT");
        }
        WebDriver wbDriver = new ChromeDriver(chromeOptions);
        wbDriver.get(TestConfigsHerokuApp.URL);

        //wbDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        TakesScreenshot takesScreenshot = (TakesScreenshot) wbDriver;
        extentTest.log(Status.INFO,
                "Home page opened",
                MediaEntityBuilder.createScreenCaptureFromBase64String(takesScreenshot.getScreenshotAs(OutputType.BASE64),"HomePage").build());


        return wbDriver;
    }//end launchHeroKu

    public WebDriver launchHeroKu(String mode,ExtentTest extentTest) {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities desCap = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        if(mode.toUpperCase().equals("INCOGNITO")){
            chromeOptions.addArguments("--incognito");
        }
        chromeOptions.merge(desCap);

        if(TestConfigsHerokuApp.URL.equals(null) || TestConfigsHerokuApp.URL.equals("")){
            System.out.println("\n******** Setting Env to SIT for individual method runs ********\n");
            TestConfigsHerokuApp.setConfigsForHeroku("SIT");
        }
        WebDriver wbDriver = new ChromeDriver(chromeOptions);

        wbDriver.get(TestConfigsHerokuApp.URL);

        TakesScreenshot takesScreenshot = (TakesScreenshot) wbDriver;
        extentTest.log(Status.INFO,
                "Home page opened",
                MediaEntityBuilder.createScreenCaptureFromBase64String(takesScreenshot.getScreenshotAs(OutputType.BASE64),"HomePage").build());

        return wbDriver;

    }//end launchHeroKu

}
