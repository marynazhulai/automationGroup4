package com.customertimes.cucumber.pages;

import com.customertimes.test.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class ProductSoldOutPage extends AbstractPage {
    private WebDriverWait wait;
    private By nextPageButton = By.cssSelector("[aria-label='Next page']");
    private By soldOutProduct = By.xpath("//*[@class='mat-grid-list']/div/mat-grid-tile[3]/figure/mat-card/div[3]/button");
    /*private By soldOutProduct =  By.cssSelector("//*[contains(text(), 'OWASP Juice Shop \"King of the Hill\" Facemask')]");*/
    /*private By soldOutProduct =  By.cssSelector("//*[contains(text(), 'OWASP Juice Shop Coaster (10pcs)')]");*//*By.xpath("//*[@class='mat-grid-list']/div/mat-grid-tile[3]");*/
    private By warningMessageOutOfStock = By.xpath("//*[contains(text(), 'We are out of stock! Sorry for the inconvenience.')]");
    private By closeWelcomeBanner = By.cssSelector("button[aria-label='Close Welcome Banner']");
    private By closeCookieMessage = By.cssSelector("[aria-label='dismiss cookie message']");

    public ProductSoldOutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }



    @Override
    public void openPage(){
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeWelcomeBanner));
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(closeWelcomeBanner)));
        getWebDriver().findElement(closeWelcomeBanner).click();
    }
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessageOutOfStock));
        return getWebDriver().findElement(warningMessageOutOfStock).getAttribute("innerText");
    }

    public void clickOnSoldOutProduct() throws InterruptedException {
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");*/

        wait.until(ExpectedConditions.visibilityOfElementLocated(soldOutProduct));
        getWebDriver().findElement(soldOutProduct).click();
        Thread.sleep(1000);
    }

    public void clickNextPage() {
        getWebDriver().findElement(closeCookieMessage).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextPageButton));
        getWebDriver().findElement(nextPageButton).click();
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
