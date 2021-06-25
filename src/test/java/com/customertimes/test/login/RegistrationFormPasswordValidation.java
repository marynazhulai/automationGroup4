package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationFormPasswordValidation extends BaseTest {
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

   // @Test
    public void registrationFormPasswordValidation() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();
        getWebDriver().findElement(By.xpath("//a[@routerlink='/register']")).click();


        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).sendKeys("1234");

        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).click();

        String passwordErrorMessage = getWebDriver().findElement(By.cssSelector("[role=\"alert\"]")).getAttribute("textContent");
        Assert.assertEquals(passwordErrorMessage, "Password must be 5-20 characters long. ", "Incorrect password");
    }

}
