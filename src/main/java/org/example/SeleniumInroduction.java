package org.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumInroduction {

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\diwak\\BrowserDrivers\\chromedriver.exe"); //setting the global level property
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/"); // green kart website
        //driver.close();
    }
    //invoking the browser:
    //Chrome - ChromeDriver - Methods


}
