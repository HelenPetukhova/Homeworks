package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseWPPage {
    private static org.apache.log4j.Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(id = "user_login")
    private WebElement usernameInput;
    @FindBy(id = "user_pass")
    private WebElement passwordInput;
    @FindBy(id = "wp-submit")
    private WebElement loginButton;


    public LoginPage() {
        super();
        PageFactory.initElements(Browser.getWebDriver(), this);

    }

    @Override
    public boolean isPageOpened() {
        try {
            Browser.waitForElementToBeClickable(loginButton);
            return loginButton.isDisplayed();
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Step("Logging in")
    public void doLogin(String role, String userName, String password) {
        logger.info("Log in WP Admin as " + role);
        logger.debug("Entering userName and password of " + role);
        fillLoginData(role, userName, password);
        logger.info("Username and password of " + role + " are entered in Login form");
        submitLoginForm();
        logger.info("Login Form submitted");
    }

    @Step("Filling username and password")
    private void fillLoginData(String role, String userName, String password) {
        logger.info("Filling credentials in Login form");
        logger.debug("Finding Username input field");
        Browser.waitForElementToBeClickable(usernameInput);
        logger.debug("Clearing Username input field");
        usernameInput.clear();
        //  usernameInput.sendKeys("test-admin");
        logger.debug("Entering userName of " + role + "in Username input field");
        usernameInput.sendKeys(userName);
        logger.info("Username is entered in Login form");

        logger.debug("Finding Password input field");
        Browser.waitForElementToBeClickable(passwordInput);
        logger.debug("Clearing Password input field");
        passwordInput.clear();
        logger.debug("Entering password of " + role + "in Password input field");
        passwordInput.sendKeys(password);
        logger.info("Password is entered in Login form");

        //  passwordInput.sendKeys("&2agnh5MyevReS8jhoYDTtbt");
    }

    @Step("Clicking LogIn button")
    public void submitLoginForm() {
        logger.info("Login form submitting");
        logger.debug("Finding LogIn button");
        Browser.waitForElementToBeClickable(loginButton);
        loginButton.click();
        logger.info("Login button clicked");
    }


}

