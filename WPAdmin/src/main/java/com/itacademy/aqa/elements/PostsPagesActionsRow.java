package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.ActionsEnum;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PostsPagesActionsRow {
    private static Logger logger = Logger.getLogger(PostsPagesActionsRow.class);

    public PostsPagesActionsRow() {
    }

    public void clickOnItem(String postOrPageTitle, ActionsEnum actionsEnum) {
        logger.info("Clicking on option from actions row");

        String postTitleString = String.format("//td[contains(@class,'title')]//strong[.='%s']", postOrPageTitle);
        WebElement postTitleLink = Browser.waitForElementToBeClickableAndFind(By.xpath(postTitleString));

        Actions actions = new Actions(Browser.getWebDriver());
        actions.moveToElement(postTitleLink).perform();

        String actionItemString = String.format("//td[contains(@class,'title')]//strong[.='%s']//..//..//*[@class='row-actions']//span[contains(@class,'%s')]", postOrPageTitle, actionsEnum.getValue());
        By actionItemLocator = By.xpath(actionItemString);

        WebElement actionLink = Browser.waitForElementToBeClickableAndFind(actionItemLocator);

        actionLink.click();

    }


}
