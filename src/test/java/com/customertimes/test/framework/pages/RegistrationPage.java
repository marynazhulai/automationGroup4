package com.customertimes.test.framework.pages;

import com.customertimes.model.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TransferQueue;

import static com.customertimes.test.framework.driver.WebdriverRunner.getWebDriver;


public class RegistrationPage extends AbstractPage {
    private WebDriverWait wait;
    private By navBarAccount = By.id("navbarAccount");
    private By loginButton = By.id("navbarLoginButton");
    private By notYetaCustomerLink = By.xpath("//a[@routerlink='/register']");
    private By emailField = (By.cssSelector("[aria-label=\"Email address field\"]"));
    private By passwordField = By.cssSelector("[aria-label=\"Field for the password\"]");
    private By confirmationPasswordField = By.cssSelector("[aria-label=\"Field to confirm the password\"]");
    private By securityQuestionField = By.cssSelector("[aria-label=\"Selection list for the security question\"]");
    private By middleNameQuestionOption = By.xpath("//*[contains(text(), ' Your eldest siblings middle name? ')]");
    private By answerField = By.xpath("//*[@aria-label='Field for the answer to the security question']");
    private By popupMessageAboutLanguage = By.xpath("//*[contains(text(), 'Language has been changed to English')]");
    private By registerButton = By.cssSelector("button#registerButton");
    private By messageAboutRegistration = By.xpath("//*[contains(text(), 'Registration completed successfully. You can now log in.')]");



    public RegistrationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
    }

    public String getMessageAboutSuccessRegistration() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageAboutRegistration));
        String messageAboutSuccessRegistration = getWebDriver().findElement(messageAboutRegistration).getText();
        return messageAboutSuccessRegistration;
    }

    public void clickOnRegisterButton() {
        //WebElement messageAboutLanguage = getWebDriver().findElement(popupMessageAboutLanguage);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupMessageAboutLanguage));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));

        getWebDriver().findElement(registerButton).click();
    }

    public void answerToSecurityQuestion(String answer) {
        getWebDriver().findElement(answerField).clear();
        getWebDriver().findElement(answerField).sendKeys(answer);
    }

    public void selectSecurityQuestion() {
        getWebDriver().findElement(securityQuestionField).click();
        getWebDriver().findElement(middleNameQuestionOption).click();
    }

    public void enterConfirmationPassword(String password) {
        getWebDriver().findElement(confirmationPasswordField).clear();
        getWebDriver().findElement(confirmationPasswordField).sendKeys(password);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = getWebDriver().findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void enterEmail(String email) {
        WebElement emailElement = getWebDriver().findElement(emailField);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void navigateToRegistrationPage() {
        getWebDriver().findElement(navBarAccount).click();
        getWebDriver().findElement(loginButton).click();
        getWebDriver().findElement(notYetaCustomerLink).click();
    }

    public void registerAs (Customer customer) throws InterruptedException {
        navigateToRegistrationPage();
        enterEmail(customer.getEmailForRegistration());
        enterPassword(customer.getPassword());
        enterConfirmationPassword(customer.getPassword());
        selectSecurityQuestion();
        answerToSecurityQuestion(customer.getAnswer());
        clickOnRegisterButton();

    }
}

