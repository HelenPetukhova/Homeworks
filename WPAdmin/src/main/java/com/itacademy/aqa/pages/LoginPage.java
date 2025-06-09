package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseWPPage {
    private static final Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(id = "user_login")
    private WebElement usernameInput;
    @FindBy(id = "user_pass")
    private WebElement passwordInput;
    @FindBy(id = "wp-submit")
    private WebElement loginButton;
    @FindBy(id = "login_error")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@id=\"login_error\"]/p")
    private WebElement errorMessageText;

    public LoginPage() {
        super();
        PageFactory.initElements(Browser.getWebDriver(), this);

    }

    @Override
    @Step("Check that 'Login' page is opened")
    public boolean isPageOpened() {
        try {
            Browser.waitForElementToBeClickable(loginButton);
            return loginButton.isDisplayed();
        } catch (RuntimeException ex) {
            logger.error("'Log In' button is not displayed or Login page is not opened");
            return false;
        }
    }

    @Step("Login as user with role: '{role}'")
    public void doLogin(String role, String userName, String password) {
        logger.info("Log in WP Admin as " + role);

        fillLoginData(role, userName, password);
        Browser.takeScreenShot();
        Browser.saveScreenShot();

        logger.info("Username and password of " + role + " are entered in Login form");

        submitLoginForm();

        logger.info("Login Form submitted");
    }

    @Step("Enter username and password of user with role: '{role}'")
    private void fillLoginData(String role, String userName, String password) {
        logger.info("Filling credentials in Login form");
        Browser.waitForElementToBeClickable(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        logger.info("UserName of " + role + " is entered in Username input field");

        Browser.waitForElementToBeClickable(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        logger.info("Password of " + role + " is entered in Password input field");

    }

    @Step("Click LogIn button")
    public void submitLoginForm() {
        logger.info("Login form submitting");
        Browser.waitForElementToBeClickable(loginButton);
        loginButton.click();
    }


    @Step("Finding error message when credentials are invalid")
    public boolean isErrorMessageForInvalidCredentialsDisplayed() {
        logger.info("Finding if error message is displayed");
        try {
            Browser.waitForElementToBeClickable(errorMessage);
            Browser.takeScreenShot();
            return errorMessage.isDisplayed();
        } catch (RuntimeException ex) {
            logger.error("Error message is not displayed");
            Browser.takeScreenShot();
            return false;
        }
    }

    @Step("Getting error message text")
    public String getErrorMessageText() {
        logger.debug("Getting text of error message");
        return errorMessageText.getText().trim();
    }
}