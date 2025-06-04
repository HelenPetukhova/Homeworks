package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.data.User;
import com.itacademy.aqa.enums.RolesEnum;
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

    private static Logger logger = Logger.getLogger(NewUserPage.class);

    public NewUserPage() {
        super();
    }


    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForVisibilityOfElementLocatedAndFind(ADD_NEW_USER_TITLE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public void feelUsernameField(String userName) {
        logger.info("Filling 'Username' field");
        WebElement usernameField = Browser.waitForElementToBeClickableAndFind(USERNAME_FIELD_LOCATOR);
        usernameField.clear();
        //  String userName = "user_" + RandomUtil.generateRandomString(8);
        usernameField.sendKeys(userName);
    }

    public void feelEmailField(String email) {
        logger.info("Filling 'Email' field");
        WebElement emailField = Browser.waitForElementToBeClickableAndFind(EMAIL_FIELD_LOCATOR);
        emailField.clear();
        //    String email = RandomUtil.generateEmail(6);
        emailField.sendKeys(email);
    }

    public void feelFirstNameField(String firstName) {
        logger.info("Filling 'First Name' field");
        WebElement firstNameField = Browser.waitForElementToBeClickableAndFind(FIRST_NAME_FIELD_LOCATOR);
        firstNameField.clear();
        //  String firstName = RandomUtil.generateRandomString(10);
        firstNameField.sendKeys(firstName);
    }


    public void feelLastNameField(String lastName) {
        logger.info("Filling 'Last Name' field");
        WebElement lastNameField = Browser.waitForElementToBeClickableAndFind(LAST_NAME_FIELD_LOCATOR);
        lastNameField.clear();
        //   String lastName = RandomUtil.generateRandomString(10);
        lastNameField.sendKeys(lastName);
    }


    public void feelWebsiteField(String website) {
        logger.info("Filling 'Website' field");
        WebElement websiteField = Browser.waitForElementToBeClickableAndFind(WEBSITE_FIELD_LOCATOR);
        websiteField.clear();
        //    String website = RandomUtil.generateRandomString(10) + ".kltest";
        websiteField.sendKeys(website);
    }


    public void feelPasswordField(String website) {
        logger.info("Filling 'Password' field");
        WebElement passwordField = Browser.waitForElementToBeClickableAndFind(PASSWORD_FIELD_LOCATOR);
        passwordField.clear();
        //    String website = RandomUtil.generateRandomString(10) + ".kltest";
        passwordField.sendKeys(website);
    }

    public String getPassword() {
        WebElement passwordField = Browser.waitForElementToBeClickableAndFind(PASSWORD_FIELD_LOCATOR);
        return passwordField.getAttribute("data-pw");

    }


    public void selectRole(RolesEnum rolesEnum) {
        logger.info("Selecting user's role: " + rolesEnum);

//        RolesEnum[] roles = RolesEnum.values();
//        Random random = new Random();
//        RolesEnum selectedRole = roles[random.nextInt(roles.length)];
//        String roleValue = selectedRole.getValue();

        WebElement roleDdlElement = Browser.waitForElementToBeClickableAndFind(ROLE_DDL_LOCATOR);
        Select roleDdl = new Select(roleDdlElement);
        roleDdl.selectByValue(rolesEnum.getValue());

    }

    public void clickAddNewUserButton() {
        logger.info("Clicking 'Add New User' button");

        WebElement addNewUserButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_USER_BUTTON_LOCATOR);
        addNewUserButton.click();
    }


    public void feelAddNewUserForm(User user) {
        logger.info("Filling all the fields on 'Add New User' form");
        feelUsernameField(user.getUsername());
        feelEmailField(user.getEmail());
        feelFirstNameField(user.getFirstName());
        feelLastNameField(user.getLastName());
        feelWebsiteField(user.getWebsite());
        feelPasswordField(user.getPassword());
        selectRole(user.getRoleEnum());
        clickAddNewUserButton();
    }




}