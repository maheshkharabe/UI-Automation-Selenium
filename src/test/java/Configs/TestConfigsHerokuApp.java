/****** Explore selenium basics: https://the-internet.herokuapp.com/  *******/

package Configs;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestConfigsHerokuApp {

    public static String URL = "";
    public static String ENV_FLAG = "";
    public static final String PATH_FOR_SCREEN_SHOTS="target/TestScreenPrints/";
    public static final String PATH_FOR_HTML_REPORT="TestReports/ExtentReports/";
    public static final String PATH_TO_DATA_FILE ="src/test/java/TestData/TestData_HeroKu.xlsx";

    @Parameters({"environmentToUse"})
    @Test
    public static void setConfigsForHeroku(@Optional("SIT") String environment) {

        ENV_FLAG= environment;
        //You can set specific endpoints based on environment. If not provided, use "SIT" by default.
        System.out.println("\n***** Env Flag value received:" + environment);
        if (environment.equalsIgnoreCase("DEV")) {
            URL = "https://the-internet.herokuapp.com/";
            System.out.println("As Instructed-Executing DEV environment");
        } else if (environment.equalsIgnoreCase("UAT")) {
            URL = "https://the-internet.herokuapp.com/";
            System.out.println("As Instructed-Executing UAT environment");
        } else {
            URL = "https://the-internet.herokuapp.com/";
            System.out.println("Executing SIT environment");
        }
    }//end Configs.TestConfigsForAPIPetStore
}


