package tests;

import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retryAnalyzer.RetryAnalyzer;
import utils.TestConfig;

import java.util.concurrent.TimeUnit;

public class AddingTodayDealsWithCategories {
    private static WebDriver driver;
    private HomePage homePage;
    private TodayDealsPage todayDealsPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String expectedResult, actualResult, productTitle;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024,768));
        homePage = new HomePage(driver);
        todayDealsPage = new TodayDealsPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        homePage.openURL(TestConfig.BASE_URL);
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void testOpenTodayDeals(){
        homePage.clickTodayDealsBtn();
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testOpenTodayDeals")
    public void testSelectCategories(){
        actualResult = todayDealsPage.getPageTitle();
        expectedResult = "Today's Deals";
        todayDealsPage.clickHeadphonesCheckBox();
        todayDealsPage.clickGroceryCheckBox();
        todayDealsPage.clickDiscountBtn();

        Assert.assertEquals(actualResult, expectedResult, "Page title is wrong");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testSelectCategories")
    public void testSelectThirdPage(){
        todayDealsPage.clickThirdPageBtn();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(todayDealsPage.getSelectedPageNo(), "3", "The selected page is wrong");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testSelectThirdPage")
    public void testSelectProduct() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        todayDealsPage.clickDealsProductTitle();
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testSelectProduct")
    public void testAddItemToCart(){
        productTitle = productPage.getProductTitle();
        productPage.clickAddToCartBtn();
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testAddItemToCart")
    public void testCheckItemAddedToCart(){
        cartPage.clickCartBtn();
        String cartProductTitle = cartPage.getCartProductTitle();

        Assert.assertEquals(cartProductTitle, productTitle, "The selected item didn't added to the cart");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}