package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationFormEmailValidation extends BaseTest {
    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://3.134.94.241");
        Thread.sleep(1000);
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

  //  @Test
    public void registrationFormEmailValidation() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();
        getWebDriver().findElement(By.xpath("//a[@routerlink='/register']")).click();


        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).sendKeys("mzgmail.com");

        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).click();


        String emailErrorMessage = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Email address is not valid.')]")).getAttribute("textContent");
        Assert.assertEquals(emailErrorMessage, "Email address is not valid.", "Incorrect email");
    }
}
