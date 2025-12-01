package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenImagesPage {

    WebDriver driver;

    public BrokenImagesPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    public By image = By.tagName("img");

  }
