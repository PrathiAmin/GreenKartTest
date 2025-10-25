package pageResources;

import BaseAndCommons.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GreenKartHome extends BaseClass {
    WebDriver driver;
    public GreenKartHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this); //intialize elements
    }


    @FindBy(xpath="//h4[contains(text(),'Brocolli - 1 Kg')]/parent::div/div/button")
    WebElement broccoliAddtocartButton;
    // Define actions
    public void broccoliAddtocartButtonClick() {
        broccoliAddtocartButton.click();
    }

    @FindBy(xpath="//div[@class='product']")
    List<WebElement> products;

    public List<WebElement> noOfProducts(){
        return products;
    }

    @FindBy(xpath="//div[@class='brand greenLogo']")
    WebElement titleName;

    public String getTitle(){
        return titleName.getText();
    }

    @FindBy(className="search-keyword")
    WebElement searchBox;

    public void enterTextInSearchBox(String text){
        searchBox.sendKeys(text);
    }





}
