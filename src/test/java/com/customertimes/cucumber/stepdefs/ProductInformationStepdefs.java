package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.pages.ProductInformationPageCucumber;
import com.customertimes.test.framework.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.customertimes.test.framework.driver.WebdriverRunner.closeWebDriver;
import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;



public class ProductInformationStepdefs {
    private ProductInformationPageCucumber productInformationPageCucumber = new ProductInformationPageCucumber(getWebDriver());
    public String expectedTitle = "Apple Juice (1000ml)";

    @Before
    public void setUp() {
        getWebDriver().get(TestConfig.CONFIG.baseUrl());
    }

    @Given("User goes to home page" )
    public void userGoesToHomePage() {
        productInformationPageCucumber.openPage();
        System.out.println("1st");
    }

    @When("User clicks on a product" )
    public void userClicksOnAProduct() {
        productInformationPageCucumber.clickOnProduct();
        System.out.println("2st");
    }

    @Then("Product information is displayed" )
    public void productInformationIsDisplayed() {
        String actualTitle = productInformationPageCucumber.getActualTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("3st");
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

}
