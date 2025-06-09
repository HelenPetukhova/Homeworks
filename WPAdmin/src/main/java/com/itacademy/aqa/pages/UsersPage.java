package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.UsersActionsRow;
import com.itacademy.aqa.enums.ActionsEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UsersPage extends BaseAdminPage {

    private final UsersActionsRow usersActionsRow;
    private static final By USERS_PAGE_TITLE_LOCATOR = By.xpath("//h1[contains(text(),'Users')]");
    private static final By ADD_NEW_USER_BUTTON_LOCATOR = By.className("page-title-action");
    private static final By ALL_USERNAMES_LOCATOR = By.xpath("//*[@id='the-list']//*[contains(@class,'username')]");
    private static final By DELETE_ALL_CONTENT_RADIOBUTTON_OPTION_LOCATOR = By.id("delete_option0");
    private static final By CONFORM_DELETION_BUTTON_LOCATOR = By.id("submit");
    private static final Logger logger = Logger.getLogger(UsersPage.class);

    public UsersPage() {
        super();
        usersActionsRow = new UsersActionsRow();
    }

    public UsersActionsRow getUsersActionsRow() {
        return usersActionsRow;
    }

    @Override
    @Step("Check that 'Users' page is opened")
    public boolean isPageOpened() {
        try {
            WebElement usersPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(USERS_PAGE_TITLE_LOCATOR);
            return usersPageTitle.isDisplayed();
        } catch (RuntimeException ex) {
            return false;
        }

    }

    @Step("Click 'Add New User' button")
    public void clickAddNewUser() {
        logger.info("Clicking 'Add New User' button");
        WebElement addNewUserButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_USER_BUTTON_LOCATOR);
        addNewUserButton.click();
    }

    @Step("Get all usernames")
    public List<String> getAllUsernames() {
        logger.info("Getting all usernames on 'Users' page");
        List<WebElement> allUsernamesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_USERNAMES_LOCATOR);
        List<String> allUsernames = new ArrayList<>();

        for (WebElement usernameElement : allUsernamesElements) {
            String username = usernameElement.getText();
            allUsernames.add(username);
        }

        return allUsernames;
    }

    @Step("Delete user '{userName}'")
    public void deleteUser(String userName) {
        logger.info("Deleting a user");
        getUsersActionsRow().clickOnItem(userName, ActionsEnum.DELETE);
        selectDeleteAllContentRadioButton();
        clickConfirmDeletionButton();
    }

    @Step("Select 'Delete All Content' radio-button")
    public void selectDeleteAllContentRadioButton() {
        logger.info("Selecting 'Delete All Content' radio button");
        WebElement deleteAllContentRadioButtonOption = Browser.waitForElementToBeClickableAndFind(DELETE_ALL_CONTENT_RADIOBUTTON_OPTION_LOCATOR);
        deleteAllContentRadioButtonOption.click();
    }

    @Step("Click 'Confirm Deletion' button")
    public void clickConfirmDeletionButton() {
        logger.info("Clicking 'Confirm Deletion' button");
        WebElement conformDeletionButton = Browser.waitForElementToBeClickableAndFind(CONFORM_DELETION_BUTTON_LOCATOR);
        conformDeletionButton.click();
    }
}