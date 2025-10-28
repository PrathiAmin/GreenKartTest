package StepDefinitions;

//import BaseAndCommons.BaseClass;
import BaseAndCommons .BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import  BaseAndCommons.BaseClass.*;
import org.testng.*;
import pageResources.GreenKartHome;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;


public class GreenKartStepDef  {

    WebDriver driver = BaseClass.getDriver();
    GreenKartHome greenKartHome = new GreenKartHome(driver);
    BaseClass baseClass = new BaseClass();

    public GreenKartStepDef() throws IOException {
    }

    //not sure the constructor to initialize  is useful
  /*  public GreenKartStepDef(WebDriver driver){
        this.driver = driver;
         //intialize elements
    } */

    @Given("User launches the {string} application")
    public void user_launches_the_application(String application) {
        try {
            Properties props=new Properties();
            // FileReader reader=new FileReader("src/test/resources/Config/application.properties");
            InputStream input = BaseClass.class.getClassLoader().getResourceAsStream("Config/application.properties");
            //FileInputStream class also can be used to get the input stream
            if (input == null) {
                throw new FileNotFoundException("Property file 'Config/application.properties' not found in the classpath");
            }
            props.load(input);
            String URL= props.getProperty(application);
            // driver = setDriver(driver);
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
          //  driver.close();

        }
        catch (Exception e){
            System.out.println("Page didn't load an exception is thrown"+ e);
        }
    }
    @When("The user is on the home page")
    public void the_user_is_on_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        baseClass.waitForPageToLoad(driver,20);
        baseClass.waitForVisibility(driver , By.xpath("//div[contains(text(),'GREEN')]"));

    }
    @Then("User verifies the title")
    public void user_verifies_the_title() {

       // throw new io.cucumber.java.PendingException();
        if(greenKartHome.getTitle().equalsIgnoreCase("GREENKART"))
        {
            System.out.println("Title is verified and the title reads" + greenKartHome.getTitle() );
        }
    }

    @Then("User enters the {string} of the vegetable")
    public void user_enters_the_of_the_vegetable(String veggieName) {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
      greenKartHome.enterTextInSearchBox(veggieName);
    }
    @Then("Checks if the vegetable {string} with picture is displayed")
    public void checks_if_the_vegetable_with_picture_is_displayed(String veggieName) {
        try {
            String noProductMessage = null;
            if(!veggieName.equalsIgnoreCase("Fish")){
                WebElement veggieElement =  driver.findElement(By.xpath("//h4[contains(text(),veggieName)]"));
                baseClass.waitForVisibilityOfElement(driver,veggieElement);
            }
            else {
                noProductMessage = driver.findElement(By.xpath(
                        "//h2[contains(text(),'no products')]")).getText();
                Assert.assertEquals(noProductMessage, "Sorry, no products matched your search!");
            }
        }
        catch (Exception e){
            System.out.println("Exception is thrown while searching the element"+ e);
        }
        // Write code here that turns the phrase above into concrete actions



    }


    @And("User closes the browser")
    public void userClosesTheBrowser() {
        driver.quit();
    }

    @And("User verifies the number of Vegetables")
    public void userVerifiesTheNumberOfVegetables() {

        Assert.assertEquals(greenKartHome.noOfProducts().size(),30);
    }
}
