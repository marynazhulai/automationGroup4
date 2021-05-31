package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationFormAnswerValidation extends BaseTest {
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
    public void registrationFormAnswerValidation() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();
        getWebDriver().findElement(By.xpath("//a[@routerlink='/register']")).click();


        getWebDriver().findElement(By.xpath("//*[@aria-label='Field for the answer to the security question']")).clear();
        getWebDriver().findElement(By.xpath("//*[@aria-label='Field for the answer to the security question']")).click();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).click();

        String answerErrorMessage = getWebDriver().findElement(By.xpath("//*[contains(text(), ' Please provide an answer to your security question. ')]")).getAttribute("textContent");
        Assert.assertEquals(answerErrorMessage, " Please provide an answer to your security question. ", "Empty Answer");
    }
}
