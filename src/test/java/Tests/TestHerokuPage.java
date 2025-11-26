package Tests;

import CommonFunctions.HeroKuCommonFunctions;
import CommonFunctions.Utility;
import Configs.TestConfigsHerokuApp;
import TestData.HeroKuDataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static CommonFunctions.Utility.takeElementScreenShotBase64;
import static CommonFunctions.Utility.takePageScreenShotBase64;

public class TestHerokuPage {

    WebDriver driver=null;
    Utility objUtil = new Utility();
    int sleepTime = 1500;
    HeroKuCommonFunctions objHKCommomFun = new HeroKuCommonFunctions();
    ExtentReports extReport;
    ExtentSparkReporter sprkReporter;
    ExtentTest extentTest;
    String pathToExtentReportFile="";

    @BeforeSuite
    public void setExtentReportTest(){
        extReport = new ExtentReports();
        pathToExtentReportFile = TestConfigsHerokuApp.PATH_FOR_HTML_REPORT + Utility.currentDate_YYYY_MM_DD + "/" + TestHerokuPage.class.getName() + "_" + Utility.currentTime_hhMMssSSS+ ".html";
        sprkReporter = new ExtentSparkReporter(pathToExtentReportFile);
        extReport.attachReporter(sprkReporter);
        extReport.setSystemInfo("OS",System.getProperty("os.name"));
        extReport.setSystemInfo("Java Version",System.getProperty("java.version"));
        extReport.setSystemInfo("Environment",TestConfigsHerokuApp.ENV_FLAG);
        extReport.setSystemInfo("Browser",TestConfigsHerokuApp.BROWSER_TO_USE);

    }//setExtentReportTest

    @BeforeTest
    public void createTestForReport(ITestContext testContext){
        extentTest = extReport.createTest(testContext.getName()).info(MarkupHelper.createLabel("Explore the UI: The Internet - HeroKu", ExtentColor.INDIGO));
    }


    @Test(groups = {"Sanity"})
    public void SplitTest() throws InterruptedException {
        /******************************************  Purpose of Split Test *****************************************/
        String infomation =" To identify which version yields better results and optimize user experience or marketing effectiveness.\n" +
                "         Two variations (A and B) are created, differing in one specific element (e.g., headline, color, layout).\n" +
                "         A random sample of users is divided into two groups, with each group exposed to one version.\n" +
                "         Performance is measured using specific metrics, such as conversion rates, click-through rates, or user engagement." +
                "         In this case: Page loads with different contents randomly e.g.'A/B Test Variation 1' or 'A/B Test Control'";

        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());

