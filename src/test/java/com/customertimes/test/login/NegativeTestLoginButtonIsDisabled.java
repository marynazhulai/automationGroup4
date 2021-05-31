package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class NegativeTestLoginButtonIsDisabled extends BaseTest {

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        Thread.sleep(1000);
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void loginButtonIsDisabled () throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        Thread.sleep(1_000);

        getWebDriver().findElement(By.cssSelector("[name=email]")).clear();
        getWebDriver().findElement(By.xpath("//*[@name='password']")).clear();


        WebElement e = getWebDriver().findElement(By.cssSelector("[type=submit]"));
        boolean actualValue = e.isEnabled();

        Assert.assertEquals(e.isEnabled(), false);

    }
}
