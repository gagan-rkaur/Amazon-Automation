package tests;

import org.openqa.selenium.edge.EdgeDriver;
import retryAnalyzer.RetryAnalyzer;
import utils.TestConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import pages.*;


public class AddingProductToCart {
    private static WebDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String expectedResult, actualResult, productTitle;


    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024,768));
        homePage = new HomePage(driver);
        resultsPage = new ResultsPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        homePage.openURL(TestConfig.BASE_URL);
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void testSearchFunction(){
        homePage.setSearchTxtBox("car accessories");
        homePage.clickSearchBtn();
        expectedResult = "\"car accessories\"";
        actualResult = resultsPage.getSearchResultTitle();

        Assert.assertEquals(actualResult, expectedResult, "Result of search data is wrong");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testSearchFunction")
    public void testSelectFirstItem(){
        expectedResult = resultsPage.getProductLink();
        resultsPage.clickProductLink();
        productTitle = productPage.getProductTitle();

        Assert.assertEquals(productTitle, expectedResult, "The selected item is wrong");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testSelectFirstItem")
    public void testAddItemToCart(){
        productPage.clickAddToCartBtn();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
