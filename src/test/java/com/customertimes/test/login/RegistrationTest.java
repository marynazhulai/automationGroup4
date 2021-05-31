package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.*;

public class RegistrationTest extends BaseTest {
    static String userEmail = "mz" + System.currentTimeMillis() + "@ctdev.io";

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
    public void userCanSignUpToJuiceShop () throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();
        getWebDriver().findElement(By.xpath("//a[@routerlink='/register']")).click();


        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Email address field\"]")).sendKeys(userEmail);

        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field for the password\"]")).sendKeys("123456");

        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field to confirm the password\"]")).clear();
        getWebDriver().findElement(By.cssSelector("[aria-label=\"Field to confirm the password\"]")).sendKeys("123456");


        getWebDriver().findElement(By.cssSelector("[role=combobox]")).click();
        Thread.sleep(1_000);
        getWebDriver().findElement(By.xpath("//*[contains(text(), ' Your ZIP/postal code when you were a teenager? ')]")).click();


        getWebDriver().findElement(By.xpath("//*[@aria-label='Field for the answer to the security question']")).clear();
        getWebDriver().findElement(By.xpath("//*[@aria-label='Field for the answer to the security question']")).sendKeys("03179");

        Thread.sleep(3_000);
        getWebDriver().findElement(By.cssSelector("#registerButton > span.mat-button-wrapper")).click();
        Thread.sleep(2_000);


        String successMessage = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Registration completed successfully. You can now log in.')]")).getAttribute("innerText");
        System.out.println(successMessage);


        Assert.assertEquals(successMessage, "Registration completed successfully. You can now log in.", "User is not registered");

    }
}
