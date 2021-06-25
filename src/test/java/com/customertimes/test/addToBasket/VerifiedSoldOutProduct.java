package com.customertimes.test.addToBasket;

import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import com.customertimes.test.framework.pages.LoginPage;
import com.customertimes.test.framework.pages.ProductInformationPage;
import com.customertimes.test.framework.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class VerifiedSoldOutProduct extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    Product product;
    ProductPage productPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://3.134.94.241");
        getWebDriver().manage().deleteAllCookies();
        //getWebDriver().navigate().refresh();
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']"))));
        getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']")).click();
        customer = Customer.newBuilder().withName("m.z1@gmail.com").withPassword("1234567").build();
        product = product = Product.newBuilder().withErrorMessage("We are out of stock! Sorry for the inconvenience.").build();
        productPage = new ProductPage(getWebDriver());
    }

    @AfterClass
    public void tearDown() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void verifiedSoldOutProduct () throws InterruptedException {

        LoginPage logIn = new LoginPage(getWebDriver());
        logIn.loginAs(customer);

        //scroll down
        productPage.scrollDown();

        //click next
        productPage.clickNextPage();

        //add sold out product to basket
        productPage.clickOnSoldOutProduct();

        //error message
        String errorMessage = productPage.getErrorMessage();
        Assert.assertEquals(errorMessage, product.getErrorMessage(), "No error message");

    }
}

