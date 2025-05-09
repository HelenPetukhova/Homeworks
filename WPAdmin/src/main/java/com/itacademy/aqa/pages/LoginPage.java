package com.itacademy.aqa.pages;

import org.example.config.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseWPPage {

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


    public void doLogin(String userName, String password) {
      //  Browser.getWebDriver().get("https://test-automation-epc6e4eqgsgdhged.eastus2-01.azurewebsites.net/wp-admin/");
        fillLoginData(userName, password);
        submitLoginForm();
    }


    private void fillLoginData(String userName, String password) {
        Browser.waitForElementToBeClickable(usernameInput);
        usernameInput.clear();
        //  usernameInput.sendKeys("test-admin");
        usernameInput.sendKeys(userName);

        Browser.waitForElementToBeClickable(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        //  passwordInput.sendKeys("&2agnh5MyevReS8jhoYDTtbt");
    }


    public void submitLoginForm() {
        Browser.waitForElementToBeClickable(loginButton);
        loginButton.click();
    }


}
