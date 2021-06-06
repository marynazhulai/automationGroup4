package com.customertimes.test.addToBasket;

import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.test.framework.driver.WebdriverRunner;
import com.customertimes.test.framework.pages.BasketPage;
import com.customertimes.test.framework.pages.LoginPage;
import com.customertimes.test.framework.pages.ProductInformationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class VerifiedAddedProductToBasket extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    Product product;
    BasketPage basketPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        getWebDriver().manage().deleteAllCookies();
        //getWebDriver().navigate().refresh();
        wait = new WebDriverWait(getWebDriver(),5);
        wait.until(ExpectedConditions.elementToBeClickable(getWebDriver().findElement(By.xpath("//*[contains(text(), 'Dismiss')]"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        customer = Customer.newBuilder().withName("m.z1@gmail.com").withPassword("1234567").build();
        product = Product.newBuilder().withTitle(" Apple Juice (1000ml) ").withDescription("The all-time classic.").withPrice(" 1.99¤").build();
        basketPage = new BasketPage(getWebDriver());
    }

    @AfterClass
    public void tearDown() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().refresh();
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void verifiedAddedProductToBasket () throws InterruptedException {

        LoginPage logIn = new LoginPage(getWebDriver());
        logIn.loginAs(customer);

        //add product to basket
        basketPage.addProductToBasket();

        //success message
        String successMessage = basketPage.getSuccessMessage();
        Assert.assertEquals(successMessage, "Placed Apple Juice (1000ml) into basket.", "No success message");

        //check that product is situated in the basket
        basketPage.goToBasket();


        String productName = basketPage.getProductName();
        Assert.assertEquals(productName, product.getTitle(), "Incorrect Product name");


        String productPrice = basketPage.getProductPrice();
        Assert.assertEquals(productPrice, product.getPrice(),"Incorrect Product price" );

    }
}
