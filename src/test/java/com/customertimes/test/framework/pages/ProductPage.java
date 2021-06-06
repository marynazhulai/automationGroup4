package com.customertimes.test.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class ProductPage extends AbstractPage {
    private WebDriverWait wait;
    private By nextPageButton = By.cssSelector("[aria-label='Next page']");
    private By soldOutProduct = By.xpath("//*[@class='mat-grid-list']/div/mat-grid-tile[2]/figure/mat-card/div[3]/button");
    private By warningMessageOutOfStock = By.xpath("//*[contains(text(), 'We are out of stock! Sorry for the inconvenience.')]");


    public ProductPage (WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }



    @Override
    public void openPage(){
    }
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessageOutOfStock));
        return getWebDriver().findElement(warningMessageOutOfStock).getAttribute("innerText");
    }

    public void clickOnSoldOutProduct() {
        getWebDriver().findElement(soldOutProduct).click();
    }

    public void clickNextPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(nextPageButton));
        getWebDriver().findElement(nextPageButton).click();
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
