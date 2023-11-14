package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodayDealsPage extends BasePage {

    private By pageTitle = By.xpath("//*[@id='slot-2']/div/h1");
    private By headphonesCheckBox = By.xpath("//span[contains(@class, 'CheckboxFilter-module') and normalize-space(text())='Headphones & Earbuds']");
    private By groceryCheckBox = By.xpath("//span[contains(@class, 'CheckboxFilter-module') and normalize-space(text())='Grocery & Gourmet Food']");
    private By discountBtn = By.xpath("//a[@data-csa-c-element-id='filter-discount-10-']//span[normalize-space(text())='10% off or more']");
    private By thirdPageBtn = By.xpath("//*[@id='dealsGridLinkAnchor']/div/div[4]/div/ul/li[4]");
    private By selectedPage = By.xpath("//li[@class='a-selected']//a");
    private By dealsProductTitle = By.xpath("(//a[contains(@class, 'a-link-normal DealLink-module__dealLink')])[1]");

    public TodayDealsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getTextFromElement(pageTitle);
    }

    public void clickHeadphonesCheckBox() {
        clickElement(headphonesCheckBox);
    }

    public void clickGroceryCheckBox() {
        clickElement(groceryCheckBox);
    }

    public void clickDiscountBtn() {
        clickElement(discountBtn);
    }

    public void clickThirdPageBtn() {
        clickElement(thirdPageBtn);
    }

    public String getSelectedPageNo() {
        return getTextFromElement(selectedPage);
    }

    public void clickDealsProductTitle() {
        clickElement(dealsProductTitle);
    }
}
