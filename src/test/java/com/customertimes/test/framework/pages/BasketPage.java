package com.customertimes.test.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class BasketPage extends AbstractPage {
    private WebDriverWait wait;
    private By productPrice = By.xpath("//*[@class='mat-table cdk-table']/mat-row[1]/mat-cell[4]");
    private By productName = By.xpath("//*[@class='mat-table cdk-table']/mat-row[1]/mat-cell[2]");
    private By basket = By.cssSelector("[aria-label='Show the shopping cart']");
    private By successMessage = By.xpath("//*[contains(text(), 'Placed Apple Juice (1000ml) into basket.')]");
    private By findNecessaryProduct =  By.xpath("//*[contains(text(), ' Apple Juice (1000ml) ')]");
    private By clickOnAddToBasketButton = By.xpath("//mat-grid-tile[1]/figure/mat-card/div[2]/button");


    public BasketPage (WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }



    @Override
    public void openPage() {
    }

    public String getProductPrice() {
        return getWebDriver().findElement(productPrice).getAttribute("textContent");
    }

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        return getWebDriver().findElement(productName).getAttribute("textContent");
    }

    public void goToBasket() {
        getWebDriver().findElement(basket).click();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return getWebDriver().findElement(successMessage).getAttribute("innerText");
    }

    public void addProductToBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(findNecessaryProduct));
        getWebDriver().findElement(clickOnAddToBasketButton).click();
    }

}
