package StepDefinitions;

import BaseAndCommons.BaseClass;
import BaseAndCommons.ScenarioContext;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static BaseAndCommons.BaseClass.getDriver;

public class Hooks extends BaseClass {
 // WebDriver driver;
    public static String screenshotAsBase64;

    @Before()
        public void beforeScenario(Scenario scenario){
        ScenarioContext.setScenario(scenario);
    }

    @After(order=2)
    public static void captureScreenshotAsBase64(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            //driver = getDriver();
            WebDriver driver = BaseClass.driver; // taking the latest driver
            TakesScreenshot ts = (TakesScreenshot) driver;
            screenshotAsBase64=   ts.getScreenshotAs(OutputType.BASE64);
        }


    }

    @After(order=1)
    public void takeScreenShotOnFailure(Scenario scenario) throws IOException {
               if (scenario.isFailed()) {
                   TakesScreenshot ts = (TakesScreenshot) driver;
                   File srcFile = ts.getScreenshotAs(OutputType.FILE);
                   //getting the currentdatetime and then formatting for the file name
                   LocalDateTime dateTime =  LocalDateTime.now();
                   System.out.println("CurrentDateTime" +dateTime);
                   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                   String formattedDateTime = dateTime.format(dateTimeFormatter);

                   File destFile = new File(System.getProperty("user.dir") +"\\screenshots\\"+scenario.getName()+"_"+formattedDateTime+".png");
                   try {
                       FileUtils.copyFile(srcFile, destFile);
                       System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
                   } catch (IOException e) {
                       System.err.println("Failed to save screenshot: " + e.getMessage());
                   }
                   //FileUtils.copyFile(srcFile,destFile);

        }


    }

    @After(order=3)
    public void tearDown(Scenario scenario) throws IOException {
       // driver = BaseClass.getDriver();

        //  BaseClass.quitDriver();

    }
}
