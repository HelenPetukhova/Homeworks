package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StatusFilterMenu {
    private static Logger logger = Logger.getLogger(StatusFilterMenu.class);

    public StatusFilterMenu() {
    }


    public void clickOnItem(StatusFilterMenuEnum statusFilterMenuEnum) {

        String STATUS_MENU_ITEM_TEMPLATE_LOCATOR = String.format("//*[@class='subsubsub']/*[@class='%s']/a", statusFilterMenuEnum.getValue());
        By xPath = By.xpath(STATUS_MENU_ITEM_TEMPLATE_LOCATOR);

        WebElement statusFilterLink = Browser.waitForElementToBeClickableAndFind(xPath);

        statusFilterLink.click();

    }



}
