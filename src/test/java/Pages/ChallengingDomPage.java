package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChallengingDomPage {

    WebDriver driver;

    public ChallengingDomPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(xpath = "//div[@class='large-10 columns']/table")
    public WebElement table;

    public By tableRows = By.xpath("//div[@class='large-10 columns']/table/tbody/tr");

    @FindBy(xpath = "//div[@class='large-2 columns']/a[@class='button']")
    public WebElement button1;

    @FindBy(xpath = "//div[@class='large-2 columns']/a[@class='button alert']")
    public WebElement button2;

    @FindBy(xpath = "//div[@class='large-2 columns']/a[@class='button success']")
    public WebElement button3;

    public void clickButton1(){
        button1.click();
    }

    public void clickButton2(){
        button2.click();
    }

    public void clickButton3(){
        button3.click();
    }


}//end class
