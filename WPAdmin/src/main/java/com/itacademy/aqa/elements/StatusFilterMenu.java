package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.StatusFilterMenuEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StatusFilterMenu {
    private static final Logger logger = Logger.getLogger(StatusFilterMenu.class);

    public StatusFilterMenu() {
    }

    @Step("Apply status filter '{statusFilterMenuEnum}'")
    public void clickOnItem(StatusFilterMenuEnum statusFilterMenuEnum) {
        logger.info("Selecting an option in 'Status' filter" + statusFilterMenuEnum);
        String statusMenuItemTemplateLocator = String.format("//*[@class='subsubsub']/*[@class='%s']/a", statusFilterMenuEnum.getValue());
        By xPath = By.xpath(statusMenuItemTemplateLocator);

        WebElement statusFilterLink = Browser.waitForElementToBeClickableAndFind(xPath);

        statusFilterLink.click();

    }



}
