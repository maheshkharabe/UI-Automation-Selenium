package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddRemoveElementPage {

    WebDriver driver;

    public AddRemoveElementPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    public By deleteButton = By.cssSelector("button.added-manually");

    @FindBy(css = "#content > h3")
    WebElement headText;

    @FindBy(css = ".example > button")
    WebElement buttonAddElement;

    @FindBy(css = "")
    public WebElement buttonDelete;

    public String getHeadText(){
        return headText.getText();
    }

    public String getButtonAddElementText(){
        return buttonAddElement.getText();
    }

    public void clickButtonAddElement(){
         buttonAddElement.click();
    }

    public void clickButtonDelete(){
       driver.findElement(deleteButton).click();
    }

    public String getButtonDeleteText(){
        return driver.findElement(deleteButton).getText();
    }

}//end class
