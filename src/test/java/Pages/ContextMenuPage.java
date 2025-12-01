package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContextMenuPage {

    WebDriver driver;

    public ContextMenuPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(id = "hot-spot")
    public WebElement contextArea;

}
