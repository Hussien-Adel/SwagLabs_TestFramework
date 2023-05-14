package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BrowserAction
{
    WebDriver driver;
    //public static WebDriver driver;
    public BrowserAction(WebDriver driver)
    {
        this.driver = driver;
    }
    public void navigateTo(String URL)
    {
        driver.get(URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs( driver.getTitle() ));

        Assert.assertNotNull(driver.getTitle());
    }

    /*public static WebDriver driverSetUp(String browserName)
    {
        switch(browserName)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver" , ".\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }

        driver.manage().window().maximize();

        return driver;
    }*/
}
