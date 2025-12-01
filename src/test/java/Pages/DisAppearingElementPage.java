package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DisAppearingElementPage {

    WebDriver driver;

    public DisAppearingElementPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(css = ".example > h3")
    WebElement headText;

    @FindBy(linkText = "Gallery")
    public WebElement gallery;

    public String getHeadText(){
        return headText.getText();
    }

}
