package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.LeftMenuEnum;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class LeftMenu {

    private static final By LEFT_MENU_ITEMS_LOCATOR = By.xpath("//*[@id=\"adminmenu\"]/li//div[@class=\"wp-menu-name\"]");
    private static final Logger logger = Logger.getLogger(LeftMenu.class);

    public LeftMenu() {
    }

    @Step("Click the Item of Left menu: '{leftMenuEnum}'")
    public void clickOnItem(LeftMenuEnum leftMenuEnum) {
        logger.info("Clicking on item from left menu: " + leftMenuEnum);

        By leftMenuLocator = By.id(leftMenuEnum.getValue());

        logger.debug("Creating locator for left menu option: " + leftMenuLocator);
        WebElement leftMenu = Browser.waitForElementToBeClickableAndFind(leftMenuLocator);
        Allure.addAttachment("Selecting the item from left menu",leftMenuEnum.getValue() );
        leftMenu.click();
        logger.info("Item selected in left menu: " + leftMenuEnum);

    }

    @Step("Get all the Items of Left menu")
    public List<String> getLeftMenuItemsTitles() {
        logger.info("Getting titles of left menu items");

        List<WebElement> menuItemsWebElements = Browser.waitForPresenceOfAllElementsAndFind(LEFT_MENU_ITEMS_LOCATOR);
        logger.debug("Found " + menuItemsWebElements.size() + " items in left menu");

        List<String> menuItemsTitles = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) Browser.getWebDriver();


        for (WebElement menuItemsWebElement : menuItemsWebElements) {
            String onlyMenuItemsText = (String) Browser.getJavascriptExecutor().executeScript(
                    "return arguments[0].childNodes[0].nodeValue.trim();", menuItemsWebElement);  // arguments[0] - ссылка на menuItemsWebElement, childNodes[0] - смотрит на первый дочерний узел элемента (Comments), nodeValue - возвращает его текст
            menuItemsTitles.add(onlyMenuItemsText);
        }
        logger.info("Got " + menuItemsTitles.size() + " menu items: " + menuItemsTitles);
        return menuItemsTitles;
    }


}
