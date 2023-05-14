package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Items
{
    WebDriver driver;
    List<WebElement> addToCartButtonsList;

    public Items(WebDriver driver)
    {
        this.driver = driver;
    }

    public void addAllItems()
    {
        addToCartButtonsList = driver.findElements(By.xpath("//div[@class=\"inventory_item_description\"]//child::button"));

        for(int i = 0 ; i < addToCartButtonsList.size() ; i++)
        {
            addToCartButtonsList.get(i).click();
        }
    }

    public void addSpecificItem(String itemName)
    {
        String locator = String.format("//div[@class=\"inventory_item_name\" and text() =\"%s\"]//ancestor::div[@class=\"inventory_item_description\"]//child::button" , itemName);

        WebElement itemToAdd = driver.findElement(By.xpath(locator));

        itemToAdd.click();
    }
}
