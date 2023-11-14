package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By searchTxtBox = By.id("twotabsearchtextbox");
    private By searchBtn = By.id("nav-search-submit-button");
    private By todayDealsBtn = By.xpath("//a[@href='/gp/goldbox?ref_=nav_cs_gb']");
    private By dontChangeBtn = By.xpath("//*[@id='nav-main']/div[1]/div/div/div[3]/span[1]/span/input");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void setSearchTxtBox(String searchTxt) {
        sendKeysToElement(searchTxtBox, searchTxt);
    }

    public void clickSearchBtn() {
        clickElement(searchBtn);
    }

    public void clickTodayDealsBtn() {
        clickElement(dontChangeBtn);
        clickElement(todayDealsBtn);
    }
}
