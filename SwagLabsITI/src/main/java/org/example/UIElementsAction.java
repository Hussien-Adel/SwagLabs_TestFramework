package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class UIElementsAction
{
    WebDriver driver;
    public UIElementsAction(WebDriver driver)
    {
        this.driver = driver;
        //this.driver = BrowserAction.driverSetUp("chrome");
    }
    public void click(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));

        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.isEnabled());

        element.click();
    }

    public void click(LocatorType locatorType, String locator)
    {
        WebElement element = getElement(locatorType,locator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));

        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.isEnabled());

        element.click();
    }

    public void setText(LocatorType locatorType, String locator, String textToWrite)
    {
        WebElement element = getElement(locatorType,locator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));

        element.clear();

        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.isEnabled());

        element.sendKeys(textToWrite);

    }

    public WebElement getElement(LocatorType locatorType, String locator )
    {
        WebElement element = null;
        switch (locatorType)
        {
            case id :
                element = driver.findElement(By.id(locator));
                break;
            case className:
                element = driver.findElement(By.className(locator));
                break;
            case xPath:
                element = driver.findElement(By.xpath(locator));
                break;
            case cssSelector:
                element = driver.findElement(By.cssSelector(locator));
                break;
        }
        return element;
    }

    public enum LocatorType {
        id,
        className,
        xPath,
        cssSelector;

    }


}
