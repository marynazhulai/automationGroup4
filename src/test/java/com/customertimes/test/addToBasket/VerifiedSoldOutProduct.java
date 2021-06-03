package com.customertimes.test.addToBasket;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class VerifiedSoldOutProduct extends BaseTest {
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']"))));
        getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']")).click();
    }

    @AfterClass
    public void tearDown() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void verifiedSoldOutProduct () throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[name=email]")).clear();
        getWebDriver().findElement(By.cssSelector("[name=email]")).sendKeys("m.z1@gmail.com");

        getWebDriver().findElement(By.xpath("//*[@name='password']")).clear();
        getWebDriver().findElement(By.xpath("//*[@name='password']")).sendKeys("1234567");

        getWebDriver().findElement(By.cssSelector("[type=submit]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Next page']")));

        //scroll down
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        getWebDriver().findElement(By.cssSelector("[aria-label='Next page']")).click();


        //add sold out product to basket
        getWebDriver().findElement(By.xpath("//*[@class='mat-grid-list']/div/mat-grid-tile[2]/figure/mat-card/div[3]/button")).click();


        //error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'We are out of stock! Sorry for the inconvenience.')]")));
        String errorMessage = getWebDriver().findElement(By.xpath("//*[contains(text(), 'We are out of stock! Sorry for the inconvenience.')]")).getAttribute("innerText");
        System.out.println(errorMessage);

        Assert.assertEquals(errorMessage, "We are out of stock! Sorry for the inconvenience.", "No error message");

    }
}