        SoftAssert softAssert = new SoftAssert();

        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("A/B Testing")).click();
        Thread.sleep(sleepTime);
        /****Store screen print as file on local
         objUtil.saveScreenPrintAsFile(driver,Thread.currentThread().getStackTrace()[1].getMethodName()); *****/
        extentTest.log(Status.INFO,"A/B Testing page opened", takePageScreenShotBase64(driver));



        //Page loads with different contents randomly e.g.'A/B Test Variation 1' or 'A/B Test Control'
        WebElement element = driver.findElement(By.cssSelector(".example > h3"));
        String tagTest = element.getText();

        softAssert.assertTrue(tagTest.contains("A/B Test"),"Incorrect Text Description");
        extentTest.log(Status.PASS,infomation, takeElementScreenShotBase64(element));


        softAssert.assertAll();

    }//end SplitTest

    @Test(groups = {"Sanity"})
    public void AddRemoveElement() throws InterruptedException {
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();
        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Add/Remove Elements")).click();
        Thread.sleep(sleepTime);
        //objUtil.saveScreenPrintAsFile(driver,Thread.currentThread().getStackTrace()[1].getMethodName());

        extentTest.log(Status.INFO,"Add/Remove Elements Page opened ", takePageScreenShotBase64(driver));


        WebElement element = driver.findElement(By.cssSelector("#content > h3"));
        softAssert.assertEquals(element.getText(),"Add/Remove Elements","Incorrect Text Description");

        element = driver.findElement(By.cssSelector(".example > button"));
        softAssert.assertEquals(element.getText(),"Add Element","No button found labelled as -Add Element-");

        //Click on 'Add Element' button
        element.click();
        Thread.sleep(sleepTime);
        extentTest.log(Status.INFO,"Clicked on Add Button", takePageScreenShotBase64(driver,"AddBttnClicked"));

        //Upon one click, only one button should be added with label 'Delete'
        WebElement buttonDeleteElement =driver.findElement(By.cssSelector("button.added-manually"));
        softAssert.assertEquals(buttonDeleteElement.getText(),"Delete","Upon addElement, button appeared but with incorrect lable");
        int countOfButtonsAfterAdd = driver.findElements(By.cssSelector("button.added-manually")).size();
        softAssert.assertEquals(countOfButtonsAfterAdd,1,"Incorrect number of buttons added");

        //Click on 'Delete' button
        buttonDeleteElement.click();
        Thread.sleep(sleepTime);
        extentTest.log(Status.INFO,"Clicked on delete button ", takePageScreenShotBase64(driver,"DeleteClicked"));


        //Button should be removed
        int countOfButtonsAfterDelete = driver.findElements(By.cssSelector("button.added-manually")).size();
        softAssert.assertEquals(countOfButtonsAfterDelete,0,"Delete did not removed the button");

        extentTest.pass("Add/Remove button functions as expected ");


        softAssert.assertAll();

    }//end AddRemoveElement

    @Test(dataProvider = "BasicAuthData",dataProviderClass = HeroKuDataProvider.class)
    public void BasicAuth(String userName, String password, String expMessage) throws InterruptedException {
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();

        driver = objHKCommomFun.launchHeroKu("incognito",extentTest);//set Incognito mode for fresh start each time

        driver.findElement(By.linkText("Basic Auth")).click();
        Thread.sleep(sleepTime);

        //Handle Login pop up which requires credentials to access page for further user actions
        //Pass credentials using URL, syntax "https://username:password@URL"
        driver.get("https://"+userName+":"+password+ "@the-internet.herokuapp.com/basic_auth");
        Thread.sleep(sleepTime);

        WebElement element = driver.findElement(By.cssSelector(".example > p"));
        softAssert.assertEquals(element.getText(),expMessage,"Incorrect confirmation message");
        extentTest.pass("Basic Auth done ", takePageScreenShotBase64(driver));

        softAssert.assertAll();

    }//end BasicAuth

    @Test()
    public void BrokenImages() throws InterruptedException {
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();
        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Broken Images")).click();
        Thread.sleep(sleepTime);
        //objUtil.saveScreenPrintAsFile(driver,Thread.currentThread().getStackTrace()[1].getMethodName());

        extentTest.log(Status.INFO,"Broken Images Page opened ",takePageScreenShotBase64(driver));

        /*****************************************************************************
         To find broken images on page, find all 'img' tags , get URL from attribute
         When we hit URL, if we get 200 then good image else its not available(i.e. broken)
         ******************************************************************************/

        List<WebElement> allImageTags = driver.findElements(By.tagName("img"));
        System.out.println("Number of images:"+allImageTags.size());

        for (WebElement image:allImageTags) {
            //get url from each image tag with its attribute 'src'
            String urlString = image.getAttribute("src");

            //Create URL connection
            try {
                URL url = new URL(urlString); //convert string url to java URL object
                URLConnection urlConn = url.openConnection(); // create connection for URL
                HttpURLConnection httpUrl = (HttpURLConnection) urlConn; //Type cast to HTTP connection
                httpUrl.setConnectTimeout(10000);//set timeout to wait for connection establishment before timeout
                httpUrl.connect();//send request to server using 'connect()'

                if(httpUrl.getResponseCode()==200){
                    System.out.println("Good Image: "+urlString+" >> "+ httpUrl.getResponseCode() + " >> "+ httpUrl.getResponseMessage());
                    extentTest.pass("Good Image",takeElementScreenShotBase64(image,urlString));
                }else{
                    System.out.println("Broken Image: "+urlString+" >> "+ httpUrl.getResponseCode() + " >> "+ httpUrl.getResponseMessage());
                    extentTest.warning("Broken Image",takeElementScreenShotBase64(image,urlString));
                }

                httpUrl.disconnect();

            } catch (Exception e) {
                System.err.println("Exception handling image, also counted as broke image:"+urlString);
                extentTest.warning(e,takeElementScreenShotBase64(image,urlString));
                e.printStackTrace();
            }

        }//end foreach


    }//end BrokenImages

    @Test
    public void CheckBoxOperations() throws InterruptedException {
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();

        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Checkboxes")).click();
        Thread.sleep(sleepTime);
        extentTest.log(Status.INFO,"Check box Page opened ",takePageScreenShotBase64(driver));

        WebElement element = driver.findElement(By.cssSelector(".example > h3"));
        softAssert.assertEquals(element.getText(), "Checkboxes", "Incorrect Text Description");

        //Check for default selection and Click function- FOR 'checkbox 1'
        WebElement elementChkBox1 = driver.findElement(By.xpath("//form[@id=\"checkboxes\"]/input[@type=\"checkbox\"][1]"));
        Thread.sleep(sleepTime);
        softAssert.assertEquals(elementChkBox1.isSelected(),false,"Checkbox 1 should NOT be selected by default");
        elementChkBox1.click();
        extentTest.log(Status.INFO, "Check box1 checked",takePageScreenShotBase64(driver,"Checkbox1"));

        Thread.sleep(sleepTime);
        softAssert.assertEquals(elementChkBox1.isSelected(),true,"Checkbox 1 should be selected after click operation");

        //Check for default selection and Click function- FOR 'checkbox 2'
        WebElement elementChkBox2 = driver.findElement(By.xpath("//form[@id=\"checkboxes\"]/input[@type=\"checkbox\"][2]"));

        softAssert.assertEquals(elementChkBox2.isSelected(),true,"Checkbox 2 should be selected by default");
        //Perform Click action and check status changes
        elementChkBox2.click();
        extentTest.log(Status.INFO, "Check box2 unchecked",takePageScreenShotBase64(driver,"Checkbox2"));

        Thread.sleep(sleepTime);
        softAssert.assertEquals(elementChkBox1.isSelected(),true,"Checkbox 2 should be unselected after click operation");

        softAssert.assertAll();
        extentTest.pass("Check box operations completed");
    }//end CheckBoxOperations


    @Test
    public void ContextMenu() throws InterruptedException {
        /******************************************  Context Menu ***************************************
         A context menu in a web application is a pop-up menu that appears upon user interaction,
         typically a right-click or a long press.Displays actions that are contextually relevant,
         such as copy, paste, delete, or specific functions related to the selected item
         **************************************************************************************************/
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();

        driver = objHKCommomFun.launchHeroKu(extentTest);
        Actions action = new Actions(driver);

        driver.findElement(By.linkText("Context Menu")).click();
        Thread.sleep(sleepTime);
        extentTest.log(Status.INFO, "Context Menu Page opened ", takePageScreenShotBase64(driver));

        WebElement elementContxMn = driver.findElement(By.id("hot-spot"));
        action.contextClick(elementContxMn).perform();//Right click on element
        Thread.sleep(sleepTime);
        Alert simpleAlert = driver.switchTo().alert();
        softAssert.assertEquals(simpleAlert.getText(),"You selected a context menu","Incorrect alert text description");
        simpleAlert.accept();

        extentTest.log(Status.PASS, "Context Menu completed",takePageScreenShotBase64(driver,"ContextCompleted"));

        softAssert.assertAll();

    }//end ContextMenu


    @Test
    public void DisappearingElement() throws InterruptedException {
        /******************************************  Disappearing Element *********************************
         Explore different approaches to handle situation.
         In current website- 'Gallery' option on Disappearing Elements page may not always present
         Apply logic on such elements- Perform actions only if element is present.
         **************************************************************************************************/
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();

        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Disappearing Elements")).click();
        Thread.sleep(sleepTime);

        WebElement element = driver.findElement(By.cssSelector(".example > h3"));
        softAssert.assertEquals(element.getText(), "Disappearing Elements", "Incorrect Text Description");

        extentTest.log(Status.INFO,"Disappearing Elements page opened",takePageScreenShotBase64(driver));

        //Click on Gallery only if displayed and check URL changes
        try {
            WebElement elementGallery = driver.findElement(By.linkText("Gallery"));

            extentTest.log(Status.PASS, "Gallery section is available in this test run and did not dissapear", takeElementScreenShotBase64(elementGallery,"GallerySection"));

            elementGallery.click();
            Thread.sleep(sleepTime);
            String eleUrl = driver.getCurrentUrl();
            objUtil.saveScreenPrintAsFile(driver, Thread.currentThread().getStackTrace()[1].getMethodName());
            softAssert.assertTrue(eleUrl.contains("gallery"),"Redirected to incorrect webpage");

        }
        catch (Exception NoSuchElementException){

            extentTest.fail(NoSuchElementException,takePageScreenShotBase64(driver));
        }
        softAssert.assertAll();

    }//end DisappearingElement

    @Test
    public void DragAndDrop() throws InterruptedException {

        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();

        driver = objHKCommomFun.launchHeroKu(extentTest);
        Actions action = new Actions(driver);


        driver.findElement(By.linkText("Drag and Drop")).click();
        Thread.sleep(sleepTime);

        WebElement element = driver.findElement(By.cssSelector(".example > h3"));
        softAssert.assertEquals(element.getText(), "Drag and Drop", "Incorrect Text Description");

        extentTest.log(Status.INFO, "Drag and Drop page opened", takePageScreenShotBase64(driver));

        //Check initial position of elements and store Text
        WebElement eleColmn1= driver.findElement(By.xpath("//div[@id='columns']/div[1]"));
        WebElement eleColmn2= driver.findElement(By.xpath("//div[@id='columns']/div[2]"));

        String textInColmn1_beforeDragDrop = driver.findElement(By.xpath("//div[@id='columns']/div[1]/header")).getText();
        String textInColmn2_beforeDragDrop = driver.findElement(By.xpath("//div[@id='columns']/div[2]/header")).getText();

        //Perform drag and drop
        action.dragAndDrop(eleColmn1,eleColmn2).perform();
        extentTest.log(Status.INFO, "Drag and Drop actioned",takePageScreenShotBase64(driver,"AfterDragDropAction"));

        Thread.sleep(sleepTime);

        //After drag drop, check texts as position should be changed post drag and drop
        String textInColmn1_afterDragDrop = driver.findElement(By.xpath("//div[@id='columns']/div[1]/header")).getText();
        String textInColmn2_afterDragDrop = driver.findElement(By.xpath("//div[@id='columns']/div[2]/header")).getText();

        softAssert.assertEquals(textInColmn1_afterDragDrop,textInColmn2_beforeDragDrop,"Drag Drop operation did not worked");
        softAssert.assertEquals(textInColmn2_afterDragDrop,textInColmn1_beforeDragDrop,"Drag Drop operation did not worked");

        extentTest.pass("DragAndDrop test completed");
        softAssert.assertAll();

    }//end DragAndDrop


    @Test
    public void DropDownListOperations() throws InterruptedException {

        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        SoftAssert softAssert = new SoftAssert();
        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Dropdown")).click();
        Thread.sleep(sleepTime);

        WebElement element = driver.findElement(By.cssSelector(".example > h3"));
        softAssert.assertEquals(element.getText(), "Dropdown List", "Incorrect Text Description");
        extentTest.log(Status.INFO,"Dropdown List page opened", takePageScreenShotBase64(driver));

        WebElement listBox = driver.findElement(By.id("dropdown"));
        Select drList =  new Select(listBox);
        drList.selectByValue("1");
        extentTest.log(Status.INFO, "Option 1 selected", takePageScreenShotBase64(driver,"Option1"));
        Thread.sleep(sleepTime);

        drList.selectByVisibleText("Option 2");
        extentTest.log(Status.INFO, "Option 2 selected", takePageScreenShotBase64(driver,"Option2"));
        Thread.sleep(sleepTime);

        extentTest.pass("Dropdown operations completed");

    }//end DropDownListOperations

    @Test(dataProvider = "ChallengingDOMData",dataProviderClass = HeroKuDataProvider.class)
    public void challengingDOM_Part1(String inputValue, String actionToPerform) throws InterruptedException {
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        extentTest.log(Status.INFO,MarkupHelper.createLabel("For this test, read input value and action('Edit' or 'Delete') to perform when match found",ExtentColor.GREY));
        boolean foundValue =  false;
        extentTest.log(Status.INFO, MarkupHelper.createLabel("Finding text: '"+inputValue + "' to perform '"+ actionToPerform + "' action on same ",ExtentColor.GREY));

        SoftAssert softAssert = new SoftAssert();
        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Challenging DOM")).click();
        Thread.sleep(sleepTime);

        extentTest.log(Status.INFO, "Challenging DOM Page opened ", takePageScreenShotBase64(driver));

        /*****************************************************************************************************
         * For this test, just take input from user to understand which value he would like to edit or delete
         * Find row basis input and click edit/delete as requested
         *****************************************************************************************************/
        WebElement table = driver.findElement(By.xpath("//div[@class='large-10 columns']/table"));//fetch all rows from table
        extentTest.log(Status.INFO, "Working on table", takeElementScreenShotBase64(table));

        List<WebElement> allRows = driver.findElements(By.xpath("//div[@class='large-10 columns']/table/tbody/tr"));//fetch all rows from table

        for (WebElement row: allRows ) {//iterate through each row
           //Iterate through each cell of selected row
            List<WebElement> allCells = row.findElements(By.xpath("td"));

            for (WebElement cell: allCells) {
                if(cell.getText().equals(inputValue)) {//when value matched, click on edit
                    extentTest.log(Status.INFO, "Match found in row", takeElementScreenShotBase64(row));//log row details with screen print
                    WebElement actionEle = row.findElement(By.linkText(actionToPerform));
                    actionEle.click(); //use  selected row to find edit/delete element on same
                    foundValue =  true;
                    extentTest.log(Status.PASS, "Click actioned on", takeElementScreenShotBase64(cell,"Clicked"));
                    extentTest.log(Status.PASS, "Action", takeElementScreenShotBase64(actionEle,"action"));
                }
            }

        }//end outer foreach allRows

        if(!foundValue){
            extentTest.log(Status.WARNING, MarkupHelper.createLabel("Text NOT found: '" +inputValue + "'",ExtentColor.RED));
        }

    }// end challengingDOM_Part1

    @Test
    public void challengingDOM_Part2() throws InterruptedException {
        extentTest = extReport.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());

        driver = objHKCommomFun.launchHeroKu(extentTest);

        driver.findElement(By.linkText("Challenging DOM")).click();
        Thread.sleep(sleepTime);

        extentTest.log(Status.INFO, "Challenging DOM Page opened ", takePageScreenShotBase64(driver));

        /*****************************************************************************************************
         * On this test, we identifying three buttons (dynamic text and ID on each run)
         * Clicking them will refresh the page
         * wait for page load till canvas is visible, scroll down and takeScreenShot of canvas
         *****************************************************************************************************/
        WebDriverWait wait = new WebDriverWait(driver, 20);//handle stale element 'canvas'.
        WebElement canvas = null;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement button1 = driver.findElement(By.xpath("//a[contains(@id,'2e41f8087142') and @class='button']"));
        extentTest.log(Status.INFO,"Clicking on 1st button",takeElementScreenShotBase64(button1));
        button1.click();//this will refresh page
        canvas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("canvas")));//wait till canvas is loaded else will get stale element exception
        extentTest.log(Status.INFO,"After 1st button click, UI refreshed",takePageScreenShotBase64(driver));
        js.executeScript("arguments[0].scrollIntoView(true);", canvas);//scroll down to take screenShot of canvas else image will not be fully captured
        extentTest.log(Status.INFO,"After 1st click, canvas output",takeElementScreenShotBase64(canvas));
        js.executeScript("window.scrollTo(0, 0);");//scroll up before next button click to capture full image


        WebElement button2 = driver.findElement(By.xpath("//a[contains(@id,'2e41f8087142') and @class='button alert']"));
        extentTest.log(Status.INFO,"Clicking on 2nd button",takeElementScreenShotBase64(button2));
        button2.click();
        canvas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("canvas")));
        extentTest.log(Status.INFO,"After 2nd button click, UI refreshed",takePageScreenShotBase64(driver));
        js.executeScript("arguments[0].scrollIntoView(true);", canvas);
        extentTest.log(Status.INFO,"After 2nd click, canvas output",takeElementScreenShotBase64(canvas));
        js.executeScript("window.scrollTo(0, 0);");


        WebElement button3 = driver.findElement(By.xpath("//a[contains(@id,'2e41f8087142') and @class='button success']"));
        extentTest.log(Status.INFO,"Clicking on 3rd button",takeElementScreenShotBase64(button3));
        button3.click();
        canvas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("canvas")));
        extentTest.log(Status.INFO,"After 3rd button click, UI refreshed",takePageScreenShotBase64(driver));
        js.executeScript("arguments[0].scrollIntoView(true);", canvas);
        extentTest.log(Status.INFO,"After 3rd click, canvas output",takeElementScreenShotBase64(canvas));
        js.executeScript("window.scrollTo(0, 0);");

        extentTest.pass("Challengin DOM part2-Dynamic element completed");

    }// end challengingDOM_Part2


        @AfterMethod
    public void closeCurrentBrowserWindow(){
        driver.close();
    }


    @AfterSuite
    public void generateExtentReport(){
        extReport.flush();
        try {
            Desktop.getDesktop().browse(new File(pathToExtentReportFile).toURI());
        }catch (Exception e){
            System.out.println("Report file not found"+e);
        }
    }

}//end class TestHerokuPage
