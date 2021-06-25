package com.customertimes.test.framework.pages;

import com.customertimes.model.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Currency;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;

public class LoginPage extends AbstractPage {
    private WebDriverWait wait;
    private By navBarAccount = By.id("navbarAccount");
    private By loginButton = By.cssSelector("[type=submit]");
    private By passwordField = By.xpath("//*[@name='password']");
    private By emailField = By.cssSelector("[name=email]");
    private By loginSubmitButton = By.id("navbarLoginButton");
    private By goToUserProfileButton = By.cssSelector("button[aria-label='Go to user profile'] span");

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }


    @Override
    public void openPage(){
    }

    @Step
    public String getActualNameText(String currentEmail) {
        wait.until(ExpectedConditions.textToBe(goToUserProfileButton, currentEmail));
        String actualNameText = getWebDriver().findElement(goToUserProfileButton).getText();
        return actualNameText;
    }

    @Step ("Click on Account button")
    public void clickOnAccountButton() {
        getWebDriver().findElement(navBarAccount).click();
    }

    @Step ("Click on Login button")
    public void clickOnLoginButton() {
        getWebDriver().findElement(loginButton).click();
    }

    @Step("Enter Password")
    public void enterPassword(String password) {
        WebElement passwordElement = getWebDriver().findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    @Step
    public void enterEmail(String email) {
        WebElement emailElement = getWebDriver().findElement(emailField);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    @Step("Enter Email")
    public void navigateToLoginPage() {
        clickOnAccountButton();
        getWebDriver().findElement(loginSubmitButton).click();
    }

    @Step
    public void loginAs (Customer customer){
        navigateToLoginPage();
        enterEmail(customer.getEmail());
        enterPassword(customer.getPassword());
        clickOnLoginButton();

    }

}

