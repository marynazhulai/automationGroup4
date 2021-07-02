package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.pages.LoginPageCucumber;
import com.customertimes.cucumber.pages.ProductSoldOutPage;
import com.customertimes.test.framework.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import static com.customertimes.test.framework.driver.WebdriverRunner.closeWebDriver;
import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class SoldOutProductStepdefs {
    private ProductSoldOutPage productSoldOutPage = new ProductSoldOutPage(getWebDriver());
    private LoginPageCucumber loginPageCucumber = new LoginPageCucumber(getWebDriver());
    public String message = "We are out of stock! Sorry for the inconvenience.";
    @Before
    public void setUp() {
        getWebDriver().get(TestConfig.CONFIG.baseUrl());
    }

    @Given("User goes to homepage" )
    public void userGoesToHomePage() {
      productSoldOutPage.openPage();
    }

    @When("User enters email {string} and password {string}")
    public void enterUserEmailAndPassword(String email, String password) {
        loginPageCucumber.enterEmail(email);
        loginPageCucumber.enterPassword(password);

    }
    @When("User clicks on login button" )
    public void userClicksOnLoginButton(){
        loginPageCucumber.clickOnLoginButton();
    }


    @When("User finds sold out product" )
    public void userFindsSoldOutProduct() throws InterruptedException {
        Thread.sleep(1000);
        productSoldOutPage.scrollDown();
        productSoldOutPage.clickNextPage();
    }

    @When("User tries to add to basket sold out product" )
    public void userTriesToAddToBasketSoldOutProduct() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollBy(0,-1000)");
        productSoldOutPage.clickOnSoldOutProduct();
     }

    @Then("Error message is displayed" )
    public void errorMessageIsDisplayed() {

        String errorMessage = productSoldOutPage.getErrorMessage();
        Assert.assertEquals(errorMessage, message, "No error message");
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

}
