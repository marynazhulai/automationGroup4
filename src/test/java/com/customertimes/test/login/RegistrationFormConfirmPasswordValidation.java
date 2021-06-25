package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationFormConfirmPasswordValidation extends BaseTest {
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

    //@Test
    public void registrationFormConfirmationPasswordValidation() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();
        getWebDriver().findElement(By.xpath("//a[@routerlink='/register']")).click();


        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).sendKeys("123456");

        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field to confirm the password\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field to confirm the password\"]")).sendKeys("123457");
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).click();


        String ConfirmationPasswordErrorMessage = getWebDriver().findElement(By.xpath("//*[contains(text(), ' Passwords do not match ')]")).getAttribute("textContent");
        Assert.assertEquals(ConfirmationPasswordErrorMessage, " Passwords do not match ", "Incorrect passwords");
    }

}
