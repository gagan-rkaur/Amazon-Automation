package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private By addToCartBtn = By.id("add-to-cart-button");
    private By productTitle = By.id("productTitle");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCartBtn() {
        clickElement(addToCartBtn);
    }

    public String getProductTitle() {
        return getTextFromElement(productTitle).trim().substring(0,30);
    }
}
