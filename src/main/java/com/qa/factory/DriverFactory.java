package com.qa.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {
    public WebDriver driver;
    public WebDriverWait wait;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the threadlocal driver on the basis of given browser
     * @param browser
     * @return
     */

    public WebDriver init_driver(String browser){
        System.out.println("browser value is:" + browser);
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        }
        else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver());
        }
        else if (browser.equals("safari")){
            tlDriver.set(new SafariDriver());
        }
        else {
            System.out.println("Please pass the correct browser value :" + browser);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();

    }

    /**
     *
     * @return
     */
    public static synchronized WebDriver getDriver(){
      return   tlDriver.get();
    }



}
