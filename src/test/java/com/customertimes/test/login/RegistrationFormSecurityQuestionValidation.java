package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationFormSecurityQuestionValidation extends BaseTest {
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
    public void registrationFormEmptySecurityQuestionValidation() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();
        getWebDriver().findElement(By.xpath("//a[@routerlink='/register']")).click();


        getWebDriver().findElement(By.cssSelector("[role=combobox]")).click();
        Thread.sleep(1000);
        getWebDriver().findElement(By.cssSelector("[role=combobox]")).sendKeys(Keys.ESCAPE);
        Thread.sleep(1000);

        String emptySecurityQuestion = getWebDriver().findElement(By.xpath("//*[contains(text(), ' Please select a security question. ')]")).getAttribute("textContent");
        Assert.assertEquals(emptySecurityQuestion, " Please select a security question. ", "Empty Security Question");
    }
}
