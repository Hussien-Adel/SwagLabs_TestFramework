import org.example.BrowserAction;
import org.example.Items;
import org.example.Login;
import org.example.UIElementsAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CartPageTests
{
    WebDriver driver;
    Login login;

    WebElement cartBtn;

    List<WebElement> qtyLabelsInCartPage;

    List <WebElement> itemsNamesInCartPage;

    BrowserAction browser;

    @BeforeTest
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver" , ".\\src\\main\\resources\\chromedriver.exe");

        driver = new ChromeDriver();

        login = new Login(driver);
        login.validLogin("standard_user","secret_sauce");

        browser = new BrowserAction(driver);


    }


    @Test
    public void checkItemsInCart()
    {
        Items items = new Items(driver);
        items.addAllItems();

        //driver.get("https://www.saucedemo.com/cart.html");
        browser.navigateTo("https://www.saucedemo.com/cart.html");

        cartBtn = driver.findElement(By.cssSelector("span[class=\"shopping_cart_badge\"]"));

        qtyLabelsInCartPage = driver.findElements(By.cssSelector("div[class=\"cart_quantity\"]"));

        Assert.assertEquals(qtyLabelsInCartPage.size() , Integer.parseInt(cartBtn.getText()));

        itemsNamesInCartPage = driver.findElements(By.cssSelector("div[class=\"inventory_item_name\"]"));

        for(int i = 0 ; i < Integer.parseInt(cartBtn.getText()) ; i++)
        {
            Assert.assertEquals(Integer.parseInt(qtyLabelsInCartPage.get(i).getText()) , 1);
        }
    }

    @Test
    public void selectedItemAddedToCart()
    {
        String itemName = "Sauce Labs Backpack";
        Items items = new Items(driver);
        items.addSpecificItem(itemName);

        //driver.get("https://www.saucedemo.com/cart.html");
        browser.navigateTo("https://www.saucedemo.com/cart.html");

        itemsNamesInCartPage = driver.findElements(By.cssSelector("div[class=\"inventory_item_name\"]"));

        Assert.assertEquals(itemsNamesInCartPage.size() , 1);

        Assert.assertEquals(itemsNamesInCartPage.get(0).getText() , itemName);

    }


    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
