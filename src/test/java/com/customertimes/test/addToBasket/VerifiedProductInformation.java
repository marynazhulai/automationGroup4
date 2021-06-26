package com.customertimes.test.addToBasket;

import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import com.customertimes.test.framework.pages.LoginPage;
import com.customertimes.test.framework.pages.ProductInformationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class VerifiedProductInformation extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    Product product;
    ProductInformationPage productInformationPage;


    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://3.134.94.241");
        //getWebDriver().manage().deleteAllCookies();
        //getWebDriver().navigate().refresh();
        wait = new WebDriverWait(getWebDriver(),15);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[aria-label='Close Welcome Banner']")));
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.cssSelector("[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("[aria-label='Close Welcome Banner']")).click();
        customer = Customer.newBuilder().withName("m.z1@gmail.com").withPassword("1234567").build();
        product = Product.newBuilder().withTitle("Apple Juice (1000ml)").withDescription("The all-time classic.").withPrice("1.99¤").build();
        productInformationPage = new ProductInformationPage(getWebDriver());
    }

    @AfterClass
    public void tearDown() {
        getWebDriver().manage().deleteAllCookies();
       // getWebDriver().navigate().refresh();
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void verifiedProductInformation () throws InterruptedException {
        LoginPage logIn = new LoginPage(getWebDriver());
        logIn.loginAs(customer);

        //click on the first product
        productInformationPage.clickOnProduct();



        //validate Title
        String actualTitle = productInformationPage.getActualTitle();
        Assert.assertEquals(actualTitle, product.getTitle());

       //validate text
        String actualGeneralInformation = productInformationPage.getActualGeneralInformation();
        Assert.assertEquals(actualGeneralInformation, product.getDescription());

        //validate price
        String actualPrice = productInformationPage.getStringActualPrice();
        Assert.assertEquals(actualPrice, product.getPrice());
    }
}
