package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckBoxOpsPage {

    WebDriver driver;

    public CheckBoxOpsPage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(css = ".example > h3")
    WebElement headText;

    @FindBy(xpath = "//form[@id=\"checkboxes\"]/input[@type=\"checkbox\"][1]")
    public WebElement checkBox1;

    @FindBy(xpath = "//form[@id=\"checkboxes\"]/input[@type=\"checkbox\"][2]")
    public WebElement checkBox2;


    public String getHeadText(){
        return headText.getText();
    }

    public void clickCheckBox1(){
        checkBox1.click();
    }

    public void clickCheckBox2(){
        checkBox2.click();
    }


}//end class
