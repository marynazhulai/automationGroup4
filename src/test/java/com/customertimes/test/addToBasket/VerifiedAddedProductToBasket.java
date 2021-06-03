package com.customertimes.test.addToBasket;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class VerifiedAddedProductToBasket extends BaseTest {
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.xpath("//*[contains(text(), 'Dismiss')]"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();

    }

    @AfterClass
    public void tearDown() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void verifiedAddedProductToBasket () throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[name=email]")).clear();
        getWebDriver().findElement(By.cssSelector("[name=email]")).sendKeys("m.z1@gmail.com");

        getWebDriver().findElement(By.xpath("//*[@name='password']")).clear();
        getWebDriver().findElement(By.xpath("//*[@name='password']")).sendKeys("1234567");

        getWebDriver().findElement(By.cssSelector("[type=submit]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), ' Apple Juice (1000ml) ')]")));


        //add to basket product
        getWebDriver().findElement(By.xpath("//mat-grid-tile[1]/figure/mat-card/div[2]/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Placed Apple Juice (1000ml) into basket.')]")));


        //success message
        String successMessage = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Placed Apple Juice (1000ml) into basket.')]")).getAttribute("innerText");
        Assert.assertEquals(successMessage, "Placed Apple Juice (1000ml) into basket.", "No success message");

        //check that product is situated in the basket
        getWebDriver().findElement(By.cssSelector("[aria-label='Show the shopping cart']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='mat-table cdk-table']/mat-row[1]/mat-cell[2]")));

        String productName = getWebDriver().findElement(By.xpath("//*[@class='mat-table cdk-table']/mat-row[1]/mat-cell[2]")).getAttribute("textContent");
        String productPrice = getWebDriver().findElement(By.xpath("//*[@class='mat-table cdk-table']/mat-row[1]/mat-cell[4]")).getAttribute("textContent");

        Assert.assertEquals(productName, " Apple Juice (1000ml) ", "Incorrect Product name");
        Assert.assertEquals(productPrice," 1.99¤","Incorrect Product price" );
    }
}
