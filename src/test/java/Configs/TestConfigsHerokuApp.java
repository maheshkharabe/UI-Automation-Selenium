/****** Explore selenium basics: https://the-internet.herokuapp.com/  *******/

package Configs;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestConfigsHerokuApp {

    public static String URL = "";
    public static String ENV_FLAG = "";
    public static String BROWSER_TO_USE = "";
    public static final String PATH_FOR_SCREEN_SHOTS="target/TestScreenPrints/";
    public static final String PATH_FOR_HTML_REPORT="TestReports/ExtentReports/";
    public static final String PATH_TO_DATA_FILE ="src/test/java/TestData/TestData_HeroKu.xlsx";

    @Parameters({"environmentToUse","browserToUse"})
    @BeforeSuite
    public static void setConfigsForHeroku(@Optional("SIT") String environment,@Optional("ChromeBrowser") String browser) {

        BROWSER_TO_USE = browser;
        ENV_FLAG= environment;
        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%% CONFIG PARAMETERS RECEIVED %%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("Environment Flag:"+environment);
        System.out.println("Browser to use:"+browser);

        //You can set specific endpoints based on environment. If not provided, use "SIT" by default.
        switch (ENV_FLAG.toUpperCase()){

            case "DEV":
                    URL = "https://the-internet.herokuapp.com/";
                    System.out.println("************** As Instructed-Executing DEV environment **************");
                break;

            case "SIT":
                    URL = "https://the-internet.herokuapp.com/";
                    System.out.println("************** Executing SIT environment **************");
                  break;

            case "UAT":
                    URL = "https://the-internet.herokuapp.com/";
                    System.out.println("************** As Instructed-Executing UAT environment **************");
                break;


                default:
                    System.out.println("************** Provide valid environment, expected one of {DEV,SIT,UAT} **************");

        }
    }//end Configs.TestConfigsForAPIPetStore
}


