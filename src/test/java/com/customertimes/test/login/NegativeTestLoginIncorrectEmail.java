package com.customertimes.test.login;

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

public class NegativeTestLoginIncorrectEmail extends BaseTest {
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://3.134.94.241");
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        Thread.sleep(3000);
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    //@Test
    public void incorrectEmail() throws InterruptedException {
        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[name=email]")).clear();
        getWebDriver().findElement(By.cssSelector("[name=email]")).sendKeys("m.z1gmail.com");


        getWebDriver().findElement(By.xpath("//*[@name='password']")).clear();
        getWebDriver().findElement(By.xpath("//*[@name='password']")).sendKeys("1234567");

        getWebDriver().findElement(By.cssSelector("[type=submit]")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid email or password.')]")));
        String errorMessage = getWebDriver().findElement(By.xpath("//div[contains(text(), 'Invalid email or password.')]")).getText();
        Assert.assertEquals(errorMessage, "Invalid email or password.", "User is logged");

    }
}
