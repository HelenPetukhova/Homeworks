package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.ActionsEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UsersActionsRow {
    private static final Logger logger = Logger.getLogger(UsersActionsRow.class);

    public UsersActionsRow() {
    }

    @Step("Select the action from users action row: '{actionsEnum}' for user '{userName}'")
    public void clickOnItem(String userName, ActionsEnum actionsEnum) {
        logger.info("Selecting an option in Users actions row");
        String usernameString = String.format("//td//*[text()='%s']", userName);
        WebElement usernameLink = Browser.waitForElementToBeClickableAndFind(By.xpath(usernameString));

        Actions actions = new Actions(Browser.getWebDriver());
        actions.moveToElement(usernameLink).perform();

        String actionItemString = String.format("//td//*[text()='%s']/../..//*[@class='row-actions']//*[@class='%s']", userName, actionsEnum.getValue());
        By actionItemLocator = By.xpath(actionItemString);

        WebElement actionLink = Browser.waitForElementToBeClickableAndFind(actionItemLocator);

        actionLink.click();

    }

    }
