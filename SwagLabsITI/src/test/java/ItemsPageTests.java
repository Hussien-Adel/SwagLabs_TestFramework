import org.example.BrowserAction;
import org.example.Login;
import org.example.UIElementsAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

//import static org.example.BrowserAction.driver;

public class ItemsPageTests
{
    WebDriver driver;
    Login login;
    List<WebElement> addToCartButtonsList;

    WebElement sortDropDown;

    WebElement cartBtn;

    List<WebElement> priceTags;

    List<WebElement> btnsAfterClick;

    UIElementsAction uiAction;




    @BeforeTest
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver" , ".\\src\\main\\resources\\chromedriver.exe");

        driver = new ChromeDriver();
        //driver = BrowserAction.driver;

        uiAction = new UIElementsAction(driver);

        login = new Login(driver);
        login.validLogin("standard_user","secret_sauce");

        addToCartButtonsList = driver.findElements(By.xpath("//div[@class=\"inventory_item_description\"]//child::button"));
        // Get elements method in UI Elements Action class --> return list

        //sortDropDown = driver.findElement(By.className("product_sort_container"));
        sortDropDown = uiAction.getElement(UIElementsAction.LocatorType.className ,"product_sort_container");

    }

    @Test
    public void addAllItems()
    {
        for(int i = 0 ; i < addToCartButtonsList.size() ; i++)
        {
            //System.out.println(addToCartButtonsList.get(i).getText());
            //addToCartButtonsList.get(i).click();
            uiAction.click(addToCartButtonsList.get(i));

            btnsAfterClick = driver.findElements(By.xpath("//div[@class=\"inventory_item_description\"]//child::button"));
            //System.out.println(btnsAfterClick.get(i).getText());

            Assert.assertEquals(btnsAfterClick.get(i).getText() , "Remove");
            //OR //Assert.assertNotEquals(btnsAfterClick.get(i).getText() , "Add to Cart");

            //cartBtn = driver.findElement(By.cssSelector("span[class=\"shopping_cart_badge\"]"));
            cartBtn = uiAction.getElement(UIElementsAction.LocatorType.cssSelector , "span[class=\"shopping_cart_badge\"]");
            //System.out.println(cartBtn.getText());

            Assert.assertEquals(cartBtn.getText() , String.valueOf(i+1) );

        }

        try {Thread.sleep(3000);} catch (InterruptedException e) {throw new RuntimeException(e);}
    }

    @Test
    public void addItemBasedOnName()
    {
        String itemName = "Sauce Labs Backpack";
        String locator = String.format("//div[@class=\"inventory_item_name\" and text() =\"%s\"]//ancestor::div[@class=\"inventory_item_description\"]//child::button" , itemName);

        //WebElement itemToAdd = driver.findElement(By.xpath(locator));

        //itemToAdd.click();
        //uiAction.click(itemToAdd);
        uiAction.click(UIElementsAction.LocatorType.xPath , locator);

        try {Thread.sleep(3000);} catch (InterruptedException e) {throw new RuntimeException(e);}

    }

    @Test
    public void sort()
    {

        Select select  = new Select(sortDropDown);
        select.selectByValue("lohi");

        priceTags = driver.findElements(By.className("inventory_item_price"));

        try {Thread.sleep(4000);} catch (InterruptedException e) {throw new RuntimeException(e);}


        double [] priceNum = new double[priceTags.size()];

        for(int i = 0 ; i < priceTags.size() ; i++)
        {
            priceNum[i] = Double.parseDouble(priceTags.get(i).getText().substring(1));
           // System.out.println(priceNum);
        }

        Assert.assertTrue(isSorted(priceNum));
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }


    boolean isSorted(double[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

}
