package com.customertimes.test.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class ProductInformationPage extends AbstractPage {
    private WebDriverWait wait;
    private By price = By.xpath("//*[@class=\"mat-dialog-content\"]/div/div[1]/div[2]/div/p");
    private By generalInformation = By.xpath("//*[contains(text(), 'The all-time classic.')]");
    private By title = By.xpath("//*[@class=\"mat-dialog-content\"]/div/div[1]/div[2]/h1");
    private By productAppleJuice = By.xpath("//*[contains(text(), ' Apple Juice (1000ml) ')]");


    public ProductInformationPage (WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }



    @Override
    public void openPage(){
    }

    public String getStringActualPrice() {
        return getWebDriver().findElement(price).getText();
    }

    public String getActualGeneralInformation() {
        return getWebDriver().findElement(generalInformation).getText();
    }

    public String getActualTitle() {
        return getWebDriver().findElement(title).getText();
    }

    public void clickOnProduct() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productAppleJuice));
        getWebDriver().findElement(productAppleJuice).click();
    }




}
