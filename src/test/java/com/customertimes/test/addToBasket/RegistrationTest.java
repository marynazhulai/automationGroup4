package com.customertimes.test.addToBasket;

import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import com.customertimes.test.framework.pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationTest extends BaseTest {
    static String userEmail = "mz" + System.currentTimeMillis() + "@ctdev.io";
    WebDriverWait wait;
    Customer customer;
    RegistrationPage registrationPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait = new WebDriverWait(getWebDriver(),10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[aria-label='Close Welcome Banner']")));
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("[aria-label='Close Welcome Banner']")).click();
        customer = Customer.newBuilder().withEmailForRegistration().withPassword("1234567").withAnswer("Jyls").build();
        registrationPage = new RegistrationPage(getWebDriver());
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void userCanSignUpToJuiceShop () throws InterruptedException {

        registrationPage.registerAs(customer);

        String messageAboutSuccessRegistration = registrationPage.getMessageAboutSuccessRegistration();
        Assert.assertEquals(messageAboutSuccessRegistration, "Registration completed successfully. You can now log in.", "User is not registered");

    }
}
