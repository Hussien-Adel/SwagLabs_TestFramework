package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import static org.example.BrowserAction.driver;

public class Login
{
    WebDriver driver;
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginBtn;

    BrowserAction browser;

    UIElementsAction uiAction;

    public Login(WebDriver driver)
    {
        this.driver = driver;
        //this.driver = BrowserAction.driver;


        browser = new BrowserAction(driver);
        //browser = new BrowserAction();

        uiAction = new UIElementsAction(driver);
    }

    public void validLogin(String userName , String password/*, WebDriver driver*/)
    {
        //driver.get("https://www.saucedemo.com/");
        browser.navigateTo("https://www.saucedemo.com/");

        //usernameField = driver.findElement(By.id("user-name"));
        //passwordField = driver.findElement(By.id("password"));
        //loginBtn = driver.findElement(By.id("login-button"));

        //usernameField.sendKeys(userName);
        //passwordField.sendKeys(password);
        //loginBtn.click();

        uiAction.setText(UIElementsAction.LocatorType.id , "user-name" , userName);
        uiAction.setText(UIElementsAction.LocatorType.id , "password" , password);
        uiAction.click(UIElementsAction.LocatorType.id , "login-button");
    }

}
