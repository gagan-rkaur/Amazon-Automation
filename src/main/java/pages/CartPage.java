package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By cartBtn = By.id("nav-cart");
    private By cartProductTitle = By.xpath(("(//span[contains(@class, 'a-truncate-cut')])[1]"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCartBtn() {
        clickElement(cartBtn);
    }

    public String getCartProductTitle() {
        return getTextFromElement(cartProductTitle).trim().substring(0,30);
    }
}
