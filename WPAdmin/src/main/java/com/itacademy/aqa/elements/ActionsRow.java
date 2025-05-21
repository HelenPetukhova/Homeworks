package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsRow {
    private static Logger logger = Logger.getLogger(ActionsRow.class);

    public ActionsRow() {
    }

//*[@class='row-actions']//span[contains(@class,'inline')]
    //td[contains(@class,'title')]//strong[.='KL Post to delete']//..//..//*[@class='row-actions']//span[contains(@class,'untrash')]
    //td[contains(@class,'title')]//strong[.='%s']//..//..//*[@class='row-actions']//span[contains(@class,'%s')]

    public void clickOnItem(String postTitle, ActionsEnum actionsEnum) {

        String postTitleString = String.format("//td[contains(@class,'title')]//strong[.='%s']", postTitle);
        WebElement postTitleLink = Browser.waitForElementToBeClickableAndFind(By.xpath(postTitleString));

        Actions actions = new Actions(Browser.getWebDriver());
        actions.moveToElement(postTitleLink).perform();

        String actionItemString = String.format("//td[contains(@class,'title')]//strong[.='%s']//..//..//*[@class='row-actions']//span[contains(@class,'%s')]", postTitle, actionsEnum.getValue());
        By actionItemLocator = By.xpath(actionItemString);

        WebElement actionLink = Browser.waitForElementToBeClickableAndFind(actionItemLocator);

        actionLink.click();

    }


}
