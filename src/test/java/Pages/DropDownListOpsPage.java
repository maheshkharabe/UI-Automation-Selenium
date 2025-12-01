package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropDownListOpsPage {

    WebDriver driver;

    public DropDownListOpsPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(css = ".example > h3")
    WebElement headText;


    @FindBy(id ="dropdown")
    public WebElement listBox;

    public String getHeadText(){
        return headText.getText();
    }

    public void selectOption1(){
        Select drList =  new Select(listBox);
        drList.selectByValue("1");
    }

    public void selectOption2(){
        Select drList =  new Select(listBox);
        drList.selectByVisibleText("Option 2");
    }
}
