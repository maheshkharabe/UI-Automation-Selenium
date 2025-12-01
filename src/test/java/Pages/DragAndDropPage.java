package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {

    WebDriver driver;

    public DragAndDropPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(css = ".example > h3")
    WebElement headText;

    @FindBy(xpath = "//div[@id='columns']/div[1]/header")
    public WebElement box1;

    @FindBy(xpath = "//div[@id='columns']/div[2]/header")
    public WebElement box2;

    public String getHeadText(){
        return headText.getText();
    }

    public String getBox1Text(){
        return box1.getText();
    }

    public String getBox2Text(){
        return box2.getText();
    }


}
