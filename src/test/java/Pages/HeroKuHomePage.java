package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeroKuHomePage {

    WebDriver driver;

    public HeroKuHomePage(WebDriver d){
        driver = d;
        PageFactory.initElements(driver,this);//Create web elements for current page
    }

    @FindBy(linkText = "A/B Testing")
    WebElement abTest;

    @FindBy(linkText = "Add/Remove Elements")
    WebElement addRemove;

    @FindBy(linkText = "Basic Auth")
    WebElement basicAuth;

    @FindBy(linkText = "Broken Images")
    WebElement brokenImages;

    @FindBy(linkText = "Checkboxes")
    WebElement checkBoxes;

    @FindBy(linkText = "Context Menu")
    WebElement contextMenu;

    @FindBy(linkText = "Disappearing Elements")
    WebElement disappearingElements;

    @FindBy(linkText = "Drag and Drop")
    WebElement dragDrop;

    @FindBy(linkText = "Dropdown")
    WebElement dropdown;

    @FindBy(linkText = "Challenging DOM")
    WebElement challengingDom;

    @FindBy(linkText = "Dynamic Content")
    WebElement dynamicContent;

    public void clickOnAbTestingLink(){
        abTest.click();
    }

    public void clickOnAddRemoveLink(){
        addRemove.click();
    }
    public void clickOnBasicAuthLink(){
        basicAuth.click();
    }
    public void clickOnBrokenImagesLink(){
        brokenImages.click();
    }
    public void clickOnCheckBoxesLink(){
        checkBoxes.click();
    }
    public void clickOnContextMenuLink(){
        contextMenu.click();
    }
    public void clickOnDisappearingElementsLink(){
        disappearingElements.click();
    }
    public void clickOnDragDropLink(){
        dragDrop.click();
    }
    public void clickOnDropdownLink(){
        dropdown.click();
    }
    public void clickOnChallengingDomLink(){
        challengingDom.click();
    }
    public void clickOnDynamicContentLink(){
        dynamicContent.click();
    }

}//end class HeroKuHomePage
