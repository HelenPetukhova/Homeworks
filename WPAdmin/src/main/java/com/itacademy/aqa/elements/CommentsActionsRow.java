package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.ActionsEnum;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommentsActionsRow {
    private static Logger logger = Logger.getLogger(CommentsActionsRow.class);

    public CommentsActionsRow() {
    }

    public void clickOnItem(String commentText, ActionsEnum actionsEnum) {
        logger.info("Selecting the action from comments action row: " + actionsEnum + " for comment: " + commentText);
        String commentTextString = String.format("//td[contains(@class,'comment')]//p[text()='%s']", commentText);
        WebElement commentTextLink = Browser.waitForElementToBeClickableAndFind(By.xpath(commentTextString));

        Actions actions = new Actions(Browser.getWebDriver());
        actions.moveToElement(commentTextLink).perform();

        String actionItemString = String.format("//td[contains(@class,'comment')]//p[text()='%s']/..//*[@class='row-actions']//*[@class='%s']/a", commentText, actionsEnum.getValue());
        By actionItemLocator = By.xpath(actionItemString);

        WebElement actionLink = Browser.waitForElementToBeClickableAndFind(actionItemLocator);

        actionLink.click();

    }

    public boolean isActionOptionAvailable(String commentText, ActionsEnum actionsEnum) {
        logger.info("Finding if action option available in Actions row");
        String commentTextString = String.format("//td[contains(@class,'comment')]//p[text()='%s']", commentText);
        WebElement commentTextLink = Browser.waitForElementToBeClickableAndFind(By.xpath(commentTextString));

        Actions actions = new Actions(Browser.getWebDriver());
        actions.moveToElement(commentTextLink).perform();

        String actionItemString = String.format("//td[contains(@class,'comment')]//p[text()='%s']/..//*[@class='row-actions']/span[contains(@class,'%s')]", commentText, actionsEnum.getValue());
        By actionItemLocator = By.xpath(actionItemString);

        return Browser.waitForElementToBeClickableAndFind(actionItemLocator).isDisplayed();

    }


}
