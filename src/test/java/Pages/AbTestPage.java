package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbTestPage {

    WebDriver driver;

    public AbTestPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(css = ".example > h3")
    public WebElement headText;


    public String getHeadText(){
        return headText.getText();
    }

}//end class
