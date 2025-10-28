package BaseAndCommons;

import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

      public static WebDriver driver;

      public static WebDriver getDriver() throws IOException {
          Properties props = new Properties();
          FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\application.properties");
          props.load(fis);
          String browser = props.getProperty("browser");
          if(browser.equalsIgnoreCase("Chrome")){
              System.setProperty("webdriver.chrome.driver", "C:\\Users\\diwak\\BrowserDrivers\\chromedriver.exe");
              driver = new ChromeDriver();

          }
          return driver;

      }

      public static void quitDriver(){
          if(driver!=null){
              driver.quit();
              driver=null;
          }
      }

/*
     public void launchGreenKart(String application, WebDriver driver) throws FileNotFoundException, IOException {
         Properties props=new Properties();
        // FileReader reader=new FileReader("src/test/resources/Config/application.properties");
         InputStream input = BaseClass.class.getClassLoader().getResourceAsStream("Config/application.properties");
         //FileInputStream class also can be used to get the input stream
         if (input == null) {
             throw new FileNotFoundException("Property file 'Config/application.properties' not found in the classpath");
         }
         props.load(input);
         String URL= props.getProperty("application");
        // driver = setDriver(driver);
         driver.get(URL);


     }

     */


     public void waitForVisibility(WebDriver driver, By by){
         WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(by));
     }

    public void waitForVisibilityOfElement(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        ExpectedCondition<Boolean> pageLoadCondition = webDriver -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            String readyState = (String) jsExecutor.executeScript("return document.readyState");
            return readyState.equals("complete");
        };
        wait.until(pageLoadCondition);
    }

        }
