package CommonFunctions;


import Configs.TestConfigsHerokuApp;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Optional;

import java.util.concurrent.TimeUnit;

import static CommonFunctions.Utility.takePageScreenShotBase64;


public class HeroKuCommonFunctions {

    public static WebDriver getBrowserSpecificDriver(String mode){


        WebDriver wb=null;

        if(TestConfigsHerokuApp.BROWSER_TO_USE.toUpperCase().contains("CHROME")){
            WebDriverManager.chromedriver().setup();
            DesiredCapabilities desCap = new DesiredCapabilities();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            if(mode.toUpperCase().contains("INCOGNITO")){
                chromeOptions.addArguments("--incognito");
            }
            chromeOptions.merge(desCap);
            wb = new ChromeDriver(chromeOptions);
        }
        else if(TestConfigsHerokuApp.BROWSER_TO_USE.toUpperCase().contains("FIREFOX")){
            WebDriverManager.firefoxdriver().setup();
            DesiredCapabilities desCap = new DesiredCapabilities();
            FirefoxOptions fxOptions = new FirefoxOptions();
            fxOptions.addArguments("--start-maximized");
            if(mode.toUpperCase().contains("INCOGNITO")){
                fxOptions.addArguments("--incognito");
            }
            wb = new FirefoxDriver();
        }


        return wb;

    }//getBrowserSpecificDriver

    public WebDriver launchHeroKu(ExtentTest extentTest) {
        String mode = "normal";
        WebDriver wbDriver = getBrowserSpecificDriver(mode);
        wbDriver.manage().timeouts().pageLoadTimeout(180,TimeUnit.SECONDS);//if not set selenium defaults wait is 300 sec.

        wbDriver.get(TestConfigsHerokuApp.URL);
        //Assert.assertEquals( wbDriver.getTitle(),"The Internet","Incorrect Title");
        wbDriver.getTitle();

        return wbDriver;
    }//end launchHeroKu

    public WebDriver launchHeroKu(String mode, ExtentTest extentTest) {
        WebDriver wbDriver = getBrowserSpecificDriver(mode);
        wbDriver.manage().timeouts().pageLoadTimeout(120,TimeUnit.SECONDS);//if not set selenium defaults wait is 300 sec.

        wbDriver.get(TestConfigsHerokuApp.URL);
       // Assert.assertEquals( wbDriver.getTitle(),"The Internet","Incorrect Title");
        wbDriver.getTitle();

        return wbDriver;
    }//end launchHeroKu


}
