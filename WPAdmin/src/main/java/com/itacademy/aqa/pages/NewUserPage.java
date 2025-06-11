package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.data.User;
import com.itacademy.aqa.enums.RolesEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewUserPage extends BaseAdminPage {
    private static final By ADD_NEW_USER_TITLE_LOCATOR = By.xpath("//*[@id='add-new-user'][contains(text(),'Add New User')]");
    private static final By USERNAME_FIELD_LOCATOR = By.id("user_login");
    private static final By EMAIL_FIELD_LOCATOR = By.id("email");
    private static final By FIRST_NAME_FIELD_LOCATOR = By.id("first_name");
    private static final By LAST_NAME_FIELD_LOCATOR = By.id("last_name");
    private static final By WEBSITE_FIELD_LOCATOR = By.id("url");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("pass1");
    private static final By ROLE_DDL_LOCATOR = By.id("role");
    private static final By ADD_NEW_USER_BUTTON_LOCATOR = By.id("createusersub");

    private static final Logger logger = Logger.getLogger(NewUserPage.class);

    public NewUserPage() {
        super();
    }


    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForVisibilityOfElementLocatedAndFind(ADD_NEW_USER_TITLE_LOCATOR).isDisplayed();
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Step("Enter username")
    public void fillUsernameField(String userName) {
        logger.info("Filling 'Username' field");
        WebElement usernameField = Browser.waitForElementToBeClickableAndFind(USERNAME_FIELD_LOCATOR);
        usernameField.clear();
        usernameField.sendKeys(userName);
    }

    @Step("Enter email")
    public void fillEmailField(String email) {
        logger.info("Filling 'Email' field");
        WebElement emailField = Browser.waitForElementToBeClickableAndFind(EMAIL_FIELD_LOCATOR);
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Enter First Name")
    public void fillFirstNameField(String firstName) {
        logger.info("Filling 'First Name' field");
        WebElement firstNameField = Browser.waitForElementToBeClickableAndFind(FIRST_NAME_FIELD_LOCATOR);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    @Step("Enter Last Name")
    public void fillLastNameField(String lastName) {
        logger.info("Filling 'Last Name' field");
        WebElement lastNameField = Browser.waitForElementToBeClickableAndFind(LAST_NAME_FIELD_LOCATOR);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @Step("Enter website")
    public void fillWebsiteField(String website) {
        logger.info("Filling 'Website' field");
        WebElement websiteField = Browser.waitForElementToBeClickableAndFind(WEBSITE_FIELD_LOCATOR);
        websiteField.clear();
        websiteField.sendKeys(website);
    }

    @Step("Enter password")
    public void fillPasswordField(String password) {
        logger.info("Filling 'Password' field");
        WebElement passwordField = Browser.waitForElementToBeClickableAndFind(PASSWORD_FIELD_LOCATOR);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public String getPassword() {
        WebElement passwordField = Browser.waitForElementToBeClickableAndFind(PASSWORD_FIELD_LOCATOR);
        return passwordField.getAttribute("data-pw");

    }

    @Step("Select a user's role")
    public void selectRole(RolesEnum rolesEnum) {
        logger.info("Selecting user's role: " + rolesEnum);
        WebElement roleDdlElement = Browser.waitForElementToBeClickableAndFind(ROLE_DDL_LOCATOR);
        Select roleDdl = new Select(roleDdlElement);
        roleDdl.selectByValue(rolesEnum.getValue());

    }

    @Step("Click 'Add New User' button")
    public void clickAddNewUserButton() {
        logger.info("Clicking 'Add New User' button");

        WebElement addNewUserButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_USER_BUTTON_LOCATOR);
        addNewUserButton.click();
    }

    @Step("Fill all the fields of 'Add New User' form")
    public void feelAddNewUserForm(User user) {
        logger.info("Filling all the fields on 'Add New User' form");
        fillUsernameField(user.getUsername());
        fillEmailField(user.getEmail());
        fillFirstNameField(user.getFirstName());
        fillLastNameField(user.getLastName());
        fillWebsiteField(user.getWebsite());
        fillPasswordField(user.getPassword());
        selectRole(user.getRoleEnum());
        clickAddNewUserButton();
    }


}