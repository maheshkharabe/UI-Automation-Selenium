package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasicAuthPage {

    WebDriver driver;

    public BasicAuthPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(css = ".example > p")
    WebElement headText;


    public String getHeadText(){
        return headText.getText();
    }
}
