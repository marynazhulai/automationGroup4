package com.customertimes.test.addToBasket;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class VerifiedProductInformation extends BaseTest {
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();

    }

    @AfterClass
    public void tearDown() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void verifiedProductInformation () throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[name=email]")).clear();
        getWebDriver().findElement(By.cssSelector("[name=email]")).sendKeys("m.z1@gmail.com");

        getWebDriver().findElement(By.xpath("//*[@name='password']")).clear();
        getWebDriver().findElement(By.xpath("//*[@name='password']")).sendKeys("1234567");

        getWebDriver().findElement(By.cssSelector("[type=submit]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), ' Apple Juice (1000ml) ')]")));


        //click on the first product
       getWebDriver().findElement(By.xpath("//*[contains(text(), ' Apple Juice (1000ml) ')]")).click();

       //validate Title
        String actualTitle = getWebDriver().findElement(By.xpath("//*[@class=\"mat-dialog-content\"]/div/div[1]/div[2]/h1")).getText();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, "Apple Juice (1000ml)");

       //validate text
        String actualGeneralInformation = getWebDriver().findElement(By.xpath("//*[contains(text(), 'The all-time classic.')]")).getText();
        System.out.println(actualGeneralInformation);
        Assert.assertEquals(actualGeneralInformation, "The all-time classic.");

        //validate price
        String actualPrice = getWebDriver().findElement(By.xpath("//*[@class=\"mat-dialog-content\"]/div/div[1]/div[2]/div/p")).getText();
        System.out.println(actualPrice);
        Assert.assertEquals(actualPrice, "1.99¤");




    }
}
